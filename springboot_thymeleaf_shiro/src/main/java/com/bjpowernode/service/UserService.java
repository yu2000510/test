package com.bjpowernode.service;

import com.bjpowernode.entity.Perms;
import com.bjpowernode.entity.User;

import java.util.List;

public interface UserService {
    // 注册用户
    void register(User user);

    User findByUsername(String username);

    User findRolesByUsername(String username);

    List<Perms> findPermsByRoleId(Integer id);
}
