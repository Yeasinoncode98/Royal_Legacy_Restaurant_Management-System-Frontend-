package com.restaurant.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/** INHERITANCE from BaseEntity. Uses COLLECTIONS (List). */
@Entity @Table(name = "orders")
public class Order extends BaseEntity {
    @Column(nullable=false,unique=true) private String orderNumber;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id") private User user;
    @OneToMany(mappedBy="order",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<OrderItem> items = new ArrayList<>();
    @Column(nullable=false) private Double totalAmount;
    @Column(nullable=false) private String customerName;
    @Column(nullable=false) private String customerPhone;
    @Column(nullable=false) private String deliveryAddress;
    @Enumerated(EnumType.STRING) private OrderStatus status = OrderStatus.PENDING;
    private Integer estimatedDeliveryMinutes = 30;

    public enum OrderStatus { PENDING, CONFIRMED, PREPARING, OUT_FOR_DELIVERY, DELIVERED, CANCELLED }

    public Order() {}
    public Order(String orderNumber, User user, Double totalAmount,
                 String customerName, String customerPhone, String deliveryAddress) {
        this.orderNumber=orderNumber; this.user=user; this.totalAmount=totalAmount;
        this.customerName=customerName; this.customerPhone=customerPhone;
        this.deliveryAddress=deliveryAddress; this.status=OrderStatus.CONFIRMED;
    }

    public String getOrderNumber() { return orderNumber; } public void setOrderNumber(String o) { orderNumber=o; }
    public User getUser() { return user; } public void setUser(User u) { user=u; }
    public List<OrderItem> getItems() { return items; } public void setItems(List<OrderItem> i) { items=i; }
    public Double getTotalAmount() { return totalAmount; } public void setTotalAmount(Double t) { totalAmount=t; }
    public String getCustomerName() { return customerName; } public void setCustomerName(String n) { customerName=n; }
    public String getCustomerPhone() { return customerPhone; } public void setCustomerPhone(String p) { customerPhone=p; }
    public String getDeliveryAddress() { return deliveryAddress; } public void setDeliveryAddress(String a) { deliveryAddress=a; }
    public OrderStatus getStatus() { return status; } public void setStatus(OrderStatus s) { status=s; }
    public Integer getEstimatedDeliveryMinutes() { return estimatedDeliveryMinutes; }
    public void setEstimatedDeliveryMinutes(Integer m) { estimatedDeliveryMinutes=m; }
}
