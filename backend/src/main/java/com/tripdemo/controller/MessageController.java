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
    public String getAll(HttpServletRequest request){
        int userId = (int) request.getAttribute("userId");
        List<Message> messages = messageMapper.getAll(userId);
        for (Message message : messages){
            message.setFromAvatar(userService.getUser(message.getFromId()).getAvatar());
        }
        return ResData.getRes("", messages);
    }

    @PutMapping("/{id}")
    public String readMes(@PathVariable("id") int id, HttpServletRequest request) {
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
    public String deleteMes(@PathVariable("id") int id, HttpServletRequest request) {
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

    // 添加消息
    @PostMapping("/")
    public String addMessage(@RequestParam("content") String content, @RequestParam("toId") int toId, HttpServletRequest request){
        try {
            int userId = (int) request.getAttribute("userId");
            Message message = new Message();
            message.setContent(content);
            message.setTitle("From:" + userService.getUser(userId).getUsername());
            message.setFromId(userId);
            message.setUserId(toId);
            messageMapper.addMes(message);
        } catch (Exception e) {
            return ResData.getRes("未知错误", "");
        }
        return ResData.getRes("", "ok");
    }
}
