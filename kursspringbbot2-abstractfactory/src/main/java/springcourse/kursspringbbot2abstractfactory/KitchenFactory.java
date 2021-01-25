package springcourse.kursspringbbot2abstractfactory;

import springcourse.kursspringbbot2abstractfactory.meal.*;

public class KitchenFactory extends Kitchen{

    @Override
    public KitchenMeal getMeal(FoodType preferency) {
        if (preferency == FoodType.FAST_FOOD){
            return new FastFoodMeal();
        }
        return new VegetarianMeal();
    }

    @Override
    public KitchenMeal getDessert(FoodType preferency) {
        if (preferency == FoodType.ICE_CREAM){
            return new IceCreamMeal();
        }
        return null;
    }
}
