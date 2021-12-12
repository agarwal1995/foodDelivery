package weekday.model;

import java.util.List;

public class OrderRequest {
    private String orderId;
    private List<String> meals;
    private double distance;

    public OrderRequest(String orderId, List<String> meals, double distance) {
        this.orderId = orderId;
        this.meals = meals;
        this.distance = distance;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<String> getMeals() {
        return meals;
    }

    public double getDistance() {
        return distance;
    }
}
