package Lab6.ModuleServer;

public class Main {

    final static int PORT = 7778;
    final static String path = "Data.xml";

    public static void main(String[] args) {



        new ServerSide().loop(args, PORT);

    }
}
