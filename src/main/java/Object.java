import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Object {
    String login;
    Socket clientSocket;
    Scanner in = new Scanner(System.in);

    public Object(String login, Socket clientSocket) {
        this.login = login;
        this.clientSocket = clientSocket;
    }

    public void StartListening() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        do {

                            BufferedReader inFromClient =
                                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                            String message = inFromClient.readLine();
                            System.out.println(login + ": " + message);

                            String response;
                            response = message + " - " +Server.vocabulary.getTranslation(message);

                            Response(login, response);
                        } while (clientSocket.getInputStream().available() > 0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void Response(String login, String message) {
        try {
            HashMap<String, Object> clients = Server.clients;
            if (!clients.containsKey(login)) return;

            Object obj = clients.get(login);
            DataOutputStream stream = new DataOutputStream(obj.clientSocket.getOutputStream());
            stream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
