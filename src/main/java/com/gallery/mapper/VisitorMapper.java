package com.gallery.mapper;

import com.gallery.entity.Visitor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VisitorMapper {

    @Insert("insert into visitor(name,note) value(#{name},#{note})")
    public boolean insert(@Param("name")String name,@Param("note")String note);

    @Select("select * from visitor")
    public List<Visitor> getAll();

    @Delete("delete from visitor where id=#{id}")
    public boolean del(int id);
}
