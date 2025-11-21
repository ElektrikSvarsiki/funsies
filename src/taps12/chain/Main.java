package taps12.chain;

abstract class Handler {
    private Handler next;
    public Handler setNext(Handler next) { this.next = next; return next; }
    public void handle(String request) {
        if (!process(request) && next != null) next.handle(request);
    }
    protected abstract boolean process(String request);
}

class LowercaseHandler extends Handler {
    protected boolean process(String request) {
        if (request.equals(request.toLowerCase())) {
            System.out.println("LowercaseHandler handled it: " + request);
            return true;
        }
        return false;
    }
}

class LengthHandler extends Handler {
    protected boolean process(String request) {
        if (request.length() < 5) {
            System.out.println("LengthHandler handled it: " + request);
            return true;
        }
        return false;
    }
}

class DefaultHandler extends Handler {
    protected boolean process(String request) {
        System.out.println("DefaultHandler handled it: " + request);
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Handler h1 = new LowercaseHandler();
        Handler h2 = new LengthHandler();
        Handler h3 = new DefaultHandler();

        h1.setNext(h2).setNext(h3);

        h1.handle("hello");
        h1.handle("Hi");
        h1.handle("WORLD");
    }
}
