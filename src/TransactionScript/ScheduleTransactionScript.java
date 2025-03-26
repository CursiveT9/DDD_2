package TransactionScript;

import ActiveRecord.Driver;
import ActiveRecord.WorkShift;
import ActiveRecord.DriverRepository;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleTransactionScript {
    private final DriverRepository repository;

    public ScheduleTransactionScript(DriverRepository repository) {
        this.repository = repository;
    }

    public void addWorkShiftToDriver(int driverId, LocalDateTime start, LocalDateTime end) {
        Driver driver = repository.findById(driverId);
        if (driver == null) {
            throw new IllegalArgumentException("Ошибка: Водитель с ID " + driverId + " не найден.");
        }
        if (!driver.isActive()) {
            throw new IllegalStateException("Ошибка: Водитель неактивен!");
        }

        try {
            System.out.println("Начало транзакции: добавление смены.");
            driver.addWorkShift(new WorkShift(start, end));
            repository.save(driver);
            System.out.println("Смена успешно добавлена.");
        } catch (Exception e) {
            System.err.println("Ошибка транзакции: " + e.getMessage());
        }
    }

    public List<WorkShift> getShiftsForDriverOnDate(int driverId, LocalDateTime date) {
        Driver driver = repository.findById(driverId);
        if (driver == null) {
            throw new IllegalArgumentException("Ошибка: Водитель с ID " + driverId + " не найден.");
        }
        return driver.getShiftsForDate(date);
    }

    public boolean checkDriverAvailability(int driverId, LocalDateTime dateTime) {
        Driver driver = repository.findById(driverId);
        if (driver == null) {
            throw new IllegalArgumentException("Ошибка: Водитель с ID " + driverId + " не найден.");
        }
        return driver.isAvailableAt(dateTime);
    }
}
