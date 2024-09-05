package com.zzw.controller;

import com.zzw.pojo.Result;
import com.zzw.pojo.User;
import com.zzw.service.UserService;
import com.zzw.util.JwtUtil;
import com.zzw.util.Md5Util;
import com.zzw.util.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {

        //查询用户
        User user = userService.findByUsername(username);
        if (user == null) {
            //没有被占用
            //注册
            userService.register(username, password);
            return Result.success();
        }
        return Result.error("用户名已被占用");
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查询User
        User loginuser = userService.findByUsername(username);
        //判断是否查询到
        if (loginuser == null) {
            return Result.error("查询错误");
        }
        //判断密码是否正确
        if (Md5Util.getMD5String(password).equals(loginuser.getPassword())) {
            //登陆成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginuser.getId());
            claims.put("username", loginuser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        //根据用户名查询用户
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");
        Map<String,Object> map =ThreadLocalUtil.get();
        String username=(String) map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);

    }
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        //1.校验参数
        String oldPwd=params.get("old_pwd");
        String newPwd=params.get("new_pwd");
        String rePwd=params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(newPwd)||!StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }
        //校验原密码是否正确
        //调用userService根据用户名拿密码，在和old_Pwd比对
        Map<String,Object> map=ThreadLocalUtil.get();
        String username= (String) map.get("username");
        User loginuser=userService.findByUsername(username);
        if(!loginuser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写不正确");
        }
        //newPwd和rePwd是否一样
        if(!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一样");
        }
        //2.调用service进行密码更新
        userService.updatePwd(newPwd);
        return Result.success();
    }

}


