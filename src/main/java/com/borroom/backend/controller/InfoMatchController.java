package com.borroom.backend.controller;

import com.borroom.backend.domain.InfoMatch;
import com.borroom.backend.domain.Result;
import com.borroom.backend.repository.InfoMatchRepository;
import com.borroom.backend.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InfoMatchController {

    @Autowired
    private InfoMatchRepository infoMatchRepository;

    /**
     * 查询所有用户的信息匹配
     * */
    @GetMapping(value = "/infomatch")
    public Result infoMatchList() {
        return ResultUtil.success(infoMatchRepository.findAll());
    }

    /**
     * 通过userid查询用户信息匹配
     * */
    @GetMapping(value = "/infomatch/{userid}")
    public Result infoMatchFindOne(@PathVariable("userid") String userid) {
        return ResultUtil.success(infoMatchRepository.findOne(userid));
    }

    /**
     * 通过idnum查询用户信息匹配
     * */
    @GetMapping(value = "/infomatch/idnum/{idnum}")
    public Result infoMatchListByIdnum(@PathVariable("idnum") String idnum) {
        return ResultUtil.success(infoMatchRepository.findByIdnum(idnum));
    }

    /**
     * 通过category查询用户信息匹配
     * */
    @GetMapping(value = "/infomatch/category/{category}")
    public Result infoMatchListByCategory(@PathVariable("category") Integer category) {
        return ResultUtil.success(infoMatchRepository.findByCategory(category));
    }

    /**
     * 删除一个用户匹配项
     * */
    @DeleteMapping(value = "/infomatch/{userid}")
    public Result infoMatchDelete(@PathVariable("userid") String userid) {
        infoMatchRepository.delete(userid);
        return ResultUtil.success();
    }

    /**
     * 添加一个用户匹配项
     * */
    @PostMapping(value = "/infomatch")
    public Result InfoMacthAdd(InfoMatch infoMatch) {
        infoMatch.setUserid(infoMatch.getUserid());
        infoMatch.setIdnum(infoMatch.getIdnum());
        infoMatch.setCategory(infoMatch.getCategory());

        return ResultUtil.success(infoMatchRepository.save(infoMatch));
    }

    /**
     * 更新一个用户匹配项
     * */
    @PutMapping(value = "/infomatch")
    public Result infoUpdate(InfoMatch infoMatch){
        return ResultUtil.success(infoMatchRepository.save(infoMatch));
    }
}
