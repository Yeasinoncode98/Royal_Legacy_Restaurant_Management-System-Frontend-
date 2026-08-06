package com.restaurant.controller;
import com.restaurant.model.FoodItem;
import com.restaurant.service.FoodItemService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/foods")
public class FoodController {
    private final FoodItemService foodItemService;
    public FoodController(FoodItemService foodItemService){ this.foodItemService=foodItemService; }

    @GetMapping
    public ResponseEntity<List<FoodItem>> getAll(@RequestParam(required=false) String category,@RequestParam(required=false) String search){
        if(search!=null&&!search.isBlank()) return ResponseEntity.ok(foodItemService.search(search));
        if(category!=null&&!category.isBlank()) return ResponseEntity.ok(foodItemService.getByCategory(category));
        return ResponseEntity.ok(foodItemService.getAllAvailable());
    }
    @GetMapping("/featured") public ResponseEntity<List<FoodItem>> featured(){ return ResponseEntity.ok(foodItemService.getFeatured()); }
    @GetMapping("/{id}") public ResponseEntity<FoodItem> getById(@PathVariable Long id){ return ResponseEntity.ok(foodItemService.getById(id)); }
    @PostMapping public ResponseEntity<FoodItem> create(@RequestBody FoodItem f){ return new ResponseEntity<>(foodItemService.create(f),HttpStatus.CREATED); }
    @PutMapping("/{id}") public ResponseEntity<FoodItem> update(@PathVariable Long id,@RequestBody FoodItem f){ return ResponseEntity.ok(foodItemService.update(id,f)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ foodItemService.delete(id); return ResponseEntity.noContent().build(); }
}
