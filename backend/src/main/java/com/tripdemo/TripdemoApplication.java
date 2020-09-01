package com.tripdemo;

import com.tripdemo.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.tripdemo.mapper")
@ServletComponentScan("com.tripdemo.Filter")
public class TripdemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(TripdemoApplication.class, args);
    }

}
