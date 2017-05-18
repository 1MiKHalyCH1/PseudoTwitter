package ru.urfu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "TBL_DIRECT_MESSAGE")
public class DirectMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String message;

    @JsonIgnore
    @JoinColumn
    private String senderName;

    @JsonIgnore
    @JoinColumn
    private String clientName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getSenderName() {

        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}