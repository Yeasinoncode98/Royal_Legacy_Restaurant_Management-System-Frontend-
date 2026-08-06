package com.restaurant.model;
import jakarta.persistence.*;

/** INHERITANCE from BaseEntity. Represents a menu item. */
@Entity @Table(name = "food_items")
public class FoodItem extends BaseEntity {
    @Column(nullable=false) private String name;
    @Column(length=500) private String description;
    @Column(nullable=false) private Double price;
    private String imageUrl;
    @Column(nullable=false) private String category;
    private Double rating = 4.0;
    private Integer reviewCount = 0;
    private Boolean available = true;

    public FoodItem() {}
    public FoodItem(String name, String description, Double price, String imageUrl, String category, Double rating) {
        this.name=name; this.description=description; this.price=price;
        this.imageUrl=imageUrl; this.category=category; this.rating=rating; this.available=true;
    }

    public String getName() { return name; } public void setName(String n) { name=n; }
    public String getDescription() { return description; } public void setDescription(String d) { description=d; }
    public Double getPrice() { return price; } public void setPrice(Double p) { price=p; }
    public String getImageUrl() { return imageUrl; } public void setImageUrl(String u) { imageUrl=u; }
    public String getCategory() { return category; } public void setCategory(String c) { category=c; }
    public Double getRating() { return rating; } public void setRating(Double r) { rating=r; }
    public Integer getReviewCount() { return reviewCount; } public void setReviewCount(Integer rc) { reviewCount=rc; }
    public Boolean getAvailable() { return available; } public void setAvailable(Boolean a) { available=a; }
}
