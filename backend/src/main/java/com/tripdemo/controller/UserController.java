package com.tripdemo.controller;

import com.sun.el.parser.Token;
import com.tripdemo.entity.LoginData;
import com.tripdemo.entity.MyToken;
import com.tripdemo.entity.User;
import com.tripdemo.response.ResData;
import com.tripdemo.service.UserService;
import com.tripdemo.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
            User user = new User(username, password, email, 0, phoneNumber, "");
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
    public String login(@RequestParam("identify") String identify, @RequestParam("password") String password){
        User user = userService.getUser(identify);
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

    @PutMapping("/")
    public String updateUser(@RequestBody User user, HttpServletRequest request){
        String tokenStr = request.getHeader("token");
        MyToken token = userService.getToken(tokenStr);
        if (token == null || token.getUserId() != user.getId()){
            return ResData.getRes("权限验证失败, 请检查登录状态", "");
        }
        boolean res = userService.updateUser(user);
        if (res) {
            return ResData.getRes("", "修改成功");
        } else {
            return ResData.getRes("修改失败，请检查用户id", "");
        }
    }

//    @CrossOrigin("*")
    @PostMapping("/avatar")
    // 实现图片上传
    public String addAvatar(@RequestParam("file") MultipartFile file) {
        final String fileDir = "src\\main\\resources\\static\\avatar\\";
        String fileName;
        if (!file.isEmpty()) {
            try {
                fileName = Tool.getCrpy(Arrays.toString(file.getBytes())) + ".jpg";
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(
                                fileDir + fileName)));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                return ResData.getRes(e.getMessage(), "");
            }

            return ResData.getRes("", "/static/avatar/"+fileName);

        } else {
            return ResData.getRes("文件为空", "");
        }
    }

}
