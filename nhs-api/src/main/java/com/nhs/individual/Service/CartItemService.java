package com.nhs.individual.Service;

import com.nhs.individual.Domain.CartItem;
import com.nhs.individual.Repository.CartItemRepository;
import com.nhs.individual.Utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    private static final ObjectUtils<CartItem> objectM=new ObjectUtils<CartItem>();

    public CartItem save(CartItem item) {
        return cartItemRepository.save(item);
    }
    public Collection<CartItem> findAllByUserId(Integer userId){
        return cartItemRepository.findAllByUser_id(userId);
    }
    public CartItem update(Integer id, CartItem item) {
        return cartItemRepository.findById(id).map(oldItem->{
            return cartItemRepository.save(objectM.merge(oldItem,item, CartItem.class));
        }).orElseThrow(() -> new RuntimeException("Cart not found"));
    }
    public void deleteById(Integer id) {
        cartItemRepository.deleteById(id);
    }
}
