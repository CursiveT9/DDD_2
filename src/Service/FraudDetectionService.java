package Service;

import Aggregate.TransportationOrder;

/**
 * Доменный сервис FraudDetectionService выполняет простейший анализ для обнаружения мошеннической активности.
 */
public class FraudDetectionService {

    /**
     * Простейшая проверка: если заказ содержит более 5 сообщений, считаем его подозрительным.
     */
    public boolean isFraudulent(TransportationOrder order) {
        if(order.getMessages().size() > 5) {
            System.out.println("Заказ " + order.getId() + " признан подозрительным на основании количества сообщений.");
            return true;
        }
        return false;
    }
}