package com.hackathon.ge.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "email")
public class Email implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(length = 4098)
    private String body;

    @Column(length = 8196)
    private String hash;

    private String subject;
    private String sender;
    private String recipient;
    private String sendDate;
    private String attachment;
    private String classification;
    private boolean spam;

    public Email() {
    }

    public Email(Long id, String subject, String sender, String recipient, String body, String sendDate, String attachment, String hash, String classification, boolean spam) {
        this.id = id;
        this.subject = subject;
        this.sender = sender;
        this.recipient = recipient;
        this.body = body;
        this.sendDate = sendDate;
        this.attachment = attachment;
        this.hash = hash;
        this.classification = classification;
        this.spam= spam;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public boolean isSpam() {
        return spam;
    }

    public void setSpam(boolean spam) {
        this.spam = spam;
    }
}
