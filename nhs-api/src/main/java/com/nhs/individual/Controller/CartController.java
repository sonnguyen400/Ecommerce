package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Cart;
import com.nhs.individual.Domain.CartItem;
import com.nhs.individual.Service.AuthService;
import com.nhs.individual.Service.CartItemService;
import com.nhs.individual.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    AuthService authService;
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Cart> getUserCarts(){
        return cartService.findAllByUserId(authService.getCurrentUser().getId());
    }
    @RequestMapping(method = RequestMethod.POST)
    public Cart create(@RequestBody Cart cart){
        return cartService.saveCart(cart);
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

}
