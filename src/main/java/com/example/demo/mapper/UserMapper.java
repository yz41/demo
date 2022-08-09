package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findById(int ucode);

    List<User> findAll();

    User login(User user);

    void update(User user);
}
