package austindurr.fsehelper;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

class Airport {
    protected String icao;
    protected float lat;
    protected float lon;
    protected String name;
    protected String country;

    Airport(String icao,float lat,float lon, String name,String country){
        this.icao=icao;
        this.lat=lat;
        this.lon=lon;
        this.name=name;
        this.country=country;
    }
    @Override
    public String toString(){
        return icao+" "+lat+" "+lon+" "+name+" "+country;
    }
}
public class FileParse {
    //Only one object needed per program instance.
    //Reads in csv with all FSEConomy fseconomytool.aircraft.Airport Info and stores it into an Array of Object (type fseconomytool.aircraft.Airport).

    private static ArrayList<Airport> airports = new ArrayList<Airport>();
    public int arrayLength;

    FileParse(){}
    FileParse(String icaoDataPath){
        InputStream icaoData = this.getClass().getClassLoader().getResourceAsStream(icaoDataPath);
        try {
            Scanner fileScanner = new Scanner(icaoData);
            while (fileScanner.hasNextLine()){
                String[] currentLineArray = fileScanner.nextLine().split(",");
                airports.add(new Airport(currentLineArray[0],Float.parseFloat(currentLineArray[1]), Float.parseFloat(currentLineArray[2]), currentLineArray[3], currentLineArray[4]));
            }
        } catch (Exception e){
            System.out.println(e);
        }
        arrayLength = airports.size();
    }

    public double retrieveLat(String icao){
        for (Airport a : airports){
            if (a.icao.equals(icao.toUpperCase())){
                return a.lat;
            }
        }
        return -1;
    }
    public double retrieveLon(String icao){
        for (Airport a : airports){
            if (a.icao.equals(icao.toUpperCase())){
                return a.lon;
            }
        }
        return -1;
    }

    public ArrayList<Airport> airportArrayList(){
        return airports;
    }
}
