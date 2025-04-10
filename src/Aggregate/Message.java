package Aggregate;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private final String id;
    private final String content;
    private final MessageSide sender;
    private final MessageSide recipient;
    private boolean confirmed;
    private final List<Attachment> attachments;

    public enum MessageSide {
        CLIENT, DRIVER
    }

    public Message(String id, String content, MessageSide sender, MessageSide recipient) {
        this.id = id;
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.confirmed = false;
        this.attachments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public MessageSide getSender() {
        return sender;
    }

    public MessageSide getRecipient() {
        return recipient;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    /**
     * Подтверждение сообщения возможно только стороной, противоположной отправителю.
     */
    public void confirmMessage(MessageSide confirmer) {
        if (confirmer == sender) {
            throw new IllegalArgumentException("Отправитель не может подтверждать собственное сообщение.");
        }
        this.confirmed = true;
    }

    /**
     * Добавление вложения к сообщению. Вложение должно добавляться вместе с сообщением.
     */
    public void addAttachment(Attachment attachment) {
        if (attachment == null) {
            throw new IllegalArgumentException("Вложение не может быть null.");
        }
        attachments.add(attachment);
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }
}