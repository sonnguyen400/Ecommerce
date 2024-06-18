package com.nhs.individual.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhs.individual.Domain.ShopOrderStatus;
import com.nhs.individual.constant.OrderStatus;
import com.nhs.individual.dto.ShopOrderDto;
import com.nhs.individual.utils.JSON;
import com.nhs.individual.utils.ResponseUtils;
import com.nhs.individual.zalopay.config.ZaloConfig;
import com.nhs.individual.zalopay.crypto.HMACUtil;
import com.nhs.individual.zalopay.model.OrderCallback;
import com.nhs.individual.zalopay.model.OrderCallbackData;
import com.nhs.individual.zalopay.model.OrderInfo;
import com.nhs.individual.zalopay.model.OrderPurchaseInfo;
import com.nimbusds.jose.crypto.impl.HMAC;
import jakarta.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j
@Service
public class ZalopayService {
    @Autowired
    ShopOrderService orderService;
    @Autowired
    ShopOrderStatusService shopOrderStatusService;
    @Autowired
    ZaloConfig zalopayconfig;
    private Mac HmacSHA256;
    private final Logger logger= LoggerFactory.getLogger(this.getClass());



    public OrderPurchaseInfo purchaseZalo(Integer orderId){
        final Map<String,Object> embed_data = new HashMap<String,Object>(){{}};
        return orderService.findById(orderId).map(shopOrder_ -> {
            int random_id = new Random().nextInt(100000);

            try(CloseableHttpClient client = HttpClients.createDefault()) {
                OrderInfo orderInfom = new OrderInfo(zalopayconfig.appId,
                        shopOrder_.getUser().getId().toString(),
                        String.valueOf(random_id),
                        (long)900,
                        (long)50000,
                        " ",
                        null, JSON.stringify(new ShopOrderDto(shopOrder_)), JSON.stringify(embed_data),
                        zalopayconfig.key1, null, null);

                HttpPost post = new HttpPost(zalopayconfig.createOrderEndpoint);
                Map<String,Object> mapParams=orderInfom.toMap();
                List<NameValuePair> params = new ArrayList<>();
                for (Map.Entry<String, Object> e : mapParams.entrySet()) {
                    params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
                }
                post.setEntity(new UrlEncodedFormEntity(params));
                CloseableHttpResponse res = client.execute(post);
                return ResponseUtils.parseObject(res, OrderPurchaseInfo.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).orElseThrow(()->new RuntimeException("Error occurred while creating charging information"));
    }
    public String zalopayHandlerCallBack(OrderCallback callBack) throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException {
        HmacSHA256=Mac.getInstance("HmacSHA256");
        HmacSHA256.init(new SecretKeySpec(zalopayconfig.key2.getBytes(),"HmacSHA256"));
        JSONObject result = new JSONObject();
        byte[] hashBytes=HmacSHA256.doFinal(callBack.getData().getBytes());
        if(callBack.getMac().equals(DatatypeConverter.printHexBinary(hashBytes).toLowerCase())){
            OrderCallbackData orderCallbackData=JSON.parse(callBack.getData(), OrderCallbackData.class);
            logger.info("update order's status = success where app_trans_id = " + orderCallbackData.getApp_trans_id());
            result.put("return_code", 1);
            result.put("return_message", "success");

            ShopOrderStatus shopOrderStatus=new ShopOrderStatus();
            shopOrderStatus.setStatus(OrderStatus.CANCEL);
        }else{
            result.put("return_code", -1);
            result.put("return_message", "mac not equal");
        }
        return result.toString();
    }
    public OrderStatus getOrderStatus(String app_trans_id) throws URISyntaxException {
        String data=zalopayconfig.appId+"|"+app_trans_id+"|"+zalopayconfig.key1;
        String mac=HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256,zalopayconfig.key1,data);
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("app_id", String.valueOf(zalopayconfig.appId)));
        params.add(new BasicNameValuePair("app_trans_id", app_trans_id));
        params.add(new BasicNameValuePair("mac", mac));
        URIBuilder uri = new URIBuilder(zalopayconfig.queryOrderEndpoint);
        uri.addParameters(params);

        try(CloseableHttpClient client = HttpClients.createDefault()){
            HttpPost post = new HttpPost(uri.build());
            post.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpResponse res = client.execute(post);
            return ResponseUtils.parseObject(res, OrderStatus.class);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
