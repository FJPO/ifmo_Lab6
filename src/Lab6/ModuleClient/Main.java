package Lab6.ModuleClient;

public class Main {

    final static int PORT = 7778;
    final static String ADDRESS = "127.0.0.1";

    public static void main(String[] args){

        // мой - 192.168.0.10
        //из консоли на helios 77.234.203.174
        //из cmd - 77.234.214.82
        //из InetAddress.getLocalHost().getHostAddress() - 192.168.10.10

        new ClientSide().loop(ADDRESS, PORT);


    }



}
