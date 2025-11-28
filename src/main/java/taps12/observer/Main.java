package taps12.observer;

import java.util.ArrayList;
import java.util.List;


interface Subscriber {
    void update(String news);
}


class SubscriberThread extends Thread implements Subscriber {
    private final NewsPublisher publisher;
    private String latestNews;

    public SubscriberThread(String name, NewsPublisher publisher) {
        super(name);
        this.publisher = publisher;
    }

    @Override
    public void update(String news) {
        latestNews = news;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (publisher) {
                try {
                    publisher.wait();
                    update(publisher.getLatestNews());
                    System.out.println(getName() + " received: " + latestNews);
                } catch (InterruptedException e) {
                    System.out.println(getName() + " stopped.");
                    break;
                }
            }
        }
    }
}


class NewsPublisher {
    private final List<Subscriber> subscribers = new ArrayList<>();
    private String latestNews;

    public synchronized void subscribe(Subscriber sub) {
        subscribers.add(sub);
    }

    public synchronized void publishNews(String news) {
        latestNews = news;
        notifyAll();
    }

    public synchronized String getLatestNews() {
        return latestNews;
    }
}


public class Main {
    public static void main(String[] args) throws InterruptedException {
        NewsPublisher publisher = new NewsPublisher();


        SubscriberThread sub1 = new SubscriberThread("Alice", publisher);
        SubscriberThread sub2 = new SubscriberThread("Bob", publisher);
        SubscriberThread sub3 = new SubscriberThread("Charlie", publisher);

        publisher.subscribe(sub1);
        publisher.subscribe(sub2);
        publisher.subscribe(sub3);

        sub1.start();
        sub2.start();
        sub3.start();

        int newsCount = 1;
        while (true) {
            String news = "News #" + newsCount++;
            synchronized (publisher) {
                publisher.publishNews(news);
                System.out.println("Published: " + news);
            }
            Thread.sleep(2000);
        }
    }
}
