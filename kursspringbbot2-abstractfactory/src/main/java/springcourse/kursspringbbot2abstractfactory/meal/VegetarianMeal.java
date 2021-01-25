package springcourse.kursspringbbot2abstractfactory.meal;

import org.springframework.stereotype.Component;

@Component
@TypeOfFood(foodType = FoodType.VEGETARIAN)
public class VegetarianMeal extends KitchenMeal {

    @Override
    public String getName(){
        return "vegetarian meal";
    }
}
