package ValueObject;

public final class OrderStatus {
    public enum Status {
        OPEN, IN_PROGRESS, CLOSED, CANCELLED
    }

    private final Status status;

    public OrderStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Статус не может быть null");
        }
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof OrderStatus)) return false;
        OrderStatus other = (OrderStatus) obj;
        return this.status.equals(other.status);
    }

    @Override
    public String toString() {
        return "OrderStatus{" +
                "status=" + status +
                '}';
    }
}
