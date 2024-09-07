package com.zzw.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzw.mapper.ArticleMapper;
import com.zzw.pojo.Article;
import com.zzw.pojo.Category;
import com.zzw.pojo.PageBean;
import com.zzw.service.ArticleService;
import com.zzw.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        //补充信息
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userid= (Integer) map.get("id");
        article.setCreateUser(userid);
        articleMapper.add(article);
    }

    @Override
    public Article detail(Integer id) {
        Article article=articleMapper.detail(id);
        return article;
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建pageBean对象
        PageBean<Article> pb=new PageBean<>();
        //开启分页查询 PageHelper
        PageHelper.startPage(pageNum,pageSize);

        //调用Mapper
        Map<String,Object> map=ThreadLocalUtil.get();
        Integer userid= (Integer) map.get("id");
        List<Article> la=articleMapper.list(userid,categoryId,state);
        Page<Article> ps= (Page<Article>) la;
        //把数据填充到PageHelper里面
        pb.setTotal(ps.getTotal());
        pb.setItems(ps.getResult());
        return pb;
    }
}
