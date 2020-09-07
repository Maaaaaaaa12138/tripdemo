package com.tripdemo.controller;

import com.tripdemo.entity.Feedback;
import com.tripdemo.mapper.FeedbackMapper;
import com.tripdemo.response.ResData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("feedbacks")
public class FeedbackController {
    @Resource
    private FeedbackMapper feedbackMapper;

    @PostMapping("/")
    public String addFeedback(@RequestBody Feedback feedback) {
        try {
            feedbackMapper.addFeedback(feedback);
            return ResData.getRes("", "添加成功");
        } catch (Exception e) {
            return ResData.getRes("添加失败", e.getMessage());
        }
    }
}
