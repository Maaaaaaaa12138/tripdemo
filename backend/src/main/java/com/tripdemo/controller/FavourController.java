package com.tripdemo.controller;

import com.tripdemo.entity.Favour;
import com.tripdemo.mapper.FavourMapper;
import com.tripdemo.response.ResData;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("favours")
public class FavourController {
    @Resource
    private FavourMapper favourMapper;

    @GetMapping("")
    public String getFavours(HttpServletRequest request){
        int userId = (int) request.getAttribute("userId");
        return ResData.getRes("", favourMapper.getFavours(userId));
    }

    @PostMapping("/")
    public String addFavour(@RequestParam("itemId") int itemId, HttpServletRequest request) {
        try {
            Favour favour = new Favour();
            int userId = (int) request.getAttribute("userId");
            // 判断是否已收藏
            if (favourMapper.getFavourByUser(userId, itemId) != null){
                return ResData.getRes("该项目已收藏，请不要重复添加", "");
            }
            favour.setItem(itemId);
            favour.setUser(userId);
            favour.setCreateTime(Math.toIntExact(System.currentTimeMillis() / 1000));
            favourMapper.addFavour(favour);
        } catch (Exception e) {
            return ResData.getRes(e.getMessage(), "");
        }
        return ResData.getRes("", "ok");
    }

    @DeleteMapping("/{id}")
    public String deleteFavour(@PathVariable("id") int id){
        try {
            favourMapper.deleteFavour(id);
        } catch (Exception e) {
            return ResData.getRes(e.getMessage(), "");
        }
        return ResData.getRes("", "ok");
    }
}
