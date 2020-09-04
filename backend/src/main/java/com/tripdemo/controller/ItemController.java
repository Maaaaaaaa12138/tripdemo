package com.tripdemo.controller;

import com.tripdemo.entity.Item;
import com.tripdemo.mapper.ItemMapper;
import com.tripdemo.response.ResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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
                           @RequestParam(value = "size", required = false) Integer size,
                           @RequestParam(value = "location", required = false) String location,
                           @RequestParam(value = "name", required = false) String name) {
        List<Item> items;
        // 根据名字删选的优先级最高
        if (name != null){
            return ResData.getRes("", itemMapper.searchItemByName("%"+name+"%"));
        }
        if (page == null) {
            items = itemMapper.getItem(0, 10, Objects.requireNonNullElse(location, "北京"));
        } else {
            items = itemMapper.getItem(page, size, Objects.requireNonNullElse(location, "北京"));
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

    @GetMapping("/")
    public String getItemByName(@RequestParam("name") String name){
        Item item = itemMapper.getItemByName(name);
        if (item != null){
            return ResData.getRes("", item);
        }
        return ResData.getRes("id错误", "");
    }
}
