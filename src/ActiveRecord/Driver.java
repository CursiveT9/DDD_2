package ActiveRecord;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Driver {
    private final int id;
    private final String fullName;
    private boolean isActive;
    private final List<WorkShift> schedule = new ArrayList<>();

    public Driver(int id, String fullName, boolean isActive) {
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
        this.isActive = active;
    }

    public void addWorkShift(WorkShift shift) {
        for (WorkShift existingShift : schedule) {
            if (existingShift.overlapsWith(shift)) {
                throw new IllegalArgumentException("Ошибка: смена пересекается с существующей!");
            }
        }
        schedule.add(shift);
    }

    public List<WorkShift> getShiftsForDate(LocalDateTime date) {
        List<WorkShift> shiftsForDate = new ArrayList<>();
        for (WorkShift shift : schedule) {
            if (shift.isOnDate(date)) {
                shiftsForDate.add(shift);
            }
        }
        return shiftsForDate;
    }

    public boolean isAvailableAt(LocalDateTime dateTime) {
        for (WorkShift shift : schedule) {
            if (shift.isDuring(dateTime)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Driver{id=" + id + ", fullName='" + fullName + "', isActive=" + isActive + '}';
    }
}
