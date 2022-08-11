package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 通过请求头获取 身份角色
     * @param type
     * @return
     */
    @RequestMapping("/findAll")
    public R findAll(@RequestHeader(value = "type", required = false) Integer type){
        if (type == null){
            return R.error("请求头type= " + type, Collections.EMPTY_LIST);
        }
        if (type == 1){
            return R.success(userService.findAll());
        }else {
            // 不是管理员不回传密码
            List<User> list = userService.findAll();
            list.forEach(user -> user.setUPwd(""));
            return R.success(list);
        }
    }

    @RequestMapping("/login")
    public User login(@RequestBody User user){
        return userService.login(user);
    }

    /**
     * 两个必传的请求头,用于校验判断
     * @param ucode 用户id
     * @param type 用户角色
     * @param user 需要修改的用户
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestHeader(value = "ucode") int ucode, @RequestHeader("type") int type, @RequestBody User user){
        if (type == 0 && user.getUCode() != ucode){
            return R.error("你没有权限修改");
        }
        return R.success(userService.update(user));
    }




}
