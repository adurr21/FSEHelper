package austindurr.fsehelper.aircraft;

public interface Aircraft {

    public double timeFlown(int hours, int minutes);
    public double fuelSides(int initial, int used);
    public double fuelCenter(int initial, int used);
    public String getLegibleName();
    public String getFlightModel();
}
