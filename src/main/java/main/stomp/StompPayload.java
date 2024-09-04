package main.stomp;

import java.util.Map;

public class StompPayload {
    private String command;
    private Map<String, String> headers;
    private String body;

    public StompPayload(String command, Map<String, String> headers, String body) {
        this.command = command;
        this.headers = headers;
        this.body = body;
    }

    public String getCommand() {
        return command;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(command).append("\n");
        headers.forEach((key, value) -> builder.append(key).append(":").append(value).append("\n"));
        builder.append("\n").append(body);
        return builder.toString();
    }
}
