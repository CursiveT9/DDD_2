package DomainModel;

import ActiveRecord.Instructor;

public class CourseManagementService {
    public void enrollInstructorToCourse(Instructor instructor, Course course) {
        if (!instructor.isActive()) {
            throw new IllegalStateException("Нельзя записать неактивного преподавателя.");
        }
        System.out.println("Преподаватель " + instructor.getFullName() + " записан на курс " + course);
    }

    public void assignModuleToCourse(Module module, Course course) {
        course.addModule(module);
        System.out.println("Модуль добавлен в курс: " + module);
    }
}
