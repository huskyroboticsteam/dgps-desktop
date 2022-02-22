package me.huskyrobotics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws URISyntaxException, InterruptedException, IOException {
        URI wsURI = new URI("ws://" + getHost() + ":" + getPort());
        WebSocketClient client = new WSClient(wsURI);
        client.connectBlocking();
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        try {
            // takes input from System.in and echoes message in server
            String userInput;
            while (client.isOpen()) {
                // converts correction data into json object and sends it to WS server
                userInput = stdIn.readLine();
                if (userInput.equalsIgnoreCase("reconnect")) {
                    client.closeBlocking();
                    wsURI.resolve("ws://" + getHost() + ":" + getPort());
                    client = new WSClient(wsURI);
                    client.connectBlocking();
                } else if (userInput.equalsIgnoreCase("close")) {
                    break;
                } else {
                    CorrectionData data = new CorrectionData(userInput);
                    client.send(convertDataToJson(data));
                }
            }
            client.closeBlocking();
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

    /**
     * Converts correction data into JSON object.
     * @param data correction data.
     * @return data as a JSON object.
     */
    private static String convertDataToJson(CorrectionData data) {
        GsonBuilder builder = new GsonBuilder();

        Gson gson = builder.create();
        return gson.toJson(data);
    }

    /**
     * Correction Data object from the DGPS reference station.
     */
    private static class CorrectionData {
        String payload;

        public CorrectionData() {
            this.payload = "";
        }

        public CorrectionData(String payload) {
            this.payload = payload;
        }
    }
}
