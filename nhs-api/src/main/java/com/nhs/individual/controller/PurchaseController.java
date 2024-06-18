package com.nhs.individual.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nhs.individual.service.ZalopayService;
import com.nhs.individual.zalopay.model.OrderCallback;
import com.nhs.individual.zalopay.model.OrderPurchaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/api/v1/purchase")
public class PurchaseController {
    @Autowired
    ZalopayService zalopayService;
    @RequestMapping(value="/{orderId}/zalopay",method= RequestMethod.GET)
    public OrderPurchaseInfo purchase(@PathVariable(name = "orderId") Integer orderId){
        return zalopayService.purchaseZalo(orderId);
    }
    @RequestMapping(value = "/zalopay/callback",method = RequestMethod.POST)
    public String zalopayCallBank(@RequestBody OrderCallback callback) throws JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException {
        return zalopayService.zalopayHandlerCallBack(callback);
    }
}
