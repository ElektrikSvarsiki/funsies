package taps5;

public abstract class Transport {
        private final TransportType type;

        protected Transport(TransportType type) {
            this.type = type;
        }

        public TransportType getType() { return type; }
        public double getRatePerKm() { return type.getRatePerKm(); }
        public double getSpeed() { return type.getSpeed(); }
        public String getTransportInfo() { return type.getInfo(); }

        public abstract double calculateFare(double distance);
        public abstract double calculateFare(double distance, int passengers);
        public abstract double calculateTime(double distance);
    }


