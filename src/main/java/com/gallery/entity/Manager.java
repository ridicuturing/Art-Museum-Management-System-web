package com.gallery.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class Manager {
    private int id;
    private String account;
    private String password;
    private String name;
    private String position;
    private String loginCredentials;
    private int grade;
    private Date date;

}
