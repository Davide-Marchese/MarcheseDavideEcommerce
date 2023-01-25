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

    public String iterazioneProdotti(String prepend) {
        int contatore = 0;
        String messaggio = "";
        for (Prodotto prodotto : prodotti) {
            contatore++;
            messaggio += contatore + ") " + prodotto.getNome() + ": " + prodotto.getQuantità() + "; ";
        }
        return prepend + messaggio;
    }

    public void run() {
        try {
            while(true){
                String messaggio = in.readLine();
            System.out.println(messaggio);
            if (messaggio.equals("Visita")) {
                messaggio = iterazioneProdotti("Prodotti: ");
                out.println(messaggio);
            } else {
                messaggio = iterazioneProdotti("Quale prodotto si vuole acquistare? ");
                out.println(messaggio + " (Inserire il numero)");
                String acquisto = in.readLine();
                if(prodotti[Integer.parseInt(acquisto) - 1].getQuantità()>0){
                    prodotti[Integer.parseInt(acquisto) - 1].acquista(1);
                    out.println("Acquistato 1 " + prodotti[Integer.parseInt(acquisto) - 1].getNome());
                }
                else{
                    out.println("Prodotto esaurito.");
                }
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
