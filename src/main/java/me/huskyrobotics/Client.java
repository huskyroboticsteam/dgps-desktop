package me.huskyrobotics;

import org.java_websocket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class Client {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        WWebSocketClient client = new WSClient(new URI("ws://" + getHost() + ":" + getPort()));
        client.connectBlocking();
        try {
            // takes input from System.in and echoes message in server
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while (!(userInput = stdIn.readLine()).equals("STOP")) {
                client.send(userInput);
            }
            client.close();
        } catch (Exception ex) {
            System.out.println("Client Failed to send message: " + ex.toString());
        }
    }
    
    /**
     * Gets user input for Host name.
     * @return Host name.
     */
    private static String getHost() {
        System.out.print("Host: ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Gets user input for port number.
     * @return Port Number.
     */
    private static int getPort() {
        System.out.print("Port Number: ");
        Scanner in = new Scanner(System.in);
        return Integer.parseInt(in.nextLine());
    }
}
