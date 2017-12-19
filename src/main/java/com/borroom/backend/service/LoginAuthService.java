package com.borroom.backend.service;

import com.borroom.backend.domain.Result;
import com.borroom.backend.domain.User;
import com.borroom.backend.repository.InfoMatchRepository;
import com.borroom.backend.repository.UserRepository;
import com.borroom.backend.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class LoginAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InfoMatchRepository infoMatchRepository;

    @Autowired
    private HttpSession httpSession;


    public Result login(String userid, String password) {
        User user = userRepository.findOne(userid);
        if(user == null) {
            //提示该用户不存在
            return ResultUtil.error(1, "no account");
        } else {
            if(password.equals(user.getPassword())) {
                //登录成功
                if(user.getIsadmin() == true) {
                    httpSession.setAttribute("admin", user);
                    return ResultUtil.success("admin");
                } else {
                    httpSession.setAttribute("user", user);
                    return ResultUtil.success("user");
                }
            } else {
                //提示密码错误，请重新输入
                return ResultUtil.error(2, "password error");
            }
        }
    }

    public Result logout() {
        if(httpSession.getAttribute("user") == null) {
            return ResultUtil.error(3, "not login");
        }
        httpSession.setAttribute("user", null);
        return ResultUtil.success();
    }

    public Result getLoginUser() {
        if(!isLogin()) {
            return ResultUtil.error(3, "not login");
        }
        return ResultUtil.success(httpSession.getAttribute("user"));
    }

    public boolean isLogin() {
        return httpSession.getAttribute("user") != null;
    }

    public Result registry(User user, String idnum) {
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //String password = passwordEncoder.encode(user.getPassword());
        String userid = user.getUserid();
        if (!userRepository.exists(userid)) {
            if (infoMatchRepository.exists(userid)) {
                if (idnum.equals(infoMatchRepository.findOne(userid).getIdnum())) {
                    userRepository.save(user);
                    return ResultUtil.success();
                } else {
                    return ResultUtil.error(10, "Idnum error");
                }
            } else {
                return ResultUtil.error(9, "Invalid account");
            }
        }else {
            return ResultUtil.error(4, "userid duplication");
        }
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }
}
