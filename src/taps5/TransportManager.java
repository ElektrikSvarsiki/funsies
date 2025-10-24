package taps5;


public class TransportManager {

        static Transport getTransport(TransportType type) {
            return switch (type) {
                case BUS -> new Bus();
                case TAXI -> new Taxi();
                case BICYCLE -> new Bicycle();
                case SCOOTER -> new Scooter();
            };
        }


    }


