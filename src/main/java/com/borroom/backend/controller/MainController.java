package com.borroom.backend.controller;

import com.borroom.backend.domain.Result;
import com.borroom.backend.domain.User;
import com.borroom.backend.service.LoginAuthService;
import com.borroom.backend.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private LoginAuthService loginAuthService;

    @PostMapping(value = "/login")
    public Result userLogin(String userid, String password) {
        return loginAuthService.login(userid, password);
    }

    @PostMapping(value = "/registry")
    public Result userReg(User user, String idnum) {
        return loginAuthService.registry(user, idnum);
    }

    @GetMapping(value = "/logout")
    public Result logout() {
        return loginAuthService.logout();
    }

    @GetMapping(value = "/isLogin")
    public Result isLogin() {
        if(loginAuthService.isLogin()){
            return ResultUtil.success(loginAuthService.getLoginUser());
        }
        return ResultUtil.error(3,"not login");
    }
}
