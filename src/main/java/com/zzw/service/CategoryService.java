package com.zzw.service;

import com.zzw.pojo.Category;

import java.util.List;

public interface CategoryService {
    //新增分类
    void add(Category category);
    //列表查询
    List<Category> list();
    //查询详情
    Category fingByid(Integer id);
    //更新新类别
    void update(Category category);
    //删除类别
    void del(Integer id);
}
