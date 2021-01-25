package springcourse.kursspringbbot2abstractfactory;

import springcourse.kursspringbbot2abstractfactory.meal.FoodType;
import springcourse.kursspringbbot2abstractfactory.meal.KitchenMeal;

public abstract class Kitchen {

    public abstract KitchenMeal getMeal(FoodType preferency);
    public abstract KitchenMeal getDessert(FoodType preferency);

}
