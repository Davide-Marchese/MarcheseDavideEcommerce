import java.net.*;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(10000);
        while (true) {
            Socket client = server.accept();
            new ClientHandler(client).start();
        }
    }
}
