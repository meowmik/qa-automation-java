package com.tcs.edu;

import com.tcs.edu.decorator.ChoiceSeverity;
import com.tcs.edu.decorator.Severity;

import java.util.Objects;
import java.util.UUID;

public class Message {
    private Severity level;
    private String body;
    private UUID id;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s %s", body, ChoiceSeverity.choiceSeverity(level));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return level == message.level && Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, body);
    }
}
