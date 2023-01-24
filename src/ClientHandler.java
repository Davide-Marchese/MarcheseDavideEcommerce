import java.net.*;
import java.io.*;

public class ClientHandler extends Thread {
    static Prodotto[] prodotti = {
            new Prodotto("AMD Ryzen 5 3600", 15),
            new Prodotto("AMD Radeon RX 7900 XT", 20),
            new Prodotto("INTEL CORE I9-13900K", 25),
            new Prodotto("NVIDIA GEFORCE RTX 2080 TI", 30)
    };
    public Socket client;
    public BufferedReader in = null;
    public PrintWriter out = null;

    public ClientHandler(Socket client) {
        this.client = client;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            String messaggio = in.readLine();
            System.out.println(messaggio);
            if (messaggio.equals("Ispezione")) {
                messaggio = "Prodotti: ";
                int contatore = 0;
                for (Prodotto prodotto : prodotti) {
                    contatore++;
                    messaggio += contatore + ")" + prodotto.getNome() + ": " + prodotto.getQuantità() + "; ";
                }
                out.println(messaggio);
            } else {
                messaggio = "Quale prodotto si vuole acquistare? ";
                int contatore = 0;
                for (Prodotto prodotto : prodotti) {
                    contatore++;
                    messaggio += contatore + ")" + prodotto.getNome() + ": " + prodotto.getQuantità() + "; ";
                }
                out.println(messaggio);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
