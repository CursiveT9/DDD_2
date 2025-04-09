import Aggregate.Attachment;
import Aggregate.Message;
import Aggregate.TransportationOrder;
import Repository.TransportationOrderRepository;
import Service.FraudDetectionService;
import Service.OrderLifecycleService;
import ValueObject.DeliveryRoute;
import Aggregate.Message.MessageSide;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Создание объекта-значения маршрута доставки
        DeliveryRoute route = new DeliveryRoute("Москва", "Санкт-Петербург", 700);

        // Создание нового заказа на перевозку
        TransportationOrder order = new TransportationOrder("ORDER-001", route);

        // Сохранение заказа в репозитории
        TransportationOrderRepository repository = new TransportationOrderRepository();
        repository.save(order);

        // Создание первого сообщения от клиента к водителю
        Message message1 = new Message("MSG-001", "Заказ принят. Ожидается подтверждение.", MessageSide.CLIENT, MessageSide.DRIVER);
        // Добавление вложения к сообщению
        Attachment attachment1 = new Attachment("ATT-001", "invoice.pdf", "application/pdf");
        message1.addAttachment(attachment1);
        // Добавление сообщения в заказ
        order.addMessage(message1);
        System.out.println("Сообщение добавлено: " + message1.getContent());

        // Подтверждение сообщения стороной водителя (только противоположная сторона может подтверждать)
        try {
            message1.confirmMessage(MessageSide.DRIVER);
            System.out.println("Сообщение подтверждено.");
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка подтверждения: " + e.getMessage());
        }

        // Создание доменных сервисов
        OrderLifecycleService lifecycleService = new OrderLifecycleService();
        FraudDetectionService fraudService = new FraudDetectionService();

        // Перевод заказа в состояние IN_PROGRESS
        lifecycleService.startOrder(order);

        // Добавление второго сообщения от водителя к клиенту
        Message message2 = new Message("MSG-002", "Водитель выехал.", MessageSide.DRIVER, MessageSide.CLIENT);
        order.addMessage(message2);
        System.out.println("Новое сообщение добавлено: " + message2.getContent());

        order.closeOrder();

        // Создание нового заказа на перевозку
        TransportationOrder order2 = new TransportationOrder("ORDER-002", route);

        // Сохранение заказа в репозитории
        repository.save(order2);

        Message message3 = new Message("MSG-003", "Маршрут составлен", MessageSide.CLIENT, MessageSide.DRIVER);
        Message message4 = new Message("MSG-004", "Водитель выехал.", MessageSide.DRIVER, MessageSide.CLIENT);
        Message message5 = new Message("MSG-005", "Заказ завершен.", MessageSide.CLIENT, MessageSide.DRIVER);
        Message message6 = new Message("MSG-006", "Я мошенник.", MessageSide.DRIVER, MessageSide.CLIENT);

        // Добавление сообщения в заказ
        order2.addMessage(message1);
        order2.addMessage(message2);
        order2.addMessage(message3);
        order2.addMessage(message4);
        order2.addMessage(message5);
        order2.addMessage(message6);

        // Проверка на мошенническую активность
        if (fraudService.isFraudulent(order2)) {
            System.out.println("Обнаружена подозрительная активность в заказе " + order2.getId() + ".");
        } else {
            System.out.println("Активность в заказе " + order2.getId() + " не вызывает подозрений.");
        }

        TransportationOrder order3 = new TransportationOrder("ORDER-002", route);

        // Сохранение заказа в репозитории
        repository.save(order3);

        // Симуляция отсутствия активности для автоматического закрытия заказа.
        // Устанавливаем пороговое время, например, на 10 минут раньше текущего времени.
        LocalDateTime thresholdTime = LocalDateTime.now().minusMinutes(10);
        lifecycleService.autoCloseOrder(order3, thresholdTime);

        // Вывод финального статуса заказа
        System.out.println("Финальный статус заказа " + order3.getId() + ": " + order3.getStatus().toString());
    }
}
