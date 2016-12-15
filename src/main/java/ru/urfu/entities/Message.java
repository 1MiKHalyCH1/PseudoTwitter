package ru.urfu.entities;

import javax.persistence.*;

/**
 * Created by Михаил on 12.10.2016.
 */

@Entity
@Table(name = "TBL_MESSAGE")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn
    private String authorName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
            this.message = message;
    }

    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
