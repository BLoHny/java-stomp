package main.stomp;

import java.util.HashMap;
import java.util.Map;

public class StompMessageBuilder {
    private String command;
    private Map<String, String> headers = new HashMap<>();
    private String body = "";

    public StompMessageBuilder setCommand(String command) {
        this.command = command;
        return this;
    }

    public StompMessageBuilder addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public StompMessageBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public StompPayload build() {
        return new StompPayload(command, headers, body);
    }
}
