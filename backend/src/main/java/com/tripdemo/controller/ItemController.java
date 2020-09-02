package com.tripdemo.controller;

import com.tripdemo.entity.Item;
import com.tripdemo.mapper.ItemMapper;
import com.tripdemo.response.ResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {
    @Resource
    private ItemMapper itemMapper;

    //    @CrossOrigin("*")
//    @RequestMapping("/getItems")
    // 获取订单接口
    @GetMapping("")
    public String getItems(@RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "size", required = false) Integer size) {
        List<Item> items;
        if (page == null) {
            items = itemMapper.getItem(0, 10);
        } else {
            items = itemMapper.getItem(page, size);
        }
        return ResData.getRes("", items);
    }

//    @RequestMapping("/getHotItems")
    // 获取热门项目
    @GetMapping("/hot")
    public String getHot() {
        return ResData.getRes("", itemMapper.getHot());
    }

//    @RequestMapping("/getItemById")
    // 根据Id获取项目详情
    @GetMapping("/{id}")
    public String getItemById(@PathVariable("id") int id) {
        Item item = itemMapper.getItemById(id);
        if (item != null) {
            return ResData.getRes("", item);
        }
        return ResData.getRes("id错误", "");
    }
}
