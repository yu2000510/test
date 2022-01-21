package com.bjpowernode.controller;

import com.bjpowernode.entity.User;
import com.bjpowernode.service.UserService;
import com.bjpowernode.utils.VerityCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    // 跳转到register请求
    @RequestMapping("/registerview")
    public String register(){
        System.out.println("跳转至register的html");
        return "register";
    }

    // 跳转login请求
    @RequestMapping("/loginview")
    public String login(){
        System.out.println("跳转至login的html");
        return "login";
    }

    // 验证码方法
    @RequestMapping("/getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        // 生成验证码
        String code = VerityCodeUtils.generateVerifyCode(4);
        // 验证码放入session
        session.setAttribute("code",code);
        // 验证码放入图片
        ServletOutputStream os = response.getOutputStream();
        response.setContentType("image/png");
        VerityCodeUtils.outputImage(220,60,os,code);
    }

    // 注册用户
    @RequestMapping("/register")
    public String register(User user){
        try{
            userService.register(user);
            return "redirect:/user/loginview";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/user/registerview";
        }
    }

    // 退出登录
    @RequestMapping("/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "redirect:/user/loginview";
    }

    // 用来处理身份认证
    @RequestMapping("/login")
    public String login(String username,String password,String code,HttpSession session){
        // 比较验证码
        String code1 = (String) session.getAttribute("code");
        try{
            if (code1.equalsIgnoreCase(code)){// 不区分大小写比较
            // 获取主体对象
            // 在web环境中，只要在配置类配置了安全管理器，就会自动注入安全工具类当中
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(username,password));
            return "redirect:/index";
            }else{
                throw new RuntimeException("验证码错误！");
            }
        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("用户名错误");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
        return "redirect:/user/loginview";
    }
}
