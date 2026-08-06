package com.restaurant.dto;
import jakarta.validation.constraints.*;
import java.util.List;
public class OrderRequest {
    private Long userId;
    @NotBlank private String customerName;
    @NotBlank private String customerPhone;
    @NotBlank private String deliveryAddress;
    @NotEmpty private List<CartItemDto> items;
    private Double totalAmount;
    public OrderRequest(){}
    public Long getUserId(){return userId;} public void setUserId(Long u){userId=u;}
    public String getCustomerName(){return customerName;} public void setCustomerName(String n){customerName=n;}
    public String getCustomerPhone(){return customerPhone;} public void setCustomerPhone(String p){customerPhone=p;}
    public String getDeliveryAddress(){return deliveryAddress;} public void setDeliveryAddress(String a){deliveryAddress=a;}
    public List<CartItemDto> getItems(){return items;} public void setItems(List<CartItemDto> i){items=i;}
    public Double getTotalAmount(){return totalAmount;} public void setTotalAmount(Double t){totalAmount=t;}
}
