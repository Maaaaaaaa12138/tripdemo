package com.tripdemo.mapper;

import com.tripdemo.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select * from comment where item=#{item}")
    public List<Comment> getCommit(int item);

    @Insert("insert into comment(user, item, createTime, content) " +
            "values(#{user}, #{item}, #{createTime}, #{content})")
    public void addCommit(Comment comment);

}
