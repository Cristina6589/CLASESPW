package org.example.tiedafx.model;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private long id;
    private String name, mail, pass;
    private int idProfile, cash;


    public User(String name, String mail, String pass, int idProfile, int cash) {
        this.name = name;
        this.mail = mail;
        this.pass = pass;
        this.idProfile = idProfile;
        this.cash = cash;
    }
}
