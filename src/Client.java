import java.io.*;
import java.net.*;

public class Client {
    public static void negozio(BufferedReader read, BufferedReader in, PrintWriter out) {
        String scelta = "";
        System.out.println("Benvenuto in eHub");
        while (true) {
            System.out.println("Seleziona l'operazione da svolgere:\nVisita\nAcquisto");
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
                String result = in.readLine();
                System.out.println(result);
                if (scelta.equals("Acquisto")) {
                    String acquisto = "";
                    boolean isCorrect = false;
                    do {
                        acquisto = read.readLine();
                        isCorrect = true;
                        try {
                            Integer.parseInt(acquisto);
                        } catch (NumberFormatException e) {
                            isCorrect = false;
                            System.out.println(result);
                        }
                    } while (!isCorrect);
                    out.println(acquisto);
                    System.out.println(in.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        Socket server = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            server = new Socket(InetAddress.getLocalHost(), 10000);
            in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            out = new PrintWriter(server.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        negozio(read, in, out);
    }
}