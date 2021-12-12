package weekday.util;

import weekday.model.MealConstant;

import java.util.List;
import java.util.Optional;

/**
 * Utility Class to calculate time functions
 */
public class DeliveryTimeUtil {

    /**
     *  deliveryTime is calculated with the following formula
     *  DeliveryTime = (8 * distance in km) + waitingTime to start particular order processing + MAX(TIME(A), TIME(M)) which is present in meals
     */
    public static double calculateDeliveryTime(double distance, double waitingTime, List<String> meals) {
        return (8 * distance) + waitingTime + getMealPreparationTime(meals);
    }

    private static double getMealPreparationTime(List<String> meals) {
        Optional<String> optionalMeal = meals.stream().filter(MealConstant.MEALS::equals).findAny();
        if (optionalMeal.isPresent()) {
            return 29;
        }
        return 17;
    }
}
