package ru.urfu.model;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Collections;

/**
 * Created by mikhail on 12/1/16.
 **/
@Entity
@Table(name = "TBL_USER")
public class User {
    private int id;
    private String login, password;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public org.springframework.security.core.userdetails.User returnUser() {
        return new org.springframework.security.core.userdetails.User(login, password, Collections.singletonList((GrantedAuthority) () -> "ROLE_USER"));
    }

    @Override
    public String toString() {
        return "User{login:" + login + "}";
    }
}
