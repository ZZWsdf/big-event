package com.zzw.controller;

import com.zzw.pojo.Article;
import com.zzw.pojo.Result;
import com.zzw.service.ArticleService;
import com.zzw.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
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
}
