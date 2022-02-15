package me.huskyrobotics;

import org.java_websocket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class Client {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        WebSocketClient client = new WSClient(new URI("ws://localhost:3001"));
        client.connectBlocking();
        try {
            client.send("hello");
        } catch (Exception ex) {
            System.out.println("Client Failed to send message: " + ex.toString());
        }
    }
}
