package com.tripdemo.mapper;

import com.tripdemo.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemMapper {
    // 按照page，size获取首页项目
    @Select("select * from item where location=#{location} limit #{page}, #{size}")
    public List<Item> getItem(int page, int size, String location);

    // 获取首页滚动图
    @Select("select * from item limit 5")
    public List<Item> getHot();

    @Select("select * from item where id=#{id}")
    public Item getItemById(int id);

    @Select("select * from item where name=#{name}")
    public Item getItemByName(String name);

    @Select("select * from item where name like #{name}")
    public List<Item> searchItemByName(String name);
}
