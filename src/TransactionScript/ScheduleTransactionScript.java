package TransactionScript;

import ActiveRecord.ClassSession;
import ActiveRecord.Instructor;
import ActiveRecord.InstructorRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleTransactionScript {
    private final InstructorRepository repository;

    public ScheduleTransactionScript(InstructorRepository repository) {
        this.repository = repository;
    }

    public void addClassToInstructor(int instructorId, LocalDateTime start, LocalDateTime end) {
        Instructor instructor = repository.findById(instructorId);
        if (instructor == null) {
            throw new IllegalArgumentException("Преподаватель с ID " + instructorId + " не найден.");
        }
        try {
            System.out.println("Начало транзакции добавления занятия.");
            instructor.addClassSession(new ClassSession(start, end));
            repository.save(instructor);
            System.out.println("Транзакция успешно завершена: Занятие добавлено.");
        } catch (Exception e) {
            System.err.println("Ошибка транзакции: " + e.getMessage());
        }
    }

    public List<ClassSession> getClassesForInstructorOnDate(int instructorId, LocalDateTime date) {
        Instructor instructor = repository.findById(instructorId);
        if (instructor == null) {
            throw new IllegalArgumentException("Преподаватель с ID " + instructorId + " не найден.");
        }
        System.out.println("Получение списка занятий для инструктора на дату: " + date.toLocalDate());
        return instructor.getClassesForDate(date);
    }

    public boolean checkInstructorAvailability(int instructorId, LocalDateTime dateTime) {
        Instructor instructor = repository.findById(instructorId);
        if (instructor == null) {
            throw new IllegalArgumentException("Преподаватель с ID " + instructorId + " не найден.");
        }
        System.out.println("Проверка доступности инструктора на момент времени: " + dateTime);
        return instructor.isAvailableAt(dateTime);
    }
}
