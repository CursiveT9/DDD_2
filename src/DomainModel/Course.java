package DomainModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Модель предметной области: управление курсами и учебным процессом
public class Course {
    private final int id;
    private final String name;
    private final List<Module> modules = new ArrayList<>();

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public void removeModule(Module module) {
        modules.remove(module);
    }

    public List<Module> getModules() {
        return Collections.unmodifiableList(modules);
    }

    @Override
    public String toString() {
        return "Course{id=" + id + ", name='" + name + '\'' + ", modules=" + modules + '}';
    }
}
