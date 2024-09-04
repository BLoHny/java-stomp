package main.socket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

import main.stomp.StompPayload;
import main.stomp.StompPayloadParser;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocketClientHandler extends WebSocketClient {
    private CountDownLatch latch = new CountDownLatch(1);

    public WebSocketClientHandler(String uri) throws URISyntaxException {
        super(new URI(uri));
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("WebSocket connection opened");
    }

    @Override
    public void onMessage(String message) {
        StompPayload stompMessage = StompPayloadParser.parse(message);
        System.out.println("Received: " + stompMessage);
        latch.countDown();
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("WebSocket connection closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public void sendStompMessage(StompPayload message) {
        send(StompPayloadParser.build(message));
    }

    public void awaitMessage() throws InterruptedException {
        latch.await();
    }
}
