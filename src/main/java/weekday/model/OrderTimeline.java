package weekday.model;

public class OrderTimeline {
    private double timeToDelivered;
    private String orderId;
    private double timeWaited;
    private int slots;

    public OrderTimeline(double timeToDelivered, String orderId, double timeWaited, int slots) {
        this.timeToDelivered = timeToDelivered;
        this.orderId = orderId;
        this.timeWaited = timeWaited;
        this.slots = slots;
    }

    public double getTimeToDelivered() {
        return timeToDelivered;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getTimeWaited() {
        return timeWaited;
    }

    public int getSlots() {
        return slots;
    }
}
