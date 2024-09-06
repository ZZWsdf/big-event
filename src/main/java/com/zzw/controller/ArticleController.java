package com.zzw.controller;

import com.zzw.pojo.Article;
import com.zzw.pojo.Category;
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
    @DeleteMapping
    public Result delete(Integer id){
        articleService.delete(id);
        return Result.success();
    }
}
