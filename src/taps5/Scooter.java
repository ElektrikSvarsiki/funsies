package taps5;

public class Scooter extends Transport {
    public Scooter() { super(TransportType.SCOOTER); }

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
        return distance / getSpeed();
    }
}
