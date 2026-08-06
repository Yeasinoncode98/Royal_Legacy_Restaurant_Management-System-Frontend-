package com.restaurant.controller;
import com.restaurant.dto.*;
import com.restaurant.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService){ this.orderService=orderService; }

    @PostMapping public ResponseEntity<OrderResponse> place(@Valid @RequestBody OrderRequest req){
        return new ResponseEntity<>(orderService.placeOrder(req),HttpStatus.CREATED);
    }
    @GetMapping("/user/{userId}") public ResponseEntity<List<OrderResponse>> byUser(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getByUser(userId));
    }
    @GetMapping("/{orderNumber}") public ResponseEntity<OrderResponse> byNumber(@PathVariable String orderNumber){
        return ResponseEntity.ok(orderService.getByNumber(orderNumber));
    }
}
