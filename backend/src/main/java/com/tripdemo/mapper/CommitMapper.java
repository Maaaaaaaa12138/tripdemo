package com.tripdemo.mapper;

import com.tripdemo.entity.Commit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommitMapper {
    @Select("select * from commit where item=#{item}")
    public List<Commit> getCommit(int item);

    @Insert("insert into commit(user, item, createTime, content) " +
            "values(#{user}, #{item}, #{createTime}, #{content})")
    public void addCommit(Commit commit);

}
