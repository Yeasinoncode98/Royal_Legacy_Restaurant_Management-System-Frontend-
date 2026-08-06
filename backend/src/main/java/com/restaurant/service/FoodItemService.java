package com.restaurant.service;
import com.restaurant.exception.ResourceNotFoundException;
import com.restaurant.model.FoodItem;
import com.restaurant.repository.FoodItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodItemService {
    private final FoodItemRepository repo;
    public FoodItemService(FoodItemRepository repo){ this.repo=repo; }

    public List<FoodItem> getAllAvailable(){ return repo.findByAvailableTrue(); }
    public List<FoodItem> getByCategory(String cat){ return repo.findByCategoryAndAvailableTrue(cat); }
    public List<FoodItem> search(String kw){
        if(kw==null||kw.isBlank()) return getAllAvailable();
        return repo.searchByKeyword(kw.trim());
    }
    public List<FoodItem> getFeatured(){ return repo.findTop6ByAvailableTrueOrderByRatingDesc(); }
    public FoodItem getById(Long id){
        return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("FoodItem","id",id));
    }
    public FoodItem create(FoodItem f){ return repo.save(f); }
    public FoodItem update(Long id,FoodItem upd){
        FoodItem ex=getById(id);
        ex.setName(upd.getName()); ex.setDescription(upd.getDescription());
        ex.setPrice(upd.getPrice()); ex.setImageUrl(upd.getImageUrl());
        ex.setCategory(upd.getCategory()); ex.setRating(upd.getRating());
        ex.setAvailable(upd.getAvailable());
        return repo.save(ex);
    }
    public void delete(Long id){ repo.delete(getById(id)); }
}
