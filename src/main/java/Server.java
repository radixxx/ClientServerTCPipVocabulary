import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;


public class Server {
    public static HashMap<String, Object> clients = new HashMap<String, Object>();
    public static Vocabulary vocabulary = new Vocabulary();


    public static void main(String argv[]) throws Exception {
        String login;
        System.out.println("Server started!");
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {

            Scanner in = new Scanner(System.in);
            // Создание объекта для передачи клиентам

            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            login = inFromClient.readLine();
            System.out.println("Connected: " + login);
            Object obj = new Object(login, connectionSocket);
            clients.put(login, obj);
            obj.StartListening();
            System.out.println("Connected "+clients.size());//+" users"
        }
    }
}
