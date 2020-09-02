package com.tripdemo.controller;

import com.tripdemo.entity.Indent;
import com.tripdemo.entity.Item;
import com.tripdemo.entity.User;
import com.tripdemo.mapper.IndentMapper;
import com.tripdemo.mapper.ItemMapper;
import com.tripdemo.response.ItemData;
import com.tripdemo.response.ResData;
import com.tripdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单相关接口实现
 */
@RestController
@RequestMapping("/indents")
public class IntentController {

    @Resource
    private IndentMapper indentMapper;

    @Resource
    private UserService userService;

    @Resource
    private ItemMapper itemMapper;

    /**
     * 获取用户的所有订单
     * @param request: http请求对象
     * @return: 统一返回格式的结果
     */
//    @RequestMapping("/getAll")
//    @GetMapping("")
    @RequestMapping("")
    public String getAll(HttpServletRequest request){
        int userId = (int) request.getAttribute("userId");
        User user = userService.getUser(userId);
        List<ItemData> itemDataList = new ArrayList<>();
        List<Indent> indents = indentMapper.getIndents(userId);
        Item item;
        for (Indent indent: indents){
            item = itemMapper.getItemById(indent.getItemId());
            itemDataList.add(new ItemData(user.getUsername() + user.getPhoneNumber(),
                    item.getName(), indent.getIndentId()));
        }
        return ResData.getRes("", itemDataList);
    }

    @PostMapping("/")
    public String addIntent(HttpServletRequest request, @RequestParam("itemId") int itemId){
        int userId = (int) request.getAttribute("userId");
        int type = 1;
        String indentId = String.format("%s-%s", userId, System.currentTimeMillis());
        indentMapper.addIndent(new Indent(userId, itemId, type, indentId));
        return ResData.getRes("", "添加成功");
    }


}
