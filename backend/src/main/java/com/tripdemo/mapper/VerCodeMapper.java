package com.tripdemo.mapper;

import com.tripdemo.entity.VerCode;
import org.apache.ibatis.annotations.*;

@Mapper
public interface VerCodeMapper {
    @Select("select * from vercode where email=#{email}")
    public VerCode getVerCode(String email);

    @Insert("insert into vercode(content, email, createTime) values(#{content}, " +
            "#{email}, #{createTime})")
    public void addVerCode(VerCode verCode);

    @Delete("delete from vercode where email=#{email}")
    public void deleteVerCode(String email);

    @Update("update vercode set content=#{content}, createTime=#{createTime} where email=#{email}")
    public void updateVerCode(VerCode verCode);
}
