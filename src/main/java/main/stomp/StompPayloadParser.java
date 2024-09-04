package main.stomp;

import java.util.HashMap;
import java.util.Map;

public class StompPayloadParser {

    public static StompPayload parse(String rawMessage) {
        String[] lines = rawMessage.split("\n");
        String command = lines[0];
        Map<String, String> headers = new HashMap<>();
        int i = 1;

        while (!lines[i].isEmpty()) {
            String[] header = lines[i].split(":");
            headers.put(header[0], header[1]);
            i++;
        }

        String body = lines[i + 1];
        return new StompPayload(command, headers, body);
    }

    public static String build(StompPayload message) {
        return message.toString();
    }
}
