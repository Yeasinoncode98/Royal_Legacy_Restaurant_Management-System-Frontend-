package com.restaurant.repository;
import com.restaurant.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem,Long> {
    List<FoodItem> findByAvailableTrue();
    List<FoodItem> findByCategoryAndAvailableTrue(String category);
    @Query("SELECT f FROM FoodItem f WHERE f.available=true AND (LOWER(f.name) LIKE LOWER(CONCAT('%',:k,'%')) OR LOWER(f.description) LIKE LOWER(CONCAT('%',:k,'%')))")
    List<FoodItem> searchByKeyword(@Param("k") String keyword);
    List<FoodItem> findTop6ByAvailableTrueOrderByRatingDesc();
}
