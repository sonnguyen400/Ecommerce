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
    private CartService cartService;
    private static final ObjectUtils<CartItem> objectM=new ObjectUtils<CartItem>();

    public CartItem addItem(Integer cartId,CartItem item) {
        return cartService.findById(cartId).map(cart->{
            if(Optional.ofNullable(item.getCart().getId()).isEmpty()) {
                item.setCart(cart);
            }
            return cartItemRepository.save(item);
        }).orElseThrow(
            () -> new RuntimeException("Cart not found")
        );
    }
    public Optional<CartItem> findById(Integer id){
        return cartItemRepository.findById(id);
    }
    public Collection<CartItem> findAllByCartId(Integer id){
        return cartItemRepository.findAllByCart_id(id);
    }
    public CartItem update(Integer id, CartItem item) {
        return findById(id).map(oldItem->{
            return cartItemRepository.save(objectM.merge(oldItem,item, CartItem.class));
        }).orElseThrow(() -> new RuntimeException("Cart not found"));
    }
    public void deleteById(Integer id) {
        cartItemRepository.deleteById(id);
    }
}
