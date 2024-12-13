package ActiveRecord;

import java.time.LocalDateTime;

public class ClassSession {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public ClassSession(LocalDateTime start, LocalDateTime end) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("Время окончания занятия не может быть раньше времени начала.");
        }
        this.start = start;
        this.end = end;
    }

    public boolean overlapsWith(ClassSession other) {
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
        return "ClassSession{start=" + start + ", end=" + end + '}';
    }
}
