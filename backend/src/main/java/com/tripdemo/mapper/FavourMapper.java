package com.tripdemo.mapper;

import com.tripdemo.entity.Favour;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FavourMapper {
    @Select("select * from love where user=#{userId}")
    public List<Favour> getFavours(int userId);

    @Insert("insert into love (item, user, create_time) values(#{item}, #{user}, #{createTime})")
    public void addFavour(Favour favour);
}
