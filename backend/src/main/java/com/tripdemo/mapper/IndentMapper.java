package com.tripdemo.mapper;

import com.tripdemo.entity.Indent;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IndentMapper {
    @Select("select * from intent where userId=#{userId} order by id desc")
    public List<Indent> getIndents(int userId);

    @Insert("insert into intent (userId, itemId, type, indentId) values(#{userId}, #{itemId}," +
            "#{type}, #{indentId})")
    public void addIndent(Indent indent);

    // 根据订单号删除订单
    @Delete("delete from intent where indentId=#{indentId}")
    public void deleteById(String indentId);

    @Update("update intent set type=#{type} where indentId=#{id}")
    public void setType(String id, int type);
}
