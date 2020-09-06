package com.tripdemo.mapper;

import com.tripdemo.entity.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("select * from message where userId=#{userId} order by id desc")
    public List<Message> getAll(int userId);

    @Update("update message set type=1 where id=#{id}")
    public void readMes(int id);

    @Delete("delete from message where id=#{id}")
    public void deleteMes(int id);

    @Select("select * from message where id=#{id}")
    public Message getMesById(int id);

}
