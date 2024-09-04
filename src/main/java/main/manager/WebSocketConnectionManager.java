package main.manager;

import main.socket.WebSocketClientHandler;
import main.socket.WebSocketServerHandler;

import java.net.URISyntaxException;

public class WebSocketConnectionManager {

    private WebSocketClientHandler client;
    private WebSocketServerHandler server;

    public WebSocketConnectionManager(String serverURL, int serverPort) throws URISyntaxException {
        this.client = new WebSocketClientHandler(serverURL);
        this.server = new WebSocketServerHandler(serverPort);
    }

    public void start() {
        new Thread(() -> server.start()).start();
        client.connect();
    }

    public void stop() throws InterruptedException {
        server.stop();
        client.close();
    }

    public WebSocketClientHandler getClient() {
        return client;
    }

    public WebSocketServerHandler getServer() {
        return server;
    }
}
