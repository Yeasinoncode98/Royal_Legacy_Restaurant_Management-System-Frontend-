package com.restaurant.model;
import jakarta.persistence.*;

@Entity @Table(name="order_items")
public class OrderItem extends BaseEntity {
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="order_id",nullable=false) private Order order;
    @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="food_item_id",nullable=false) private FoodItem foodItem;
    @Column(nullable=false) private Integer quantity;
    @Column(nullable=false) private Double unitPrice;
    @Column(nullable=false) private Double subtotal;

    public OrderItem() {}
    public OrderItem(Order order, FoodItem foodItem, Integer quantity) {
        this.order=order; this.foodItem=foodItem; this.quantity=quantity;
        this.unitPrice=foodItem.getPrice(); this.subtotal=foodItem.getPrice()*quantity;
    }

    public Order getOrder() { return order; } public void setOrder(Order o) { order=o; }
    public FoodItem getFoodItem() { return foodItem; } public void setFoodItem(FoodItem f) { foodItem=f; }
    public Integer getQuantity() { return quantity; } public void setQuantity(Integer q) { quantity=q; }
    public Double getUnitPrice() { return unitPrice; } public void setUnitPrice(Double u) { unitPrice=u; }
    public Double getSubtotal() { return subtotal; } public void setSubtotal(Double s) { subtotal=s; }
}
