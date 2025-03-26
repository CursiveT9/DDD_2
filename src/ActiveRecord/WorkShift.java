package ActiveRecord;

import java.time.LocalDateTime;

public class WorkShift {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public WorkShift(LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("Ошибка: Время окончания смены не может быть раньше начала!");
        }
        this.start = start;
        this.end = end;
    }

    public boolean overlapsWith(WorkShift other) {
        return !this.end.isBefore(other.start) && !this.start.isAfter(other.end);
    }

    public boolean isOnDate(LocalDateTime date) {
        return start.toLocalDate().equals(date.toLocalDate());
    }

    public boolean isDuring(LocalDateTime dateTime) {
        return !dateTime.isBefore(start) && !dateTime.isAfter(end);
    }

    @Override
    public String toString() {
        return "WorkShift{start=" + start + ", end=" + end + '}';
    }
}
