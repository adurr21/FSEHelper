package austindurr.fsehelper;

public class Main {
    //Global Variables
    final static String icaoDataPath = "icaodata.csv";
    final static String applicationMD5 = "dc8e8e74f259e9a6fcb14bb2e4b71a49";


    public static void main(String[] args){
        FileParse file = new FileParse(icaoDataPath);
        Config config = new Config();
        Menu menu = new Menu(config,applicationMD5);
    }
}