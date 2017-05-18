package ru.urfu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;

@Entity
@Table(name = "TBL_MESSAGE")
public class Message implements Serializable {
    private int id;

    @Column(nullable = false)
    private String message;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private String authorName;

    @JsonIgnore
    @ElementCollection
    private HashSet<Integer> likes = new HashSet<>();

    @JsonIgnore
    private String retwitedAuthor=null;

    public String getRetwitedAuthor() {
        return retwitedAuthor;
    }

    public void setRetwitedAuthor(String retwitedAuthor) {
        this.retwitedAuthor = retwitedAuthor;
    }

    public HashSet<Integer> getLikes() {
        return likes;
    }

    public void setLikes(HashSet<Integer> likes) {
        this.likes = likes;
    }

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


    //При ретвите поднимать флаг и ссылку на староое
}