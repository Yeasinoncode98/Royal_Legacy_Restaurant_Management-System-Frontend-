package com.restaurant.config;
import com.restaurant.model.*;
import com.restaurant.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final FoodItemRepository foodRepo;
    private final UserRepository userRepo;
    public DataSeeder(FoodItemRepository foodRepo,UserRepository userRepo){this.foodRepo=foodRepo;this.userRepo=userRepo;}

    @Override
    public void run(String... args){
        seedAdmin();
        seedFood();
    }

    private void seedAdmin(){
        if(!userRepo.existsByEmail("admin@royallegacy.com"))
            userRepo.save(new User("Admin","admin@royallegacy.com","admin123","01700000000",User.Role.ADMIN));
    }

    private void seedFood(){
        if(foodRepo.count()>0) return;
        foodRepo.save(new FoodItem("Royal Grilled Chicken","Tender chicken grilled with secret spices, served with garlic butter rice",12.99,"https://images.unsplash.com/photo-1567620905732-2d1ec7ab7445?w=600","Grills",4.8));
        foodRepo.save(new FoodItem("Classic Beef Burger","Juicy beef patty with fresh lettuce, tomato, cheese and signature sauce",9.99,"https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=600","Burgers",4.7));
        foodRepo.save(new FoodItem("Margherita Pizza","Hand-tossed pizza with San Marzano tomatoes, fresh mozzarella and basil",11.99,"https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=600","Pizza",4.9));
        foodRepo.save(new FoodItem("Creamy Pasta Alfredo","Fettuccine pasta in rich parmesan cream sauce with grilled chicken",10.99,"https://images.unsplash.com/photo-1621996346565-e3dbc646d9a9?w=600","Pasta",4.6));
        foodRepo.save(new FoodItem("Crispy Fried Fish","Golden battered fish fillet with tartar sauce and seasoned fries",13.99,"https://images.unsplash.com/photo-1544943910-4c1dc44aab44?w=600","Seafood",4.5));
        foodRepo.save(new FoodItem("BBQ Ribs Platter","Slow-cooked baby back ribs with smoky BBQ glaze and coleslaw",18.99,"https://images.unsplash.com/photo-1544025162-d76594e8bb6e?w=600","Grills",4.9));
        foodRepo.save(new FoodItem("Spicy Prawn Stir Fry","Tiger prawns in spicy garlic chili sauce with jasmine rice",15.99,"https://images.unsplash.com/photo-1565557623262-b51c2513a641?w=600","Seafood",4.7));
        foodRepo.save(new FoodItem("Chicken Caesar Salad","Crispy romaine, croutons, parmesan and grilled chicken with Caesar dressing",9.49,"https://images.unsplash.com/photo-1550304943-4f24f54ddde9?w=600","Salads",4.5));
        foodRepo.save(new FoodItem("Double Smash Burger","Two smashed beef patties with caramelized onions, pickles and special sauce",11.49,"https://images.unsplash.com/photo-1553979459-d2229ba7433b?w=600","Burgers",4.8));
        foodRepo.save(new FoodItem("Pepperoni Pizza","Classic pizza loaded with pepperoni, mozzarella and fresh herbs",12.99,"https://images.unsplash.com/photo-1628840042765-356cda07504e?w=600","Pizza",4.7));
        foodRepo.save(new FoodItem("Chocolate Lava Cake","Warm chocolate cake with molten center, served with vanilla ice cream",6.99,"https://images.unsplash.com/photo-1606313564200-e75d5e30476c?w=600","Desserts",4.9));
        foodRepo.save(new FoodItem("Mango Cheesecake","Creamy New York cheesecake topped with fresh mango coulis",5.99,"https://images.unsplash.com/photo-1533134242443-d4fd215305ad?w=600","Desserts",4.6));
        foodRepo.save(new FoodItem("Fresh Lemonade","Hand-squeezed lemonade with mint and a hint of ginger",3.99,"https://images.unsplash.com/photo-1621263764928-df1444c5e859?w=600","Drinks",4.5));
        foodRepo.save(new FoodItem("Mushroom Risotto","Creamy Arborio rice with wild mushrooms, white wine and aged parmesan",13.49,"https://images.unsplash.com/photo-1476124369491-e7addf5db371?w=600","Pasta",4.6));
        foodRepo.save(new FoodItem("Veggie Deluxe Wrap","Grilled veggies, hummus, feta cheese and mixed greens in warm tortilla",8.99,"https://images.unsplash.com/photo-1626700051175-6818013e1d4f?w=600","Wraps",4.4));
        foodRepo.save(new FoodItem("Tomato Basil Soup","Velvety tomato soup with fresh basil oil and crusty sourdough bread",7.99,"https://images.unsplash.com/photo-1547592166-23ac45744acd?w=600","Salads",4.4));
    }
}
