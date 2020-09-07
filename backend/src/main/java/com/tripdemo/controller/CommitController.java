package com.tripdemo.controller;

import com.tripdemo.entity.Commit;
import com.tripdemo.mapper.CommitMapper;
import com.tripdemo.response.ResData;
import com.tripdemo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("commits")
public class CommitController {
    @Resource
    private CommitMapper commitMapper;

    @Resource
    private UserService userService;

    @GetMapping("")
    public String getCommits(@RequestParam("itemId") int itemId) {
        List<Commit> commits = commitMapper.getCommit(itemId);
        // 给每条评论添加用户头像
        for (Commit commit : commits) {
            commit.setUserAvatar(userService.getUser(commit.getUser()).getAvatar());
        }
        return ResData.getRes("", commits);
    }

    // 添加评论
    @PostMapping("/")
    public String addCommits(@RequestParam("content") String content, @RequestParam("itemId") int itemId, HttpServletRequest request) {
        int userId = (int) request.getAttribute("userId");
        Commit commit = new Commit();
        commit.setContent(content);
        commit.setItem(itemId);
        commit.setCreateTime(Math.toIntExact(System.currentTimeMillis() / 1000));
        commit.setUser(userId);
        commitMapper.addCommit(commit);
        return ResData.getRes("", "ok");
    }

}
