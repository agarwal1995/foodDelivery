package weekday;

import weekday.service.OrderManagementService;
import weekday.service.impl.OrderManagementServiceImpl;

public class App {

    public static void main(String[] args) {
        OrderManagementService orderManagementService = new OrderManagementServiceImpl();
        String solution = orderManagementService.calculateDeliveryIntervals();
        System.out.println(solution);
    }
}
