import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        Socket server = null;
        BufferedReader in = null;
        PrintWriter out = null;
        String scelta = "";
        try {
            server = new Socket(InetAddress.getLocalHost(), 10000);
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            out = new PrintWriter(server.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            System.out.println("Benvenuto in eHub, seleziona l'operazione da svolgere:\nVisita\nAcquisto");
        try {
            scelta = read.readLine();
            while (!scelta.equals("Visita") && !scelta.equals("Acquisto")) {
                System.out.println("Selezionare l'operazione da svolgere:\nVisita\nAcquisto");
                scelta = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(scelta);
        try {
            System.out.println(in.readLine());
            if (scelta.equals("Acquisto")) {
                String acquisto = read.readLine();
                out.println(acquisto);
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }
}
