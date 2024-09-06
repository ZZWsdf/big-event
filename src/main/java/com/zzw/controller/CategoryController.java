package com.zzw.controller;

import com.zzw.pojo.Category;
import com.zzw.pojo.Result;
import com.zzw.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);

        return Result.success();

    }
    @GetMapping
    public Result<List<Category>> list(){

        List<Category> list=categoryService.list();
        return Result.success(list);
    }
    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        Category category=categoryService.fingByid(id);
        return Result.success(category);
    }
    @PutMapping
    public Result update(@Validated(Category.Update.class) @RequestBody Category category){
        categoryService.update(category);
        return Result.success();
    }
    public Result delete(Integer id){
        return Result.success();
    }
}
