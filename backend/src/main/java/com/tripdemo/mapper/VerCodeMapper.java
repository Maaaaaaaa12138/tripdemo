package com.tripdemo.mapper;

import com.tripdemo.entity.VerCode;
import org.apache.ibatis.annotations.*;

@Mapper
public interface VerCodeMapper {
    @Select("select * from VerCode where email=#{email}")
    public VerCode getVerCode(String email);

    @Insert("insert into verCode(content, email, createTime) values(#{content}, " +
            "#{email}, #{createTime})")
    public void addVerCode(VerCode verCode);

    @Delete("delete from verCode where email=#{email}")
    public void deleteVerCode(String email);

    @Update("update verCode set content=#{content}, createTime=#{createTime} where email=#{email}")
    public void updateVerCode(VerCode verCode);
}
