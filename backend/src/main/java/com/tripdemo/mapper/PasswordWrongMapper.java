package com.tripdemo.mapper;

import com.tripdemo.entity.PasswordWrong;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PasswordWrongMapper {

    @Select("select * from passwordwrong where user=#{user}")
    public PasswordWrong getByUser(int user);

    @Update("update passwordwrong set wrongTimes=#{wrongTimes}, updateTime=#{updateTime} where user=#{user}")
    public void update(PasswordWrong passwordWrong);

    @Update("insert into passwordwrong (user, wrongTimes, updateTime) values" +
            "(#{user}, #{wrongTimes}, #{updateTime})")
    public void add(PasswordWrong passwordWrong);

    @Delete("delete from passwordwrong where user=#{user}")
    public void delete(int user);

}
