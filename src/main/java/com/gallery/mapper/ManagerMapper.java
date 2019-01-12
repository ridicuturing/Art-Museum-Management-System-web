package com.gallery.mapper;

import com.gallery.entity.Manager;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.Mapping;

@Mapper
public interface ManagerMapper {
    @Select("select * from manager where account=#{account} and password=#{password}")
    public Manager checkManager(@Param("account")String account,@Param("password")String password);

    @Update("update manager set loginCredentials=#{loginCredentials} where id=#{id}")
    public boolean updateLoginCredentials(@Param("id")int id,@Param("loginCredentials")String loginCredentials);

    @Select("select * from manager where loginCredentials = #{loginCredentials}")
    public Manager findLoginCredentials(String loginCredentials);

    @Select("select id,name,position,grade,date from manager")
    public Manager[] getAll();

    @Select("select name from manager where account=#{account}")
    public Manager checkAccount(String account);

    @Insert("insert into manager(name,account,password,position,grade) value(#{name},#{account},#{password},#{position},#{grade})")
    public boolean insert(@Param("name")String name,@Param("account")String account,@Param("password")String password,@Param("position")String position,@Param("grade")int grade);

    @Delete("delete from manager where id = #{id}")
    public boolean delManager(int id);
}
