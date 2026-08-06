package com.restaurant.dto;
public class CartItemDto {
    private Long foodItemId; private Integer quantity; private Double unitPrice;
    public CartItemDto(){}
    public CartItemDto(Long foodItemId,Integer quantity,Double unitPrice){this.foodItemId=foodItemId;this.quantity=quantity;this.unitPrice=unitPrice;}
    public Long getFoodItemId(){return foodItemId;} public void setFoodItemId(Long f){foodItemId=f;}
    public Integer getQuantity(){return quantity;} public void setQuantity(Integer q){quantity=q;}
    public Double getUnitPrice(){return unitPrice;} public void setUnitPrice(Double u){unitPrice=u;}
}
