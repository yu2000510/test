package com.bjpowernode.dao;

import com.bjpowernode.entity.Perms;
import com.bjpowernode.entity.User;

import java.util.List;


public interface UserDao {
    void save(User user);

    User findByUsername(String username);

    // 根据用户名查询所有角色
    User findRolesByUsername(String username);
    // 根据角色的id查询权限信息集合
    List<Perms> findPermsByRoleId(Integer id);

}
