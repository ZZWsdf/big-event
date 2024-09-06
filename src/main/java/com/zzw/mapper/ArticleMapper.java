package com.zzw.mapper;

import com.zzw.pojo.Article;
import com.zzw.pojo.Category;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ArticleMapper {
    //新增文章
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time) " +
            "values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);
    //查询详情
    @Select("select * from article where id=#{id}")
    Article detail(Integer id);

    //删除文章
    @Delete("delete from article where id=#{id}")
    void delete(Integer id);
    //更新文章

    @Update("update article set title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId},update_time=#{updateTime} where id=#{id}")
    void update(Article article);
}
