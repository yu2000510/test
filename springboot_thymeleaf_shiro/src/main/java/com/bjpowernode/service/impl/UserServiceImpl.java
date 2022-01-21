package com.bjpowernode.service.impl;

import com.bjpowernode.dao.UserDao;
import com.bjpowernode.entity.Perms;
import com.bjpowernode.entity.User;
import com.bjpowernode.service.UserService;
import com.bjpowernode.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        // 处理业务调用dao
        // 明文的密码进行MD5 + salt + hash散列
        // 1.生成随即盐
        String salt = SaltUtils.getSalt(8);
        // 2.将随机盐保存到数据库
        user.setSalt(salt);
        // 3.根据明文密码进行MD5 + salt hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        // 4.将生成的密文存入数据库
        user.setPassword(md5Hash.toHex());
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findRolesByUsername(String username) {
        return userDao.findRolesByUsername(username);
    }

    @Override
    public List<Perms> findPermsByRoleId(Integer id) {
        return userDao.findPermsByRoleId(id);
    }
}
