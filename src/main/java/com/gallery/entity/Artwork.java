package com.gallery.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class Artwork {
    private int id;
    private String name;
    private String painter;
    private String artworkUrl;
    private String describe;
    private int managerId;
    private Date date;
}
