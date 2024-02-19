package com.nhs.individual.Service;

import com.nhs.individual.Domain.Cart;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Repository.CartRepository;
import com.nhs.individual.Utils.ObjectUtils;
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
    public Cart update(Integer id,Cart cart){
        return cartRepository.findById(id).map(oldCart->{
            return cartRepository.save(ObjectUtils.merge(oldCart,cart,Cart.class));
        }).orElseThrow(()->new ResourceNotFoundException("Cart not found"));
    }
    public void deleteById(int id){
        cartRepository.deleteById(id);
    }

}
