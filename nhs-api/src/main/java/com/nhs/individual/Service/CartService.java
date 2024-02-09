package com.nhs.individual.Service;

import com.nhs.individual.Domain.Cart;
import com.nhs.individual.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    public Cart saveCart(Cart cart){
        return cartRepository.save(cart);
    }
    public Optional<Cart> findById(int id){
        return cartRepository.findById(id);
    }
    public void deleteById(int id){
        cartRepository.deleteById(id);
    }

}
