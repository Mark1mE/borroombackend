package com.borroom.backend.controller;

import com.borroom.backend.domain.Boardroom;
import com.borroom.backend.domain.Result;
import com.borroom.backend.repository.BoardroomRepository;
import com.borroom.backend.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardroomController {

    @Autowired
    private BoardroomRepository boardroomRepository;

    /**
     * 查询所有会议室
     * */
    @GetMapping(value = "/boardrooms")
    public Result boardroomList() {
        return ResultUtil.success(boardroomRepository.findAll());
    }

    /**
     * 添加一个会议室
     * */
    @PostMapping(value = "/boardrooms")
    public Result boardroomAdd(Boardroom boardroom) {
        if(boardroomRepository.exists(boardroom.getRoomnum())) {
            return ResultUtil.error(5,"boardroom duplication");
        }

        return ResultUtil.success(boardroomRepository.save(boardroom));
    }

    /**
     * 通过roomnum查询会议室
     * */
    @GetMapping(value = "/boardrooms/{roomnum}")
    public Result boardroomFindOne(@PathVariable("roomnum") String roomnum) {
        if(!boardroomRepository.exists(roomnum)) {
            return ResultUtil.error(6, "no boardroom");
        } else {
            return ResultUtil.success(boardroomRepository.findOne(roomnum));
        }
    }

    /**
     * 通过maxcap查询会议室
     * */
    @GetMapping(value = "/boardrooms/maxcap/{maxcap}")
    public Result boardroomListByCap(@PathVariable("maxcap") Integer maxcap) {
        return ResultUtil.success(boardroomRepository.findByMaxcapGreaterThan(maxcap));
    }

    /**
     * 通过projector查询会议室
     * */
    @GetMapping(value = "/boardrooms/projector/{projector}")
    public Result boardroomListByCap(@PathVariable("projector") Boolean projector) {
        return ResultUtil.success(boardroomRepository.findByProjector(projector));
    }

    /**
     * 通过available查询会议室
     * */
    @GetMapping(value = "/boardrooms/available/{available}")
    public Result boardroomListByAvail(@PathVariable("available") Boolean available) {
        return ResultUtil.success(boardroomRepository.findByAvailable(available));
    }

    /**
     * 删除一个会议室
     * */
    @DeleteMapping(value = "/boardrooms/{roomnum}")
    public Result boardroomDelete(@PathVariable("roomnum") String roomnum) {
        boardroomRepository.delete(roomnum);
        return ResultUtil.success();
    }

    /**
     * 更新会议室信息
     * */
    @PutMapping(value = "/boardrooms")
    public Result boardroomUpdate(Boardroom boardroom) {
        return ResultUtil.success(boardroomRepository.save(boardroom));
    }
}
