package com.tripdemo.controller;

import com.tripdemo.entity.Message;
import com.tripdemo.mapper.MessageMapper;
import com.tripdemo.response.ResData;
import com.tripdemo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("messages")
public class MessageController {
    @Resource
    private MessageMapper messageMapper;

    @Resource
    private UserService userService;

    @GetMapping("")
    private String getAll(HttpServletRequest request){
        int userId = (int) request.getAttribute("userId");
        List<Message> messages = messageMapper.getAll(userId);
        for (Message message : messages){
            message.setFromAvatar(userService.getUser(message.getFromId()).getAvatar());
        }
        return ResData.getRes("", messages);
    }

    @PutMapping("/{id}")
    private String readMes(@PathVariable("id") int id, HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        Message message = messageMapper.getMesById(id);
        if (message.getUserId() != userId){
            return ResData.getRes("权限验证失败", "");
        }
        try {
            messageMapper.readMes(id);
        } catch (Exception e) {
            return ResData.getRes(e.getMessage(), "");
        }
        return ResData.getRes("", "ok");
    }

    @DeleteMapping("/{id}")
    private String deleteMes(@PathVariable("id") int id, HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        Message message = messageMapper.getMesById(id);
        if (message.getUserId() != userId){
            return ResData.getRes("权限验证失败", "");
        }
        try {
            messageMapper.deleteMes(id);
        } catch (Exception e) {
            return ResData.getRes(e.getMessage(), "");
        }
        return ResData.getRes("", "ok");
    }
}
