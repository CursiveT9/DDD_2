package ActiveRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Репозиторий для хранения преподавателей
public class InstructorRepository {
    private final Map<Integer, Instructor> storage = new HashMap<>();

    public void save(Instructor instructor) {
        storage.put(instructor.getId(), instructor);
        System.out.println("Преподаватель сохранен: " + instructor);
    }

    public Instructor findById(int id) {
        return storage.get(id);
    }

    public List<Instructor> findAll() {
        return new ArrayList<>(storage.values());
    }
}
