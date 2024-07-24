package com.timo.moosmann.tbr.mybank.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Transaction {
    private String id;
    @JsonProperty("sending_user_id")
    private String sendingUserId;
    @JsonProperty("receiving_user_id")
    private String receivingUserId;
    private Integer amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmZ")
    private ZonedDateTime timestamp;
    private String reference, slogan;

    public Transaction(
            String sendingUserId,
            String receivingUserId,
            Integer amount,
            ZonedDateTime timestamp,
            String reference,
            String slogan
    ) {
        this.id = UUID.randomUUID().toString();
        this.sendingUserId = sendingUserId;
        this.receivingUserId = receivingUserId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.reference = reference;
        this.slogan = slogan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getSendingUserId() {
        return sendingUserId;
    }

    public void setSendingUserId(String sendingUserId) {
        this.sendingUserId = sendingUserId;
    }

    public String getReceivingUserId() {
        return receivingUserId;
    }

    public void setReceivingUserId(String receivingUserId) {
        this.receivingUserId = receivingUserId;
    }
}
