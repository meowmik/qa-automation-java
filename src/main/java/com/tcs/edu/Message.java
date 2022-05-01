package com.tcs.edu;

import com.tcs.edu.decorator.Severity;

public class Message {
    private Severity level;
    private String body;

    public Message(Severity level, String body) {
        this.level = level;
        this.body = body;
    }

    public Message(String body) {
        this(Severity.REGULAR, body);
    }

    public Severity getLevel() {
        return level;
    }

    public String getBody() {
        return body;
    }
}
