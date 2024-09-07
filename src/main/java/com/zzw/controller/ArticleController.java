package com.zzw.controller;

import com.zzw.pojo.Article;
import com.zzw.pojo.Category;
import com.zzw.pojo.PageBean;
import com.zzw.pojo.Result;
import com.zzw.service.ArticleService;
import com.zzw.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping
    public Result add(@Validated @RequestBody Article article){
        articleService.add(article);
        return Result.success();
    }
    @GetMapping("/detail")
    public Result<Article> detail(Integer id){
        Article article=articleService.detail(id);
        return Result.success(article);
    }
    @PutMapping
    public Result update(@Validated @RequestBody Article article){
        articleService.update(article);
        return Result.success();
    }
    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum,Integer pageSize,
                                          @RequestParam(required = false) Integer categoryId,
                                          @RequestParam(required = false) String state){
        PageBean<Article> pb=articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);

    }
    @DeleteMapping
    public Result delete(Integer id){
        articleService.delete(id);
        return Result.success();
    }
}
