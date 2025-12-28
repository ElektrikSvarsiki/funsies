package taps12.facade;

class Light {
    void on() { System.out.println("Light is on"); }
    void off() { System.out.println("Light is off"); }
}

class Fan {
    void start() { System.out.println("Fan started"); }
    void stop() { System.out.println("Fan stopped"); }
}

class AirConditioner {
    void start() { System.out.println("AC started"); }
    void stop() { System.out.println("AC stopped"); }
}

class HomeFacade {
    private final Light light;
    private final Fan fan;
    private final AirConditioner ac;

    public HomeFacade() {
        this.light = new Light();
        this.fan = new Fan();
        this.ac = new AirConditioner();
    }

    void leaveHome() {
        light.off();
        fan.stop();
        ac.stop();
    }

    void arriveHome() {
        light.on();
        fan.start();
        ac.start();
    }
}

public class Main {
    public static void main(String[] args) {
        HomeFacade home = new HomeFacade();
        home.arriveHome();
        home.leaveHome();
    }
}
