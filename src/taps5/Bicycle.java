package taps5;

public class Bicycle extends Transport {
    public Bicycle() { super(TransportType.BICYCLE); }

    @Override
    public double calculateFare(double distance) {
        return distance * getRatePerKm();
    }

    @Override
    public double calculateFare(double distance, int passengers) {
        return calculateFare(distance) * passengers;
    }

    @Override
    public double calculateTime(double distance) {
        return (distance / getSpeed());
    }
}


