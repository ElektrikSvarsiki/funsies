package taps5;

public class Taxi extends Transport {
    public Taxi() { super(TransportType.TAXI); }

    @Override
    public double calculateFare(double distance) {
        double baseFee = 3.0;
        return baseFee + distance * getRatePerKm();
    }

    @Override
    public double calculateFare(double distance, int passengers) {
        return calculateFare(distance); // no discount, same fare regardless
    }

    @Override
    public double calculateTime(double distance) {
        return distance / getSpeed();
    }
}
