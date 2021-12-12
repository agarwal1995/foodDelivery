package weekday.service.impl;

import weekday.model.OrderRequest;
import weekday.model.OrderTimeline;
import weekday.service.OrderManagementService;
import weekday.service.InputService;
import weekday.service.OrderManagementUtilityService;
import weekday.util.DeliveryTimeUtil;

import java.util.*;

public class OrderManagementServiceImpl implements OrderManagementService {

    private static final int MAX_SLOTS = 7;
    private static int slotsFilled = 0;

    private final InputService inputService = new JsonStringInputServiceImpl();
    private final OrderManagementUtilityService utilityService = new OrderManagementUtilityServiceImpl(MAX_SLOTS);

    /**
     * @code {orderRequests} request that came to be fulfilled
     * @code {ordersInProcess} orders to which slots are assigned and is in processing state
     * @code {waitingRequests} orders which has to be put in a waiting queue
     * @return solution string
     */
    public String calculateDeliveryIntervals() {
        Queue<OrderTimeline> inProcessOrders = utilityService.getOrdersInProgressQueue();
        List<OrderRequest> orderRequests = inputService.read();
        Queue<OrderRequest> waitingRequests = new LinkedList<>();
        return String.valueOf(
                preprocess(orderRequests, inProcessOrders, waitingRequests)) +
                processValidOrders(inProcessOrders, waitingRequests);
    }


    /**
     * the method filters the orderRequests
     * @return string solution which contains all the orders which cannot be accommodated by the restaurant
     */
    private StringBuilder preprocess(List<OrderRequest> orderRequests, Queue<OrderTimeline> ordersInProcess, Queue<OrderRequest> waitingRequests) {
        StringBuilder solution = new StringBuilder();
        for (OrderRequest orderRequest : orderRequests) {
            int slotsRequired = utilityService.getNumberOfSlotsRequired(orderRequest);
            if (!utilityService.isValidOrderRequest(slotsRequired)) {
                solution.append("Order ").append(orderRequest.getOrderId()).append(" is denied because the restaurant cannot accommodate it.");
                solution.append("\n");
                continue;
            }
            if (utilityService.isSlotAvailable(slotsRequired, slotsFilled)) {
                slotsFilled = slotsFilled + slotsRequired;
                ordersInProcess.add(formOrderTimeline(orderRequest, 0, slotsRequired));
            } else {
                waitingRequests.add(orderRequest);
            }
        }
        return solution;
    }

    /**
     * processed all the orders which are in processing and waiting state
     * @return string solution
     */
    private StringBuilder processValidOrders(Queue<OrderTimeline> ordersInProcess, Queue<OrderRequest> waitingRequests) {
        StringBuilder solution = new StringBuilder();
        while (!ordersInProcess.isEmpty()) {
            OrderTimeline orderTimeline = ordersInProcess.poll();
            solution.append(orderTimeline.getOrderId()).append(" will get delivered in ").append(orderTimeline.getTimeToDelivered()).append(" minutes").append("\n");
            slotsFilled = slotsFilled - orderTimeline.getSlots();
            while (slotsFilled < MAX_SLOTS && !waitingRequests.isEmpty()) {
                OrderRequest toBeProcess = waitingRequests.peek();
                int slotsRequired = utilityService.getNumberOfSlotsRequired(toBeProcess);
                if (!utilityService.isSlotAvailable(slotsRequired, slotsFilled)) {
                    break;
                }
                ordersInProcess.add(formOrderTimeline(toBeProcess, orderTimeline.getTimeToDelivered() + orderTimeline.getTimeWaited(), slotsRequired));
                waitingRequests.poll();
                slotsFilled = slotsFilled + slotsRequired;
            }
        }
        return solution;
    }

    OrderTimeline formOrderTimeline(OrderRequest orderRequest, double waitingTime, int slotsRequired) {
        return new OrderTimeline(
                DeliveryTimeUtil.calculateDeliveryTime(orderRequest.getDistance(), waitingTime, orderRequest.getMeals()),
                orderRequest.getOrderId(),
                waitingTime,
                slotsRequired);
    }
}
