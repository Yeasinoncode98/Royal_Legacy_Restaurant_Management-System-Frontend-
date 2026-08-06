package com.restaurant.dto;
import java.time.LocalDateTime;
import java.util.List;
public class OrderResponse {
    private Long orderId; private String orderNumber; private String customerName;
    private String customerPhone; private String deliveryAddress; private Double totalAmount;
    private String status; private Integer estimatedDeliveryMinutes; private LocalDateTime createdAt;
    private List<OrderItemDto> items;

    public OrderResponse(){}

    public static class OrderItemDto {
        private String foodName; private Integer quantity; private Double unitPrice; private Double subtotal;
        public OrderItemDto(String foodName,Integer quantity,Double unitPrice,Double subtotal){
            this.foodName=foodName;this.quantity=quantity;this.unitPrice=unitPrice;this.subtotal=subtotal;
        }
        public String getFoodName(){return foodName;} public Integer getQuantity(){return quantity;}
        public Double getUnitPrice(){return unitPrice;} public Double getSubtotal(){return subtotal;}
    }

    public Long getOrderId(){return orderId;} public void setOrderId(Long o){orderId=o;}
    public String getOrderNumber(){return orderNumber;} public void setOrderNumber(String o){orderNumber=o;}
    public String getCustomerName(){return customerName;} public void setCustomerName(String n){customerName=n;}
    public String getCustomerPhone(){return customerPhone;} public void setCustomerPhone(String p){customerPhone=p;}
    public String getDeliveryAddress(){return deliveryAddress;} public void setDeliveryAddress(String a){deliveryAddress=a;}
    public Double getTotalAmount(){return totalAmount;} public void setTotalAmount(Double t){totalAmount=t;}
    public String getStatus(){return status;} public void setStatus(String s){status=s;}
    public Integer getEstimatedDeliveryMinutes(){return estimatedDeliveryMinutes;}
    public void setEstimatedDeliveryMinutes(Integer m){estimatedDeliveryMinutes=m;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime c){createdAt=c;}
    public List<OrderItemDto> getItems(){return items;} public void setItems(List<OrderItemDto> i){items=i;}
}
