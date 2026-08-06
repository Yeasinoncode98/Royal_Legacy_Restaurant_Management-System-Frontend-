package com.restaurant.service;
import com.restaurant.dto.CartItemDto;
import java.util.List;
/** INTERFACE — ABSTRACTION + POLYMORPHISM */
public interface Orderable {
    Double calculateTotal(List<CartItemDto> items);
    String generateOrderNumber();
}
