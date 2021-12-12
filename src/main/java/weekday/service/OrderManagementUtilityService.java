package weekday.service;

import weekday.model.OrderRequest;
import weekday.model.OrderTimeline;

import java.util.Queue;

/**
 * Utility Service used by services for simplification of the processes
 */
public interface OrderManagementUtilityService {

    boolean isValidOrderRequest(int slotsRequired);

    int getNumberOfSlotsRequired(OrderRequest orderRequest);

    boolean isSlotAvailable(int slotRequired, int slotsFilled);

    Queue<OrderTimeline> getOrdersInProgressQueue();

    int getSlotForMeal(String meal);
}
