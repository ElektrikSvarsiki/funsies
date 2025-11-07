package taps8;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class CarRentalSystem {
    private Set<Car> allCars = new HashSet<>();
    private Set<Car> availableCars = new HashSet<>();
    private Map<Customer, Car> activeRentals = new HashMap<>();
    private Map<Car, LocalDateTime> rentalTimes = new HashMap<>();
    private Map<Car, String> rentalHistory = new LinkedHashMap<>();

    public void addCar(Car car) {
        allCars.add(car);
        availableCars.add(car);
    }

    public void rentCar(Customer c, Car car) {
        if (!availableCars.contains(car)) {
            System.out.println("Car is not available!");
            return;
        }
        activeRentals.put(c, car);
        LocalDateTime start = LocalDateTime.now();
        rentalTimes.put(car, start);
        availableCars.remove(car);
        System.out.println(c.getName() + " rented " + car + " at " + start);
    }

    public void returnCar(Customer c) {
        Car car = activeRentals.remove(c);
        if (car == null) return;
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = rentalTimes.remove(car);
        availableCars.add(car);
        Duration duration = Duration.between(start, end);
        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        System.out.println(c.getName() + " returned " + car + " at " + end);
        System.out.println("This car was rented for " + days + " days " + hours + " hours.");
        rentalHistory.put(car, "Rented at " + start + " returned at " + end);
    }

    public void printActiveRentals() {
        if (activeRentals.isEmpty()) {
            System.out.println("No active rentals.");
            return;
        }
        for (Map.Entry<Customer, Car> entry : activeRentals.entrySet()) {
            System.out.println(entry.getKey().getName() + " -> " + entry.getValue() + " since " + rentalTimes.get(entry.getValue()));
        }
    }

    public void printAvailableCars() {
        if (availableCars.isEmpty()) {
            System.out.println("No available cars.");
            return;
        }
        for (Car car : availableCars) System.out.println(car);
    }

    public void printRentalHistory() {
        if (rentalHistory.isEmpty()) {
            System.out.println("No rental history.");
            return;
        }
        for (Map.Entry<Car, String> e : rentalHistory.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }


}
