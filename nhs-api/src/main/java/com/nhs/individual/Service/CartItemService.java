package com.nhs.individual.Service;

import com.nhs.individual.Domain.CartItem;
import com.nhs.individual.Domain.User;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Repository.CartItemRepository;
import com.nhs.individual.Utils.ObjectUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductItemService productItemService;
    @Autowired
    AuthService authService;
    private static final ObjectUtils<CartItem> objectM= new ObjectUtils<>();

    public Collection<CartItem> findAllByUserId(Integer userId) {
        return cartItemRepository.findAllByUser_id(userId);
    }
    @Transactional
    public CartItem save(CartItem cartItem) {
        User user=authService.getCurrentUser();
        cartItem.setUser(user);
        return cartItemRepository.findByUser_idAndProduct_item_id(user.getId(),cartItem.getProductItem().getId())
                .map(cartItem1 -> {
                    cartItemRepository.updateQty(cartItem1.getId(),cartItem1.getQty()+cartItem.getQty());
                    cartItem1.setQty(cartItem.getQty()+cartItem1.getQty());
                    return cartItem1;
                }).orElse(cartItemRepository.save(cartItem));
    }
    @Transactional
    public CartItem update(Integer id,CartItem cartItem){
        return cartItemRepository.findById(id).map(item->{
            if(cartItem.getQty()!=null){
                item.setQty(cartItem.getQty());
            }
            if(cartItem.getProductItem()!=null&&cartItem.getProductItem().getId()!=null){
                productItemService.findById(cartItem.getProductItem().getId()).ifPresent(productItem->{
                    item.setProductItem(productItem);
                });
            }
            cartItemRepository.update(item.getId(), item.getQty(), item.getProductItem().getId());
            return item;
        }).orElseThrow(()->new ResourceNotFoundException("Could not find cart item with id " + cartItem.getId()));
    }

    public Optional<CartItem> findById(Integer id){
        return cartItemRepository.findById(id);
    }

    public void deleteById(Integer id) {
        cartItemRepository.deleteById(id);
    }
}
