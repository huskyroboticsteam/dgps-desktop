package me.huskyrobotics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.java_websocket.client.WebSocketClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        WebSocketClient client = new WSClient(new URI("ws://" + getHost() + ":" + getPort()));
        client.connectBlocking();

        try {
            // takes input from System.in and echoes message in server
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            while (!(userInput = stdIn.readLine()).equals("STOP")) {
                // converts correction data into json object and sends it to WS server
                CorrectionData data = new CorrectionData(userInput);
                client.send(convertDataToJson(data));
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

    /**
     * Converts correction data into JSON object.
     * @param data correction data/
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
