package weekday.service.impl;

import weekday.model.MealConstant;
import weekday.model.OrderRequest;
import weekday.model.OrderTimeline;
import weekday.service.OrderManagementUtilityService;

import java.util.PriorityQueue;
import java.util.Queue;

public class OrderManagementUtilityServiceImpl implements OrderManagementUtilityService {

    private final int MAX_SLOTS;
    public OrderManagementUtilityServiceImpl(int max) {
        MAX_SLOTS = max;
    }

    public boolean isValidOrderRequest(int slotsRequired) {
        return slotsRequired <= MAX_SLOTS;
    }

    public int getNumberOfSlotsRequired(OrderRequest orderRequest) {
        int slotsRequired = 0;
        for (String m : orderRequest.getMeals()) {
            slotsRequired += getSlotForMeal(m);
        }
        return slotsRequired;
    }

    public boolean isSlotAvailable(int slotRequired, int slotsFilled) {
        return slotsFilled + slotRequired <= MAX_SLOTS;
    }

    public Queue<OrderTimeline> getOrdersInProgressQueue() {
        return new PriorityQueue<>((o1, o2) -> (int) (o1.getTimeToDelivered() - o2.getTimeToDelivered()));
    }

    public int getSlotForMeal(String meal) {
        switch (meal) {
            case MealConstant.APPETIZER:
                return 1;
            case MealConstant.MEALS:
                return 2;
            default:
                return 0;
        }
    }
}
