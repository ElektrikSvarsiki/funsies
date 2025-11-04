package taps7;

import java.util.*;

class Passenger {
    String name;
    boolean isPriority;

    Passenger(String name, boolean isPriority) {
        this.name = name;
        this.isPriority = isPriority;
    }

    @Override
    public String toString() {
        return name + (isPriority ? " (P)" : "");
    }
}

public class BusRouteSimulation {
    private static final int MAX_STOP_QUEUE = 10;
    private static final int BUS_CAPACITY = 5;
    private static final Random random = new Random();



    static Queue<Passenger> generateStop(int stopNumber) {
        Queue<Passenger> queue = new LinkedList<>();
        int passengerCount = random.nextInt(MAX_STOP_QUEUE + 1);

        for (int i = 0; i < passengerCount; i++) {
            boolean isPriority = random.nextBoolean();
            queue.add(new Passenger("S" + stopNumber + "_P" + (i + 1), isPriority));
        }
        return queue;
    }

    static void passengersGetOff(List<Passenger> bus) {
        if (bus.isEmpty()) return;

        int toGetOff = 1 + random.nextInt(2);
        toGetOff = Math.min(toGetOff, bus.size());

        System.out.println(toGetOff + " passenger(s) getting off");

        for (int i = 0; i < toGetOff; i++) {
            int index = random.nextInt(bus.size());
            Passenger p = bus.remove(index);
            System.out.println(p.name + " got off.");
        }
    }

    static void passengersGetOn(List<Passenger> bus, Queue<Passenger> stopQueue) {
        if (stopQueue.isEmpty()) return;

        List<Passenger> priority = new ArrayList<>();
        List<Passenger> normal = new ArrayList<>();

        for (Passenger p : stopQueue) {
            if (p.isPriority) priority.add(p);
            else normal.add(p);
        }

        stopQueue.clear();


        for (Passenger p : priority) {
            if (bus.size() < BUS_CAPACITY) bus.add(p);
            else stopQueue.add(p);
        }


        for (Passenger p : normal) {
            if (bus.size() < BUS_CAPACITY) bus.add(p);
            else stopQueue.add(p);
        }
    }
}

