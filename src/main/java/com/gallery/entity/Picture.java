package com.gallery.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;

@Mapper
@Setter
@Getter
public class Picture {
    private int id;
    private String url;
    private String describe;
    private int artworkId;
    private int managerId;
    private Date date;
}
