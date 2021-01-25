package springcourse.kursspringbbot2abstractfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import springcourse.kursspringbbot2abstractfactory.meal.FoodType;
import springcourse.kursspringbbot2abstractfactory.meal.KitchenMeal;
import springcourse.kursspringbbot2abstractfactory.meal.TypeOfFood;

@Service
public class Restaurant {

    @Autowired
    public Restaurant(@TypeOfFood(foodType = FoodType.FAST_FOOD) KitchenMeal kitchenMeal) {

        System.out.println(kitchenMeal.getName());


//        Kitchen factory = new KitchenFactory();
//        KitchenMeal meal = factory.getMeal(FoodType.VEGETARIAN);
//        KitchenMeal dessert = factory.getDessert(FoodType.ICE_CREAM);
//
//        System.out.println(meal.getName());
//        System.out.println(dessert.getName());
    }
}
