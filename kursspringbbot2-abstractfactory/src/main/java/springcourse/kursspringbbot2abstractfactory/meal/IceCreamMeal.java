package springcourse.kursspringbbot2abstractfactory.meal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@TypeOfFood(foodType = FoodType.ICE_CREAM)
public class IceCreamMeal extends KitchenMeal {
    @Override
    public String getName() {
        return "ice-cream meal";
    }
}
