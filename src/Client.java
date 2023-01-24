import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

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
        System.out.println("Benvenuto in eHub, seleziona l'operazione da svolgere:\nIspezione\nAcquisto");
        try {
            scelta = read.readLine();
            while (!scelta.equals("Ispezione") && !scelta.equals("Acquisto")) {
                System.out.println("Selezionare l'operazione da svolgere:\nIspezione\nAcquisto");
                scelta = read.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(scelta);
        try {
            System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
