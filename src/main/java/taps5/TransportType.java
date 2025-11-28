package taps5;

public enum TransportType {
        BUS(0.5, 40, "Cheap, slower"),
        TAXI(2.0, 80, "Fast"),
        BICYCLE(0.1, 15, "eco friendly, fun"),
        SCOOTER(0.3, 25, "faster than bicycle");

        private final double ratePerKm;
        private final double speed;
        private final String info;

        TransportType(double ratePerKm, double speed, String info) {
            this.ratePerKm = ratePerKm;
            this.speed = speed;
            this.info = info;
        }

        public double getRatePerKm() { return ratePerKm; }
        public double getSpeed() { return speed; }
        public String getInfo() { return info; }
    }


