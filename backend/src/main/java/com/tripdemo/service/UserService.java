package com.tripdemo.service;

import com.tripdemo.entity.MyToken;
import com.tripdemo.entity.User;
import com.tripdemo.entity.VerCode;
import com.tripdemo.mapper.UserMapper;
import com.tripdemo.mapper.VerCodeMapper;
import com.tripdemo.tool.Tool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private VerCodeMapper verCodeMapper;

    public User getUser(int id){
        User user = userMapper.getUser(id, "");
        // 不给前端返回用户密码
        user.setPassword("");
        return user;
    }

    public User getUser(String email){
        User user = userMapper.getUser(-1,email);
        if (user == null){
            return null;
        }
        user.setPassword("");
        return user;
    }

    public MyToken getToken(String token){
        return userMapper.getToken(-1, token);
    }

    public void addUser(User user){
//        密码加密
        user.setPassword(Tool.getCrpy(user.getPassword()));
        userMapper.addUser(user);
    }

//    验证密码是否正确
    public boolean checkPassword(int id, String password){
        String pwd = Tool.getCrpy(password);
        return pwd.equals(userMapper.getUser(id, "").getPassword());
    }

    public void resetPassword(String email, String password){
        userMapper.resetPassword(email, Tool.getCrpy(password));
    }

//    验证token是否正确
    public boolean checkToken(int id, String token){
        MyToken token1 = userMapper.getToken(id, "");
        return token1 != null && token1.getContent().equals(token) && (System.currentTimeMillis() / 1000 - token1.getCreateTime() > 60 * 60 * 24 * 30);
    }

    public boolean checkVerCode(String email, String verCode){
        VerCode verCodeObj = verCodeMapper.getVerCode(email);
        if (verCodeObj != null) {
            // 判断验证码是否超时
            if (System.currentTimeMillis() / 1000 - verCodeObj.getCreateTime() > 10 * 60) {
                return false;
            }
            if (verCodeObj.getContent().equals(verCode)) {
//                验证成功时候删除验证码
                verCodeMapper.deleteVerCode(email);
                return true;
            }
        }
        return false;
    }

//    发送验证码
    public boolean senVerCode(String email){
        StringBuilder content = new StringBuilder();
        for(int i = 0; i < 6; i++){
            content.append(String.valueOf(new Random().nextInt(9)));
        }
        long ct = System.currentTimeMillis()/1000;
        VerCode verCode = new VerCode(String.valueOf(content), email, ct);
        // 判断是否已有验证码
        if (verCodeMapper.getVerCode(email) != null) {
            // 卡发送时间
            if (ct - verCodeMapper.getVerCode(email).getCreateTime() < 60) {
                return false;
            }
            verCodeMapper.updateVerCode(verCode);
        } else {
            verCodeMapper.addVerCode(verCode);
        }
        return Tool.sendEmail(email, String.valueOf(content));
    }

//    登录成功后设置Token
    public String setToken(int userId){
        String name = this.getUser(userId).getUsername();
        long ct = System.currentTimeMillis() / 1000;
        String content = Tool.getCrpy(name + ct);
        if (userMapper.getToken(userId, "") != null){
            userMapper.updateToken(userId, content, ct);
        }
        else{
            userMapper.setToken(userId, content, ct);
        }
        return content;
    }

    public boolean updateUser(User user){
        try {
            userMapper.updateUser(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
