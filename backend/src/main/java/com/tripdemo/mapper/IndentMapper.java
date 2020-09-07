package com.tripdemo.mapper;

import com.tripdemo.entity.Indent;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IndentMapper {
    @Select("select * from indent where userId=#{userId} order by id desc")
    public List<Indent> getIndents(int userId);

    @Insert("insert into indent (userId, itemId, type, indentId) values(#{userId}, #{itemId}," +
            "#{type}, #{indentId})")
    public void addIndent(Indent indent);

    // 根据订单号删除订单
    @Delete("delete from indent where indentId=#{indentId}")
    public void deleteById(String indentId);

    @Update("update indent set type=#{type} where indentId=#{id}")
    public void setType(String id, int type);
}
