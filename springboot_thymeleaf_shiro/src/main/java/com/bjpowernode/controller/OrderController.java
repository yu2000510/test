package com.bjpowernode.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @RequestMapping("/save")
    //@RequiresRoles(value = {"admin","user"}) // 用来判断角色，同时具有
    @RequiresPermissions("user:update:*") // 用来判断权限字符串
    public String save(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("user")){
            System.out.println("保存订单");
        }else {
            System.out.println("无权访问");
        }
        return "redirect:/index.jsp";
    }

}
