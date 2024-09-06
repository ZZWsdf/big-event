package com.zzw.mapper;

import com.zzw.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //新增
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)" +
            "values (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);
    //查询所有
    @Select("select * from category where create_user=#{id} ")
    List<Category> list(Integer id);
    //查询分类详情
    @Select("select * from category where id=#{id}")
    Category fingByid(Integer id);
    //更新数据
    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id=#{id}")
    void update(Category category);
    @Delete("delete from category where id=#{id}")
    void del(Integer id);
}
