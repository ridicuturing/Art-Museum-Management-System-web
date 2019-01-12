package com.gallery.controller.api;

import com.gallery.entity.Manager;
import com.gallery.mapper.ArtworkMapper;
import com.gallery.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ManagerController {
    @Autowired
    ArtworkMapper A;
    @Autowired
    ManagerMapper M;

    @RequestMapping("/getMyInformation")
    public Manager getMyInformation(HttpServletRequest r){
        return getMyManager(r);
    }

    @RequestMapping("/getManagerList")
    public Manager[] getUserList(HttpServletRequest r){
        int id = checkLogin(r);
        if(id == -1)
            return null;

        return M.getAll();
    }

    @RequestMapping("/delManager")
    public int delManager(int id,HttpServletRequest r){
        int myId = checkLogin(r); //获得当前管理员id
        if(myId == -1)//登录异常
            return -2;
        if(myId == id)//如果删除本身账户，不允许删除
            return 0;
        if(M.delManager(id))//删除账户
            return 1;
        return -1;
    }

    @RequestMapping("/addManager")
    public int addManager(String name,String account,String password,String position,int grade,HttpServletRequest r){
        Manager man = getMyManager(r);//获得当前管理员
        if(man.getId() == -1)//如果登录异常
            return -2;
        if(null != M.checkAccount(account)) //如果注册账户已被注册
            return -3;
        if(grade > man.getGrade())//如果创建账户的等级超过当前管理员的等级
            grade = man.getGrade();//设置为当前管理员的等级
        if(M.insert(name,account,password,position,grade))//插入数据库
            return 1;
        return 0;
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
