package main.socket;

import main.stomp.StompPayload;
import main.stomp.StompPayloadParser;
import org.java_websocket.server.WebSocketServer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;

import java.net.InetSocketAddress;

public class WebSocketServerHandler extends WebSocketServer {

    public WebSocketServerHandler(int port) {
        super(new InetSocketAddress(port));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New connection opened: " + handshake.getResourceDescriptor());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection to " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        StompPayload stompMessage = StompPayloadParser.parse(message);
        System.out.println("Received: " + stompMessage);

        conn.send(StompPayloadParser.build(stompMessage));
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("An error occurred on connection " + conn.getRemoteSocketAddress() + ":" + ex);
    }

    @Override
    public void onStart() {
        System.out.println("Server started successfully");
    }
}
