package com.example.demo.service;

import com.example.demo.pojo.User;

import java.util.List;

public interface UserService {
    User findById(int ucode);

    List<User> findAll();

    User login(User user);

    User update(User user);
}
