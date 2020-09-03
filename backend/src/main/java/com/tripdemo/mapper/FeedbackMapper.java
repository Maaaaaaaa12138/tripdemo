package com.tripdemo.mapper;

import com.tripdemo.entity.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedbackMapper {
    @Insert("insert into feedback(title, content, userId) values(#{title}, #{content}, #{userId})")
    public void addFeedback(Feedback feedback);
}
