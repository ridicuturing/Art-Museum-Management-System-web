package com.gallery.mapper;

import com.gallery.entity.Artwork;
import com.gallery.entity.Picture;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PictureMapper {

    @Select("select * from picture")
    public List<Picture> getAll();

    @Select("select * from picture where id=#{id}")
    public Picture getone(int id);

    @Select("select * from picture where managerId in (select id from manager where name=#{name})")
    public List<Picture> search1(String name);

    @Select("select * from picture where managerId in (select id from manager where name regexp #{name})")
    public List<Picture> search2(String name);

    @Select("select * from picture where managerId in (select id from manager where name like #{name})")
    public List<Picture> search3(String name);


    @Insert("insert into picture(url,picture.describe,managerId) value(#{url},#{describe},#{managerId})")
    public boolean insert(@Param("url")String url,@Param("describe")String describe,@Param("managerId")int managerId);

    @Update("update picture set picture.describe=#{describe},artworkId=#{artworkId} where url=#{url}")
    public boolean update(@Param("url")String url,@Param("describe")String describe,@Param("artworkId")int artworkId);

    @Delete("delete from picture where id=#{id}")
    public boolean delete(@Param("id")int id);

}
