package taps11;

import java.util.Random;

public class PizzaDelivery {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        PizzaOrder[] orders = new PizzaOrder[5];
        for (int i = 0; i < 5; i++) {
            orders[i] = new PizzaOrder(i + 1);
            orders[i].start();
        }

        for (PizzaOrder order : orders) {
            order.join();
        }

        PizzaOrder fastest = orders[0];
        for (PizzaOrder o : orders) {
            if (o.getTotalTime() < fastest.getTotalTime()) fastest = o;
        }

        long total = (System.currentTimeMillis() - start) / 1000;

        synchronized (System.out) {
            System.out.println("\n=======================================");
            System.out.println("All orders completed!");
            System.out.println("Total simulation time: " + total + " seconds");
            System.out.println("Fastest order: Order #" + fastest.getIdNumber()
                    + " → " + fastest.getTotalTime() + " sec");
            System.out.println("=======================================");
        }
    }
}

class PizzaOrder extends Thread {

    private final int id;
    private long totalTime;
    private static final Random RAND = new Random();

    public PizzaOrder(int id) {
        this.id = id;
    }

    public int getIdNumber() {
        return id;
    }

    public long getTotalTime() {
        return totalTime;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();

        log("Started.");
        sleepRandom("Preparing pizza...", "Preparation complete.", 1, 3);
        sleepRandom("Baking pizza...", "Baking complete.", 3, 6);
        sleepRandom("Delivering pizza to customer...", "Pizza delivered!", 2, 5);

        totalTime = (System.currentTimeMillis() - start) / 1000;

        synchronized (System.out) {
            System.out.println("[Order #" + id + "] Total time: " + totalTime + " seconds\n");
        }
    }

    private void sleepRandom(String startMsg, String endMsg, int min, int max) {
        log(startMsg);
        try {
            Thread.sleep((RAND.nextInt(max - min + 1) + min) * 1000L);
        } catch (InterruptedException ignored) {}
        log(endMsg);
    }

    private void log(String msg) {
        synchronized (System.out) {
            System.out.println("[Order #" + id + "] " + msg);
        }
    }
}
