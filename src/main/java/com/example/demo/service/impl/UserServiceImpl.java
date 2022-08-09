package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    @Override
    public List<User> findAll() {
        return mapper.findAll();
    }

    @Override
    public User findById(int ucode) {
        return mapper.findById(ucode);
    }

    @Override
    public User login(User user) {
        return mapper.login(user);
    }

    @Override
    public User update(User user) {
        mapper.update(user);
        return mapper.findById(user.getUCode());
    }
}
