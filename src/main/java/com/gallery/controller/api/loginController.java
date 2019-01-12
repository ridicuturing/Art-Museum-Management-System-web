package com.gallery.controller.api;

import com.gallery.entity.Manager;
import com.gallery.mapper.ArtworkMapper;
import com.gallery.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class loginController {
    @Autowired
    ArtworkMapper A;
    @Autowired
    ManagerMapper M;



    @RequestMapping("/login")
    public int login(String account,String password,HttpServletResponse res){
        Manager man = M.checkManager(account,password);
        if(man == null)
            return -1;//用户名密码错误
        setLoginCredentials(man,res);
        return 1;
    }

    private void setLoginCredentials(Manager man,HttpServletResponse res){
        String loginCredentials = System.currentTimeMillis() + "" +  man.getId() + "" + Math.random();
        res.addCookie(new Cookie("loginCredentials",loginCredentials));
        M.updateLoginCredentials(man.getId(),loginCredentials);
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
