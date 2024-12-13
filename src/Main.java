// Пример для новой предметной области: управление расписанием занятий и курсами

import ActiveRecord.ClassSession;
import ActiveRecord.Instructor;
import ActiveRecord.InstructorFactory;
import ActiveRecord.InstructorRepository;
import DomainModel.Course;
import DomainModel.Module;
import DomainModel.CourseManagementService;
import TransactionScript.ScheduleTransactionScript;


import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Создание фабрики и репозитория
        InstructorRepository repository = new InstructorRepository();
        Instructor instructor = InstructorFactory.createInstructor("John Doe", true);
        repository.save(instructor);

        // Создание транзакционного скрипта
        ScheduleTransactionScript transactionScript = new ScheduleTransactionScript(repository);

        // Добавление занятий
        transactionScript.addClassToInstructor(instructor.getId(), LocalDateTime.of(2023, 12, 14, 10, 0), LocalDateTime.of(2023, 12, 14, 12, 0));
        transactionScript.addClassToInstructor(instructor.getId(), LocalDateTime.of(2023, 12, 15, 14, 0), LocalDateTime.of(2023, 12, 15, 16, 0));

        // Проверка расписания
        List<ClassSession> sessions = transactionScript.getClassesForInstructorOnDate(instructor.getId(), LocalDateTime.of(2023, 12, 14, 0, 0));
        System.out.println("Занятия на 2023-12-14: " + sessions);

        // Проверка доступности
        boolean isAvailable = transactionScript.checkInstructorAvailability(instructor.getId(), LocalDateTime.of(2023, 12, 14, 11, 0));
        System.out.println("Доступность на 2023-12-14 11:00: " + isAvailable);

        // Управление курсами
        Course course = new Course(1, "Programming 101");
        Module module = new Module("Introduction to Java", 10);
        CourseManagementService courseService = new CourseManagementService();
        courseService.enrollInstructorToCourse(instructor, course);
        courseService.assignModuleToCourse(module, course);

        System.out.println("Курс: " + course);
    }
}
