package ActiveRecord;

// Фабрика для создания объектов
public class InstructorFactory {
    private static int idCounter = 1;

    public static Instructor createInstructor(String fullName, boolean isActive) {
        return new Instructor(idCounter++, fullName, isActive);
    }
}
