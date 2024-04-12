package com.nhs.individual.Service;

import com.nhs.individual.Domain.Cart;
import com.nhs.individual.Domain.User;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Repository.CartRepository;
import com.nhs.individual.Utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    AuthService authService;
    static final Logger logger= LoggerFactory.getLogger(CartService.class);

    public Cart saveCart(Cart cart){
        User user = authService.getCurrentUser();
        cart.setUser(user);
        return cartRepository.save(cart);
    }
    public Optional<Cart> findById(int id){
        return cartRepository.findById(id);
    }
    public Cart update(Integer id,Cart cart){
        return cartRepository.findById(id).map(oldCart->{
            return cartRepository.save(ObjectUtils.merge(oldCart,cart,Cart.class));
        }).orElseThrow(()->new ResourceNotFoundException("Cart not found"));
    }
    public void deleteById(int id){
        cartRepository.deleteById(id);
    }
    public Collection<Cart> findAllByUserId(int userId){
        return cartRepository.findAllByUser_Id(userId);
    }
}
