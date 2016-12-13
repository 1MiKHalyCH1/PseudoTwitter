package ru.urfu.model;

import javax.persistence.*;

/**
 * Created by Михаил on 12.10.2016.
 */

@Entity
@Table(name = "TBL_MESSAGE")
public class Message {
    private int id;
    private String _message;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String get_message() {
        return this._message;
    }
    public void set_message(String _message) {
        if (_message.length() > 140)
            this._message = _message.substring(0, 140);
        else
            this._message = _message;
    }
}
