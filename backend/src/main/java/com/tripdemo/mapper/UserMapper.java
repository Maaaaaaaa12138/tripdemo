package com.tripdemo.mapper;

import com.tripdemo.entity.MyToken;
import com.tripdemo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

@Mapper
public interface UserMapper {
    @Insert("insert into user(username, password, email, phoneNumber, avatar) values" +
            "(#{username}, #{password}, #{email}, #{phoneNumber}, #{avatar})")
    public void addUser(User user);

    @Select("select * from user where id=#{id} or email=#{email}")
    public User getUser(int id, String email);

    @Select("select * from user where username=#{username}")
    public User getUserByUsername(String username);

    @Select("select * from user where phoneNumber=#{phoneNumber}")
    public User getUserByPhoneNumber(String phoneNumber);

    @Select("select * from token where userId=#{userId} or content=#{token}")
    public MyToken getToken(int userId, String token);

    @Update("update user set password=#{password} where email=#{email}")
    public void resetPassword(String email, String password);

    @Insert("insert into token (content, userId, createTime) values(#{content}, #{userId}, #{createTime})")
    public void setToken(int userId, String content,  long createTime);

    @Update("update token set content=#{content}, createTime=#{currentTime} where userId=#{userId}")
    public void updateToken(int userId, String content, long currentTime);

    @Update("update user set username=#{username}, phoneNumber=#{phoneNumber}, avatar=#{avatar} where id=#{id}")
    public void updateUser(User user);
}
