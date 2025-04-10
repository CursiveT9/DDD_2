package Service;

import Aggregate.TransportationOrder;

import java.time.LocalDateTime;

public class OrderLifecycleService {

    //    Перевод заказа из состояния OPEN в IN_PROGRESS.
    public void startOrder(TransportationOrder order) {
        order.startOrder();
        System.out.println("Заказ " + order.getId() + " переведен в состояние IN_PROGRESS.");
    }

    public void closeOrder(TransportationOrder order) {
        order.closeOrder();
        System.out.println("Заказ " + order.getId() + " закрыт.");
    }

    //Автоматическая проверка и закрытие заказа при отсутствии активности.
    public void autoCloseOrder(TransportationOrder order, LocalDateTime thresholdTime) {
        order.autoCloseIfInactive(thresholdTime);
        System.out.println("Проверено автоматическое закрытие заказа " + order.getId() + ".");
    }
}
