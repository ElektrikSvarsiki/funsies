package taps5;

import java.util.Scanner;

import static taps5.TransportManager.getTransport;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Transport transport = null;

        while (transport == null) {
            System.out.print("Enter transport type (BUS, TAXI, BICYCLE, SCOOTER): ");
            try {
                TransportType type = TransportType.valueOf(sc.next().toUpperCase());
                transport = getTransport(type);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type. Try again.");
            }
        }

        System.out.print("Enter distance (km): ");
        double distance = sc.nextDouble();

        System.out.print("Enter passenger count: ");
        int passengers = sc.nextInt();


        System.out.println(transport.getTransportInfo());
        System.out.printf("Fare: %.2f%n", transport.calculateFare(distance, passengers));
        System.out.printf("Time: %.2f hours%n", transport.calculateTime(distance));
    }
}
