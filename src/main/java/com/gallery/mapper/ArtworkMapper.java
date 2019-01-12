package com.gallery.mapper;

import com.gallery.entity.Artwork;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArtworkMapper {

    @Select("select * from artwork")
    public List<Artwork> getAll();

    @Select("select * from artwork")
    public List<Artwork> getPageOf(int i);

    @Select("select * from artwork where id=#{id}")
    public Artwork getone(int id);

    @Select("Select * from artwork where name=#{name} and painter=#{painter}")
    public Artwork[] checkExisted(@Param("name")String name, @Param("painter")String painter);

    @Select("select * from artwork where name=#{name}")
    public List<Artwork> search1(String name);

    @Select("select * from artwork where name regexp #{name}")
    public List<Artwork> search2(String name);

    @Select("select * from artwork where name like #{name}")
    public List<Artwork> search3(String name);

    @Select("select * from artwork where painter=#{name}")
    public List<Artwork> searchByPainter1(String name);

    @Select("select * from artwork where painter regexp #{name}")
    public List<Artwork> searchByPainter2(String name);

    @Select("select * from artwork where painter like #{name}")
    public List<Artwork> searchByPainter3(String name);

    @Select("SELECT * FROM artwork WHERE id IN ( SELECT artworkId FROM picture WHERE managerId IN ( SELECT id FROM manager WHERE NAME = #{name} ) )" +
            "OR managerId IN ( SELECT id FROM manager WHERE NAME = #{name} )")
    public List<Artwork> searchByManager1(String name);

    @Select("SELECT * FROM artwork WHERE id IN ( SELECT artworkId FROM picture WHERE managerId IN ( SELECT id FROM manager WHERE NAME regexp #{name} ) )" +
            "OR managerId IN ( SELECT id FROM manager WHERE NAME regexp #{name} )")
    public List<Artwork> searchByManager2(String name);

    @Select("SELECT * FROM artwork WHERE id IN ( SELECT artworkId FROM picture WHERE managerId IN ( SELECT id FROM manager WHERE NAME like #{name} ) )" +
            "OR managerId IN ( SELECT id FROM manager WHERE NAME like #{name} )")
    public List<Artwork> searchByManager3(String name);

    @Insert("insert into artwork(name,painter,artwork.describe,managerId) value(#{name},#{painter},#{describe},#{managerId})")
    public boolean insert(@Param("name")String name,@Param("painter")String painter, @Param("describe")String describe,@Param("managerId")int managerId);

    @Update("update artwork set name=#{name},painter=#{painter},artworkUrl=#{artworkUrl},artwork.describe=#{describe} where id=#{id}")
    public boolean updateArtwork(@Param("id")int id,@Param("name")String name,@Param("painter")String painter,@Param("artworkUrl")String artworkUrl, @Param("describe")String describe);

    @Delete("delete from artwork where id=#{id}")
    public boolean delete(@Param("id")int id);
}
