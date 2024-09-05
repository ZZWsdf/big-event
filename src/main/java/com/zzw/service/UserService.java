package com.zzw.service;

import com.zzw.pojo.User;


public interface UserService {

    //更新
    void update(User user);

    //根据用户名查询用户
    User findByUsername(String username);
    //注册用户
    void register(String username, String password);
    //更新头像
    void updateAvatar(String avatarUrl);
    //更新密码
    void updatePwd(String newPwd);
}
