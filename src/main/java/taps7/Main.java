package taps7;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static taps7.BusRouteSimulation.*;

public class Main {
    public static void main(String[] args) {
        List<Queue<Passenger>> stops = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            stops.add(generateStop(i));
        }

        List<Passenger> bus = new ArrayList<>();

        for (int stopIndex = 0; stopIndex < stops.size(); stopIndex++) {
            System.out.println("\n--- Arrived at Stop " + (stopIndex + 1) + " ---");

            Queue<Passenger> currentStop = stops.get(stopIndex);


            passengersGetOff(bus);


            passengersGetOn(bus, currentStop);

            System.out.println("Bus now: " + bus);
            System.out.println("Remaining at stop: " + currentStop);
        }


        System.out.println("Bus passengers: " + bus);
        for (int i = 0; i < stops.size(); i++) {
            System.out.println("Stop " + (i + 1) + " remaining: " + stops.get(i));
        }
    }
}
