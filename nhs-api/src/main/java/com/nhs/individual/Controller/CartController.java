package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Cart;
import com.nhs.individual.Domain.CartItem;
import com.nhs.individual.Service.CartItemService;
import com.nhs.individual.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController(value = "/api/v1/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartItemService cartItemService;
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Collection<CartItem> findAllByUserId(@RequestParam(value = "id",required=true) Integer userId){
        return cartItemService.findAllByCartId(userId);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Cart updateById(@PathVariable(value = "id") Integer id,
                                           @RequestBody Cart cart
    ){
        return cartService.update(id,cart);
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(value = "id") Integer id){
        cartService.deleteById(id);
    }
    //CartItem
    @RequestMapping(value = "/{cart_id}/item",method = RequestMethod.POST)
    public CartItem create(@RequestBody CartItem cartItem,
                             @RequestParam(value = "cart_id",required=true) Integer cartId){
        return cartItemService.addItem(cartId,cartItem);
    }
    @RequestMapping(value = "/{cart_id}/item/{item_id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "cart_id",required = false) Integer cart_id,
                       @PathVariable(value = "item_id") Integer item_id){
        cartItemService.deleteById(item_id);
    }

//    @RequestMapping(value = "/",method = RequestMethod.PUT)
//    public CartItem update(@RequestBody CartItem cartItem,
//                             @RequestParam(value = "ud",required=true) Integer id){
//        return cartItemService.update(id,cartItem);
//    }
}
