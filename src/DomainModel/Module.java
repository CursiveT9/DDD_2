package DomainModel;

public class Module {
    private final String title;
    private final int durationInHours;

    public Module(String title, int durationInHours) {
        this.title = title;
        this.durationInHours = durationInHours;
    }

    @Override
    public String toString() {
        return "Module{title='" + title + '\'' + ", durationInHours=" + durationInHours + '}';
    }
}
