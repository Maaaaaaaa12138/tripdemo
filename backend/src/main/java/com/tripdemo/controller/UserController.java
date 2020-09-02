package com.tripdemo.controller;

import com.tripdemo.entity.LoginData;
import com.tripdemo.entity.MyToken;
import com.tripdemo.entity.User;
import com.tripdemo.response.ResData;
import com.tripdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

//    @Autowired
    @Resource
    private UserService userService;

    //  注册接口
//    @RequestMapping("/register")
    @PostMapping("/")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password,
                          @RequestParam("email") String email, @RequestParam("phoneNumber") String phoneNumber,
                          @RequestParam("verCode") String verCode) {

        if (userService.getUser(email) != null){
            return ResData.getRes("该邮箱已注册", "");
        }
        if (userService.checkVerCode(email, verCode)){
            User user = new User(username, password, email, 0, phoneNumber);
            userService.addUser(user);
            return ResData.getRes("", "注册成功");
        }
        return ResData.getRes("验证码错误或超时", "");
    }

    // 发送验证码接口
//    @RequestMapping("/sendCode")
    @GetMapping("code")
    public String sendCode(@RequestParam("email") String email){
        if (userService.senVerCode(email)){
            return ResData.getRes("", "发送成功");
        }
        return ResData.getRes("发送失败，请稍后再试", "");
    }

//    @RequestMapping("/login")
    // 登录接口
    @PostMapping("/auth")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password){
        User user = userService.getUser(email);
       // 判断密码是否正确
        if (user != null && userService.checkPassword(user.getId(), password)) {
            return ResData.getRes("", new LoginData(user.getId(), userService.setToken(user.getId())));
        }
        return ResData.getRes("邮箱或密码错误", "");
    }

//    @RequestMapping("/resetPassword")
    @PutMapping("/password")
    public String resetPassword(@RequestParam("email") String email,
                                @RequestParam("verCode") String verCode,
                                @RequestParam("newPassword") String newPassword) {
        if (userService.checkVerCode(email, verCode)){
            userService.resetPassword(email, newPassword);
            return ResData.getRes("", "密码重置成功");
        }
        return ResData.getRes("验证码错误或超时", "");
    }

    // 按照id获取用户
//    @RequestMapping("/getUser")
    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id){
        return ResData.getRes("", userService.getUser(id));
    }

}
