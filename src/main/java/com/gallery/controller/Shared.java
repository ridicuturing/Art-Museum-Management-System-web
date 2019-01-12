package com.gallery.controller;

import java.io.File;

public class Shared {
    public static String picturePath;
    static{
        File f;
        picturePath = "/home/tomcat/apache-tomcat-8.5.8/webapps/static/pictures/";
        if(System.getProperty("os.name").startsWith("Win") ||System.getProperty("os.name").startsWith("win"))
            picturePath = "D:/pictures/";
        f = new File(picturePath);
        if(!f.exists())
            f.mkdirs();
    }

    public static int pageSize = 9;

}
