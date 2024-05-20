package com.nhs.individual.Controller;

import com.nhs.individual.Domain.CartItem;
import com.nhs.individual.Service.AuthService;
import com.nhs.individual.Service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/cart")
public class CartController {
    @Autowired
    CartItemService cartItemService;
    @Autowired
    AuthService authService;
    @RequestMapping(method = RequestMethod.GET)
    public Collection<CartItem> getUserCarts(){
        return cartItemService.findAllByUserId(authService.getCurrentUser().getId());
    }
    @RequestMapping(method = RequestMethod.POST)
    public CartItem create(@RequestBody CartItem cartItem){
        return cartItemService.save(cartItem);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public void updateById(@PathVariable(value = "id") Integer id,
                                           @RequestBody CartItem cart
    ){
        cartItemService.update(id,cart);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(value = "id") Integer id){
        cartItemService.deleteById(id);
    }

}
