package com.restaurant.service;
import com.restaurant.dto.*;
import com.restaurant.exception.ResourceNotFoundException;
import com.restaurant.model.*;
import com.restaurant.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/** Implements Orderable INTERFACE — POLYMORPHISM & METHOD OVERRIDING */
@Service
public class OrderService implements Orderable {
    private final OrderRepository orderRepo;
    private final FoodItemService foodItemService;
    private final UserService userService;

    public OrderService(OrderRepository orderRepo,FoodItemService foodItemService,UserService userService){
        this.orderRepo=orderRepo; this.foodItemService=foodItemService; this.userService=userService;
    }

    @Transactional
    public OrderResponse placeOrder(OrderRequest req){
        if(req.getItems()==null||req.getItems().isEmpty()) throw new IllegalArgumentException("Cart is empty");
        User user=null;
        if(req.getUserId()!=null){ try{ user=userService.findById(req.getUserId()); }catch(ResourceNotFoundException e){} }
        Double total=calculateTotal(req.getItems());
        Order order=new Order(generateOrderNumber(),user,total,req.getCustomerName(),req.getCustomerPhone(),req.getDeliveryAddress());
        order.setEstimatedDeliveryMinutes(25+new Random().nextInt(20));
        Order saved=orderRepo.save(order);
        List<OrderItem> items=new ArrayList<>();
        for(CartItemDto c:req.getItems()){
            FoodItem fi=foodItemService.getById(c.getFoodItemId());
            items.add(new OrderItem(saved,fi,c.getQuantity()));
        }
        saved.setItems(items);
        return toResponse(orderRepo.save(saved));
    }

    @Override
    public Double calculateTotal(List<CartItemDto> items){
        return items.stream().mapToDouble(i->i.getUnitPrice()*i.getQuantity()).sum();
    }

    @Override
    public String generateOrderNumber(){
        return "RL-"+String.format("%05d",new Random().nextInt(99999));
    }

    public List<OrderResponse> getByUser(Long userId){
        return orderRepo.findByUserIdOrderByCreatedAtDesc(userId).stream().map(this::toResponse).toList();
    }

    public OrderResponse getByNumber(String num){
        return toResponse(orderRepo.findByOrderNumber(num)
            .orElseThrow(()->new ResourceNotFoundException("Order","orderNumber",num)));
    }

    private OrderResponse toResponse(Order o){
        OrderResponse r=new OrderResponse();
        r.setOrderId(o.getId()); r.setOrderNumber(o.getOrderNumber());
        r.setCustomerName(o.getCustomerName()); r.setCustomerPhone(o.getCustomerPhone());
        r.setDeliveryAddress(o.getDeliveryAddress()); r.setTotalAmount(o.getTotalAmount());
        r.setStatus(o.getStatus().name()); r.setEstimatedDeliveryMinutes(o.getEstimatedDeliveryMinutes());
        r.setCreatedAt(o.getCreatedAt());
        r.setItems(o.getItems().stream().map(i->new OrderResponse.OrderItemDto(
            i.getFoodItem().getName(),i.getQuantity(),i.getUnitPrice(),i.getSubtotal())).toList());
        return r;
    }
}
