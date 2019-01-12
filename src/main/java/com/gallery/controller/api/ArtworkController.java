package com.gallery.controller.api;

import com.gallery.controller.Shared;
import com.gallery.entity.Manager;
import com.gallery.entity.Artwork;
import com.gallery.mapper.ManagerMapper;
import com.gallery.mapper.ArtworkMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ArtworkController {
    @Autowired
    ArtworkMapper A;
    @Autowired
    ManagerMapper M;


    @RequestMapping("/getPage")
    public List<Artwork> p(@RequestParam(value = "page", defaultValue = "1") int page) {
        return A.getAll();
    }

    @RequestMapping("/getArtworkMessage")
    public Artwork getArtworkMessage(int id){
        return A.getone(id);
    }

    @RequestMapping("/checkArtworkExisted")
    public int checkArtworkExisted(String name, String painter, HttpServletRequest r){
        int id = checkLogin(r);
        if(id == -1){
            System.out.println(id);
            return -1;
        }
        if(A.checkExisted(name,painter).length > 0) {
            return 1;
        }
        return 0;
    }

    @RequestMapping("/addArtwork")
    public int addArtwork(String name,String painter,String describe,HttpServletRequest r){
        int id = checkLogin(r);//获得管理员id
        if(id == -1)
            return -2; //用户登录失败
        if(A.checkExisted(name,painter).length > 0) {
            return -1; //artwork已存在
        }
        if(A.insert(name, painter,describe,id))
            return 1;//成功
        return 0;//sql更新失败
    }

    @RequestMapping("/modifyArtwork")
    public int modifyArtwork(int artwordId,String name,String painter,String describe,String artworkUrl,HttpServletRequest r){
        int id = checkLogin(r);
        if(id == -1)
            return -2; //用户登录失败
        if(A.updateArtwork(artwordId,name, painter,artworkUrl,describe))
            return 1;//成功
        return 0;//sql更新失败
    }

    @RequestMapping("/delArtwork")
    public int delArtwork(int artworkId,HttpServletRequest r){
        int id = checkLogin(r);
        if(id == -1)
            return -2; //用户登录失败
        if(A.delete(artworkId))
            return 1;//成功
        return 0;//删除失败（没有此艺术品）
    }

    @RequestMapping("/searchArtworkByName1")
    public List<Artwork> searchArtworkByName1(String name){
        return A.search1(name);
    }

    @RequestMapping("/searchArtworkByName2")
    public List<Artwork> searchArtworkByName2(String name){
        return A.search2(name);
    }

    @RequestMapping("/searchArtworkByName3")
    public List<Artwork> searchArtworkByName3(String name){
        return A.search3(name);
    }

    @RequestMapping("/searchArtworkByPainter1")
    public List<Artwork> searchArtworkByPainter1(String name){
        return A.searchByPainter1(name);
    }

    @RequestMapping("/searchArtworkByPainter2")
    public List<Artwork> searchArtworkByPainter2(String name){
        return A.searchByPainter2(name);
    }

    @RequestMapping("/searchArtworkByPainter3")
    public List<Artwork> searchArtworkByPainter3(String name){
        return A.searchByPainter3(name);
    }

    @RequestMapping("/searchByManager1")
    public List<Artwork> searchByManager1(String name){
        return A.searchByManager1(name);
    }

    @RequestMapping("/searchByManager2")
    public List<Artwork> searchByManager2(String name){
        return A.searchByManager2(name);
    }

    @RequestMapping("/searchByManager3")
    public List<Artwork> searchByManager3(String name){
        return A.searchByManager3(name);
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
