package Repository;

import Aggregate.TransportationOrder;

import java.util.HashMap;
import java.util.Map;

public class TransportationOrderRepository {
    private final Map<String, TransportationOrder> storage = new HashMap<>();

    public void save(TransportationOrder order) {
        storage.put(order.getId(), order);
        System.out.println("Заказ " + order.getId() + " сохранен.");
    }

    public TransportationOrder findById(String id) {
        return storage.get(id);
    }

    public void delete(String id) {
        storage.remove(id);
        System.out.println("Заказ " + id + " удален.");
    }
}