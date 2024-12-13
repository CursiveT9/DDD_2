package ActiveRecord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Паттерн Active Record для преподавателей
public class Instructor {
    private final int id;
    private final String fullName;
    private boolean isActive;
    private final List<ClassSession> schedule = new ArrayList<>();

    public Instructor(int id, String fullName, boolean isActive) {
        this.id = id;
        this.fullName = fullName;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void addClassSession(ClassSession session) {
        for (ClassSession s : schedule) {
            if (s.overlapsWith(session)) {
                throw new IllegalArgumentException("Занятие пересекается с существующим занятием.");
            }
        }
        schedule.add(session);
    }

    public List<ClassSession> getClassesForDate(LocalDateTime date) {
        List<ClassSession> classesForDate = new ArrayList<>();
        for (ClassSession s : schedule) {
            if (s.isOnDate(date)) {
                classesForDate.add(s);
            }
        }
        return classesForDate;
    }

    public boolean isAvailableAt(LocalDateTime dateTime) {
        for (ClassSession s : schedule) {
            if (s.isDuring(dateTime)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Instructor{id=" + id + ", fullName='" + fullName + '\'' + ", isActive=" + isActive + '}';
    }
}
