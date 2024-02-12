package com.nhs.individual.Controller;

import com.nhs.individual.Domain.CartItem;
import com.nhs.individual.Service.CartItemService;
import com.nhs.individual.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController(value = "/api/v1/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartItemService cartItemService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Collection<CartItem> findAllByUserId(@RequestParam(value = "userId",required=true) Integer userId){
        return cartItemService.findAllByCartId(userId);
    }
//    @RequestMapping(value = "/",method = RequestMethod.POST)
//    public CartItem create(@RequestBody CartItem cartItem,
//                             @RequestParam(value = "userId",required=true) Integer userId){
//        return cartItemService.addItem(userId,cartItem);
//    }
//    @RequestMapping(value = "/",method = RequestMethod.DELETE)
//    public void delete(@RequestParam(value = "id") Integer id){
//        cartItemService.deleteById(id);
//    }
//    @RequestMapping(value = "/",method = RequestMethod.PUT)
//    public CartItem update(@RequestBody CartItem cartItem,
//                             @RequestParam(value = "ud",required=true) Integer id){
//        return cartItemService.update(id,cartItem);
//    }
}
