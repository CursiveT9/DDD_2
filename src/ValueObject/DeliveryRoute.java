package ValueObject;

/**
 * Объект-значение DeliveryRoute представляет маршрут доставки.
 * Содержит начальное местоположение, пункт назначения и расстояние в километрах.
 */
public final class DeliveryRoute {
    private final String startLocation;
    private final String destination;
    private final double distance; // Расстояние в километрах

    public DeliveryRoute(String startLocation, String destination, double distance) {
        if(startLocation == null || destination == null) {
            throw new IllegalArgumentException("Начальная и конечная точки не могут быть null.");
        }
        if(distance <= 0) {
            throw new IllegalArgumentException("Расстояние должно быть положительным.");
        }
        this.startLocation = startLocation;
        this.destination = destination;
        this.distance = distance;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getDestination() {
        return destination;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DeliveryRoute)) return false;
        DeliveryRoute other = (DeliveryRoute) obj;
        return startLocation.equals(other.startLocation) &&
                destination.equals(other.destination) &&
                Double.compare(distance, other.distance) == 0;
    }

    @Override
    public String toString() {
        return "DeliveryRoute{" +
                "startLocation='" + startLocation + '\'' +
                ", destination='" + destination + '\'' +
                ", distance=" + distance +
                '}';
    }
}