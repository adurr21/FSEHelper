package austindurr.fsehelper;

import austindurr.fsehelper.aircraft.Aircraft;
import austindurr.fsehelper.aircraft.CessnaCaravan;
import austindurr.fsehelper.aircraft.CessnaCitationX;

import java.util.Scanner;

public class Menu {
    WebRequest requests = new WebRequest();
    Menu(Config config,String md5){
        Aircraft aircraft = new CessnaCitationX();
        Scanner scanner = new Scanner(System.in);
        String choice = null;
        boolean exit=false;
        do {
            System.out.println("Please enter 's' to start flight, 'e' to end flight, 'c' to change flight model, or 'q' to quit.");
            System.out.println("Currently selected flight model: "+aircraft.getLegibleName());
            System.out.println();
            choice=scanner.nextLine();
            switch (choice){
                case "s":
                    System.out.println("Enter starting ICAO:");
                    String startIcao = scanner.nextLine();
                    requests.startFlight(aircraft,config,startIcao,md5);
                    System.out.println();
                    break;
                case "e":
                    System.out.println("Enter total inital fuel (gallons):");
                    int initFuel = scanner.nextInt();
                    System.out.println("Please enter the total fuel used:");
                    int usedFuel = scanner.nextInt();
                    System.out.println("Enter hours flown:");
                    int hours = scanner.nextInt();
                    System.out.println("Enter minutes flown:");
                    int minutes = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter ending airport:");
                    String endIcao = scanner.nextLine();
                    requests.endFlight(aircraft,config,endIcao,initFuel,usedFuel,hours,minutes,md5);
                    System.out.println();
                    break;
                case "c":
                    System.out.println("Enter 'a' to choose Cessna Citation X, or 'b' to choose Cessna 208 Caravan");
                    String planeChoice = scanner.nextLine();
                    switch (planeChoice){
                        case "a":
                            aircraft=new CessnaCitationX();
                            break;
                        case "b":
                            aircraft=new CessnaCaravan();
                            break;
                    }
                    System.out.println();
                    break;
                case "q":
                    exit=true;
                    System.out.println("Exiting...");
                    break;
            }

        } while(!exit);
    }
}
