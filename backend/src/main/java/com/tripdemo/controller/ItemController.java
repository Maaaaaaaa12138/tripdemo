package com.tripdemo.controller;

import com.tripdemo.entity.Item;
import com.tripdemo.mapper.ItemMapper;
import com.tripdemo.response.ResData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemMapper itemMapper;

//    @CrossOrigin("*")
    @RequestMapping("/getItems")
    public String getItems(@RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "size", required = false) Integer size) {
        List<Item> items;
        if (page == null) {
            items = itemMapper.getItem(0, 10);
        }
        else{
            items = itemMapper.getItem(page, size);
        }
        return ResData.getRes("", items);
    }

    @RequestMapping("/getHotItems")
    public String getHot(){
        return ResData.getRes("", itemMapper.getHot());
    }

    @RequestMapping("/getItemById")
    public String getItemById(@RequestParam("id") int id){
        Item item = itemMapper.getItemById(id);
        if (item != null){
            return ResData.getRes("", item);
        }
        return ResData.getRes("id错误", "");
    }
}
