package com.nhs.individual.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nhs.individual.service.ShopOrderService;
import com.nhs.individual.service.ZalopayService;
import com.nhs.individual.vnpay.VNPayService;
import com.nhs.individual.vnpay.config.Config;
import com.nhs.individual.vnpay.config.VNPayConfig;
import com.nhs.individual.zalopay.model.OrderCallback;
import com.nhs.individual.zalopay.model.OrderPurchaseInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/purchase")
@AllArgsConstructor
public class PurchaseController {
    private final ZalopayService zalopayService;
    private final VNPayService vnPayService;
    private final ShopOrderService shopOrderService;
    @RequestMapping(value="/{orderId}/zalopay",method= RequestMethod.GET)
    public OrderPurchaseInfo purchase(@PathVariable(name = "orderId") Integer orderId){
        return zalopayService.purchaseZalo(orderId);
    }
    @RequestMapping(value = "/zalopay/callback",method = RequestMethod.POST)
    public String zalopayCallBank(@RequestBody OrderCallback callback) throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException {
        return zalopayService.zalopayHandlerCallBack(callback);
    }

    @RequestMapping(value="/{orderId}/vnpay",method= RequestMethod.GET)
    public String purchaseByVNPay(@PathVariable(name = "orderId") Integer orderId,
                                  @RequestParam(name="bankcode",defaultValue = "QRONLY") String bankcode,
                                  @RequestParam(name = "order-type",defaultValue = "topup") String orderType,
                                  HttpServletRequest request){
        return shopOrderService.findById(orderId).map(shopOrder_->{
            return vnPayService.createOrder(shopOrder_.getTotal().intValue(),orderId.toString(),"http://localhost:8085",VNPayConfig.getIpAddress(request),bankcode,orderType);
        }).orElseThrow(()->new IllegalArgumentException("Illegal"));
    }

}

