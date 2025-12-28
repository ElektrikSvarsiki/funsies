package taps5;

public class Bus extends Transport {
    public Bus() { super(TransportType.BUS); }

    @Override
    public double calculateFare(double distance) {
        return 0.6;
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
