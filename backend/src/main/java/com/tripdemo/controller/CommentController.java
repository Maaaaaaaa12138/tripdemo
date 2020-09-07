package com.tripdemo.controller;

import com.tripdemo.entity.Comment;
import com.tripdemo.entity.User;
import com.tripdemo.mapper.CommentMapper;
import com.tripdemo.response.ResData;
import com.tripdemo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("comments")
public class CommentController {
    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserService userService;

    @GetMapping("")
    public String getCommits(@RequestParam("itemId") int itemId) {
        List<Comment> comments = commentMapper.getCommit(itemId);
        User user;
        // 给每条评论添加用户头像
        for (Comment comment : comments) {
            user = userService.getUser(comment.getUser());
            comment.setUserAvatar(user.getAvatar());
            comment.setUsername(user.getUsername());
        }
        return ResData.getRes("", comments);
    }

    // 添加评论
    @PostMapping("/")
    public String addCommits(@RequestParam("content") String content, @RequestParam("itemId") int itemId, HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setItem(itemId);
        comment.setCreateTime(Math.toIntExact(System.currentTimeMillis() / 1000));
        comment.setUser(userId);
        commentMapper.addCommit(comment);
        return ResData.getRes("", "ok");
    }

}
