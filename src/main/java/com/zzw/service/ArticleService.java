package com.zzw.service;

import com.zzw.pojo.Article;
import com.zzw.pojo.PageBean;

public interface ArticleService {
    void add(Article article);
    //查询详情
    Article detail(Integer id);
    //删除文章
    void delete(Integer id);
    //更新文章
    void update(Article article);
    //分页查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
