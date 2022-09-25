package austindurr.fsehelper.aircraft;

public class CessnaCitationX implements Aircraft {
    public String flightModel = "Cessna%20Citation%20X";
    public String legibleName = "Cessna Citation X";

    @Override
    public String getFlightModel() {
        return flightModel;
    }

    @Override
    public String getLegibleName() {
        return legibleName;
    }

    @Override
    public double timeFlown(int hours,int minutes){
        return (.8*(hours*60*60)+(minutes*60));
    }
    @Override
    public double fuelSides(int initial,int used){
        return ((initial-(used*.8))*.5381);
    }
    @Override
    public double fuelCenter(int initial,int used){
        return ((initial - (used * .8)) * .4618);
    }
}
