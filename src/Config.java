package fseconomytool;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Config {

    private String username;
    private String password;

    private static boolean auth = false;

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    private void createNewConfig(){
        Scanner scanner = new Scanner(System.in);
        File userData = new File("userdata.durr");
        try {
            userData.createNewFile();
            System.out.println("Creating new user config file.");
            PrintWriter pw = new PrintWriter(userData);
            System.out.println();
            System.out.println("Enter FSEconomy Username: ");
            String username = scanner.nextLine();
            System.out.println("Enter FSEconomy Password: ");
            String password = scanner.nextLine();
            pw.println(username);
            pw.println(password);
            pw.flush();
            pw.close();
        } catch (Exception e){
            System.out.println(e);
        }
        readConfig();
    }
    private void readConfig(){
        File userData = new File("userdata.durr");
        try {
            Scanner scanner = new Scanner(userData);
            username=scanner.nextLine();
            password=scanner.nextLine();
        } catch (Exception e){
            System.out.println("Invalid config file");
            userData.delete();
            createNewConfig();
        }
    }

    Config() {
        Scanner scanner = new Scanner(System.in);
        if (!auth){
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();
            this.username=username;
            this.password=password;
        }
    }
}
