package com.gallery.controller.api;

import com.gallery.entity.Manager;
import com.gallery.entity.Visitor;
import com.gallery.mapper.ArtworkMapper;
import com.gallery.mapper.ManagerMapper;
import com.gallery.mapper.VisitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class VisitorController {
    @Autowired
    ArtworkMapper A;
    @Autowired
    ManagerMapper M;
    @Autowired
    VisitorMapper V;
    @RequestMapping("/getAllVisitors")
    public List<Visitor> getAllVisitors(){
        return V.getAll();
    }

    @RequestMapping("/delVisitors")
    public boolean delVisitors(String id){
        return V.del(Integer.parseInt(id));
    }
    @RequestMapping("/addVisitors")
    public boolean addVisitors(String name,String note){
        return V.insert(name,note);
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
