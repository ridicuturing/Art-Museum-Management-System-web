package com.gallery.controller.api;

import com.gallery.controller.Shared;
import com.gallery.entity.Manager;
import com.gallery.entity.Picture;
import com.gallery.mapper.ArtworkMapper;
import com.gallery.mapper.ManagerMapper;
import com.gallery.mapper.PictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@RestController
public class PictrueController {
    @Autowired
    ArtworkMapper A;
    @Autowired
    ManagerMapper M;
    @Autowired
    PictureMapper P;

    public static String url = "http://ridm.xin/static/pictures/";

    @RequestMapping("/uploadFile")
    public String upload(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) {
        String url2;
        Manager man = getMyManager(request);
        if(man==null)
            return "";
        for (MultipartFile f : files) {
            try {
                url2 = uploadFile(f.getInputStream(), f.getOriginalFilename());
                P.insert(url + url2,"",man.getId());
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return "upload success";
    }
    private static String uploadFile(InputStream in, String fileName) throws Exception {
        String filePath;
        fileName = System.currentTimeMillis() + "" + (int)(Math.random()*10000) + fileName.substring(fileName.lastIndexOf("."));
        filePath = Shared.picturePath + fileName;
        File f = new File(filePath);
        while(f.exists()) {
            fileName = System.currentTimeMillis() + "" + (int)(Math.random()*10000) + fileName.substring(fileName.lastIndexOf("."));
            filePath = Shared.picturePath + fileName;
            f = new File(filePath);
        }
        FileOutputStream out = new FileOutputStream(f);
        byte[] buff = new byte[1024];
        while (-1 != in.read(buff)) {
            out.write(buff);
        }
        out.flush();
        out.close();
        return fileName;
    }

    @RequestMapping("/getAllPictures")
    public List<Picture> getAllPictures(HttpServletRequest r){
        if(checkLogin(r) == -1)
            return null;
        return P.getAll();
    }

    @RequestMapping("/getPictureMessage")
    public Picture getPictureMessage(int id){
        return P.getone(id);
    }

    @RequestMapping("/modifyPicture")
    public int modifyPicture(String url,String describe,String artworkId,HttpServletRequest r){
        Manager man = getMyManager(r);
        if (man == null)
            return -2;
        if(P.update(url,describe,Integer.parseInt(artworkId)))
            return 1;
        return 0;
    }

    @RequestMapping("/delPicture")
    public int delPicture(String pictureId,HttpServletRequest r){
        Manager man = getMyManager(r);
        if (man == null)
            return -2;
        if(pictureId == null)
            return -1;
        if(P.delete(Integer.parseInt(pictureId)))
            return 1;
        return 0;
    }

    @RequestMapping("/searchPicturesByManager1")
    public List<Picture> searchPicturesByManager1(String name){
        return P.search1(name);
    }
    @RequestMapping("/searchPicturesByManager2")
    public List<Picture> searchPicturesByManager2(String name){
        return P.search2(name);
    }
    @RequestMapping("/searchPicturesByManager3")
    public List<Picture> searchPicturesByManager3(String name){
        return P.search3(name);
    }




    /*
     * check user whether logined legally
     */
    private int checkLogin(HttpServletRequest r){
        Manager man = getMyManager(r);
        if(man != null)
            return man.getId();
        return -1;
    }

    private Manager getMyManager(HttpServletRequest r){
        Manager man;
        for(Cookie c: r.getCookies()){
            if(c.getName().equals("loginCredentials")){
                if((man = M.findLoginCredentials(c.getValue())) != null){
                    return man;
                }
            }
        }
        return null;
    }

}
