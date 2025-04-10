package Aggregate;

import ValueObject.DeliveryRoute;
import ValueObject.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransportationOrder {
    private final String id;  // Уникальный идентификатор заказа
    private OrderStatus status;
    private DeliveryRoute deliveryRoute;
    private final List<Message> messages;
    private LocalDateTime lastActivity; // Время последней активности (для автозакрытия)

    public TransportationOrder(String id, DeliveryRoute deliveryRoute) {
        this.id = id;
        this.deliveryRoute = deliveryRoute;
        this.status = new OrderStatus(OrderStatus.Status.OPEN); // Начальное состояние заказа – OPEN
        this.messages = new ArrayList<>();
        this.lastActivity = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public DeliveryRoute getDeliveryRoute() {
        return deliveryRoute;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    private void updateLastActivity() {
        lastActivity = LocalDateTime.now();
    }

    //    Добавление сообщения в заказ возможно только если заказ находится в состоянии OPEN.
    public void addMessage(Message message) {
        if (!(status.getStatus().equals(OrderStatus.Status.OPEN)
                || status.getStatus().equals(OrderStatus.Status.IN_PROGRESS))) {
            throw new IllegalStateException("Нельзя добавить сообщение в неактивную заявку.");
        }
        messages.add(message);
        updateLastActivity();
    }

    //    Перевод заказа в состояние IN_PROGRESS. Это действие возможно только из состояния OPEN.
    public void startOrder() {
        if (!status.getStatus().equals(OrderStatus.Status.OPEN)) {
            throw new IllegalStateException("Заказ не может быть начат, т.к. он не в состоянии OPEN.");
        }
        this.status = new OrderStatus(OrderStatus.Status.IN_PROGRESS);
        updateLastActivity();
    }

    public void closeOrder() {
        this.status = new OrderStatus(OrderStatus.Status.CLOSED);
    }

    public void autoCloseIfInactive(LocalDateTime thresholdTime) {
        // Проверяем, если время последней активности меньше порогового времени
        if (!lastActivity.isBefore(thresholdTime) && (status.getStatus().equals(OrderStatus.Status.OPEN))) {
            closeOrder();
        }
    }
}