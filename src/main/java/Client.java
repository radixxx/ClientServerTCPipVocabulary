import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client {

    public static void main(String argv[]) throws Exception {
        String host = "127.0.0.1";
        final Scanner in = new Scanner(System.in);
        System.out.println("Enter host/ip: ");
        host = in.next();
        final Socket clientSocket = new Socket(host, 6789);

        new Thread(new Runnable() {
            public void run() {
                System.out.println("Please enter your name: \n");

                try {
                    String sentence;
                    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

                    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                    sentence = inFromUser.readLine();
                    outToServer.writeBytes(sentence + '\n');

                    while (sentence != null && !sentence.isEmpty()){
                        System.out.println("Enter a word you want to translate: ");
                        outToServer = new DataOutputStream(clientSocket.getOutputStream());
                        sentence = inFromUser.readLine();
                        outToServer.writeBytes(sentence + '\n');
                    }
                    System.out.println("Exit");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {

                        do {
                            DataInputStream inFromClient =
                                    new DataInputStream(clientSocket.getInputStream());
                            String message = inFromClient.readUTF();
                            System.out.println(message);
                        } while (clientSocket.getInputStream().available() > 0);
                }} catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (true) {}
    }

}