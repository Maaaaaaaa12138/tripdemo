package com.tripdemo.controller;

import com.tripdemo.entity.Message;
import com.tripdemo.mapper.MessageMapper;
import com.tripdemo.response.ResData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("messages")
public class MessageController {
    @Resource
    private MessageMapper messageMapper;

    @GetMapping("")
    private String getAll(HttpServletRequest request){
        int userId = (int) request.getAttribute("userId");
        return ResData.getRes("", messageMapper.getAll(userId));
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
