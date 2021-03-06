package com.borroom.backend.controller;

import com.borroom.backend.domain.Result;
import com.borroom.backend.domain.User;
import com.borroom.backend.repository.UserRepository;
import com.borroom.backend.service.LoginAuthService;
import com.borroom.backend.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Console;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginAuthService loginAuthService;

    /**
     * 查询所有用户
     * */
    @GetMapping(value = "/users")
    public Result userList() {
        return ResultUtil.success(userRepository.findAll());
    }

    @GetMapping(value = "/admin/users/{userid}")
    public Result userListExceptAdmin(@PathVariable("userid") String userid) {
        return ResultUtil.success(userRepository.findAllByUseridNotContains(userid));
    }

    /**
     * 添加一个用户
     * */
    @PostMapping(value = "/users")
    public Result userAdd(User user) {
        if(userRepository.exists(user.getUserid())) {
            return ResultUtil.error(4, "userid duplication");
        }
        return ResultUtil.success(userRepository.save(user));
    }

    /**
     * 通过userid查询一个用户
     */
    @GetMapping(value = "/users/{userid}")
    public Result userFindOne(@PathVariable("userid") String userid) {
        System.out.println(userid);
        if(!userRepository.exists(userid)) {
            return ResultUtil.error(1, "no account");
        }
        return ResultUtil.success(userRepository.findOne(userid));
    }

    /**
     * 更新一个用户
     */
    @PutMapping(value = "/users")
    public Result userUpdate(User user) {
        if(!userRepository.exists(user.getUserid())) {
            return ResultUtil.error(7, "no user");
        } else {
            if(userOrAdmin(user.getUserid())) {
                user.setIsadmin(true);
            } else {
                user.setIsadmin(false);
            }
            return ResultUtil.success(userRepository.save(user));
        }
    }

    @PutMapping(value = "/admin/users")
    public Result adminUpdateUser(User user) {
        return ResultUtil.success(userRepository.save(user));
    }

    /**
     * 删除一个用户
     */
    @DeleteMapping(value = "/users/{userid}")
    public Result userDelete(@PathVariable("userid") String userid) {
        userRepository.delete(userid);
        return ResultUtil.success();
    }

    /**
     * 通过teacher查询用户列表
     * */
    @GetMapping(value = "/users/teacher/{teacher}")
    public Result userListByTeacher(@PathVariable("teacher") String teacher) {
        if(!userRepository.exists(teacher)) {
            return ResultUtil.error(8, "no teacher");
        }
        return ResultUtil.success(userRepository.findByTeacher(teacher));
    }

    /**
     * 判断用户是否是管理员
     * */
    public Boolean userOrAdmin(String userid) {
        return userRepository.findOne(userid).getIsadmin();
    }
}
