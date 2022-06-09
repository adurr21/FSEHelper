package fseconomytool;

import fseconomytool.aircraft.Aircraft;
import fseconomytool.aircraft.CessnaCaravan;
import fseconomytool.aircraft.CessnaCitationX;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.text.DecimalFormat;

public class WebRequest {
    HttpRequest webRequest;
    HttpClient webClient = HttpClient.newHttpClient();

    DecimalFormat time = new DecimalFormat("0.#");

    boolean validIcao(String icao){
        FileParse database = new FileParse();
        if (database.retrieveLat(icao)==-1||database.retrieveLon(icao)==-1){
            return false;
        }
        return true;
    }

    void startFlight(Aircraft aircraft, Config config, String icao, String md5){
        if (!validIcao(icao)){
            System.out.println("ICAO not found in database. Web request terminated.");
            return;
        }
        FileParse database = new FileParse();
        webRequest = HttpRequest.newBuilder(
                        URI.create("https://server.fseconomy.net/fsagentx?md5sum="+md5+"&user="+config.getUsername()+"&pass="+config.getPassword()+"&action=startFlight&lat="+database.retrieveLat(icao)+"&lon="+database.retrieveLon(icao)+"&aircraft="+aircraft.getFlightModel()))
                .build();
        try {
            HttpResponse response = webClient.send(webRequest,HttpResponse.BodyHandlers.ofString());
            System.out.println("Web response: "+response.body());
        } catch (IOException e){
            System.out.println(e);
        } catch (InterruptedException e){
            System.out.println(e);
        }
    }
    void endFlight(Aircraft aircraft, Config config, String icao, int initialFuel, int usedFuel, int hours, int minutes, String md5){
        if (!validIcao(icao)){
            System.out.println("ICAO not found in database. Web request terminated.");
            return;
        }
        FileParse database = new FileParse();
        if (aircraft instanceof CessnaCitationX){
            webRequest = HttpRequest.newBuilder(
                            URI.create("https://server.fseconomy.net/fsagentx?md5sum="+md5+"&user="+config.getUsername()+"&pass="+config.getPassword()+"&action=arrive&rentalTime="+time.format(aircraft.timeFlown(hours, minutes))+"&lat="+database.retrieveLat(icao)+"&lon="+database.retrieveLon(icao)+"&c="+aircraft.fuelCenter(initialFuel,usedFuel)+"&lm="+aircraft.fuelSides(initialFuel,usedFuel)+"&la=0&let=0&rm="+aircraft.fuelSides(initialFuel,usedFuel)+"&ra=0&rt=0&c2=0&c3=0&x1=0&x2=0&mixture1=0&heat1=221.452322&time1="+time.format(aircraft.timeFlown(hours, minutes))))
                    .build();
        } else if (aircraft instanceof CessnaCaravan){
            webRequest = HttpRequest.newBuilder(
                            URI.create("https://server.fseconomy.net/fsagentx?md5sum="+md5+"&user="+config.getUsername()+"&pass="+config.getPassword()+"&action=arrive&rentalTime="+time.format(aircraft.timeFlown(hours, minutes))+"&lat="+database.retrieveLat(icao)+"&lon="+database.retrieveLon(icao)+"&c=0"+"&lm="+aircraft.fuelSides(initialFuel,usedFuel)+"&la=0&let=0&rm="+aircraft.fuelSides(initialFuel,usedFuel)+"&ra=0&rt=0&c2=0&c3=0&x1=0&x2=0&mixture1=0&heat1=221.452322&time1="+time.format(aircraft.timeFlown(hours, minutes))))
                    .build();
        }

        try {
            HttpResponse response = webClient.send(webRequest,HttpResponse.BodyHandlers.ofString());
            System.out.println("Web response: "+response.body());
        } catch (IOException e){
            System.out.println(e);
        } catch (InterruptedException e){
            System.out.println(e);
        }
    }

}
