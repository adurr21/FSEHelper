package austindurr.fsehelper.aircraft;

public class CessnaCaravan implements Aircraft {
    public String flightModel = "Cessna%20208%20Caravan";
    public String legibleName = "Cessna 208 Caravan";

    @Override
    public String getFlightModel() {
        return flightModel;
    }

    @Override
    public String getLegibleName() {
        return legibleName;
    }

    @Override
    public double timeFlown(int hours, int minutes) {
        return (.8*(hours*60*60)+(minutes*60));
    }

    @Override
    //Cessna 208 does not have a center fuel tank
    public double fuelCenter(int initial, int used) {
        return -1;
    }

    @Override
    public double fuelSides(int initial, int used) {
        return (initial-(used*.9))/2;
    }
}
