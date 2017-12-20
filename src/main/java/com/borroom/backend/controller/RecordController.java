package com.borroom.backend.controller;

import com.borroom.backend.domain.Record;
import com.borroom.backend.domain.Result;
import com.borroom.backend.repository.RecordRepository;
import com.borroom.backend.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class RecordController {

    @Autowired
    private RecordRepository recordRepository;

    /**
     *查询所有记录
     */
    @GetMapping(value = "/records")
    public Result recordList() {
        return ResultUtil.success(recordRepository.findAll());
    }

    /**
     * 通过id查询记录
     * */
    @GetMapping(value = "/records/id/{id}")
    public Result recordById(@PathVariable("id") Long id) {
        if(!recordRepository.exists(id)) {
            return ResultUtil.error(7,"no record");
        }
        return ResultUtil.success(recordRepository.findOne(id));
    }

    /**
     * 通过userid查询记录
     * */
    @GetMapping(value = "/records/userid/{userid}")
    public Result recordListByUserid(@PathVariable("userid") String userid) {
        System.out.println(userid);
        if(recordRepository.findByUserid(userid) == null) {
            return ResultUtil.error(7,"no record");
        }
        return ResultUtil.success(recordRepository.findByUserid(userid));
    }

    /**
     * 通过roomnum查询记录
     * */
    @GetMapping(value = "/records/roomnum/{roomnum}")
    public Result recordListByRoomnum(@PathVariable("roomnum") String roomnum) {
        if(recordRepository.findByUserid(roomnum) == null) {
            return ResultUtil.error(7,"no record");
        }
        return ResultUtil.success(recordRepository.findByRoomnum(roomnum));
    }

    /**
     * 通过status查询记录
     * */
    @GetMapping(value = "/records/status/{status}")
    public Result recordListByStatus(@PathVariable("status") Integer status) {
        if(recordRepository.findByStatus(status) == null) {
            return ResultUtil.error(7,"no record");
        }
        return ResultUtil.success(recordRepository.findByStatus(status));
    }

    /**
     * 通过userid和status查询记录
     * */
    @PostMapping(value = "/records/userid/status")
    public Result recordListByUseridAndStatus(String userid, Integer status) {
        System.out.println(status);
        if(recordRepository.findByUseridAndStatus(userid, status) == null) {
            return ResultUtil.error(7,"no record");
        }
        return ResultUtil.success(recordRepository.findByUseridAndStatus(userid, status));
    }

    @PostMapping(value = "/records/query")
    public Result queryRecord(String roomnum, String starttime) {
        ArrayList<Integer> status = new ArrayList<Integer>();
        status.add(1);
        status.add(2);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(starttime, pos);
        return ResultUtil.success(recordRepository.findByRoomnumAndStarttimeAfterAndStatusIn(roomnum, strtodate, status));
    }

    /**
     * 删除一条记录
     * */
    @DeleteMapping(value = "/records/{id}")
    public Result recordDelete(@PathVariable("id") Long id) {
        recordRepository.delete(id);
        return ResultUtil.success();
    }

    /**
     * 增加一条记录
     * */
    @PostMapping(value = "/records")
    public Result<Record> recordAdd(Record record) {
        return ResultUtil.success(recordRepository.save(record));
    }

    /**
     * 更新记录
     * */
    @PutMapping(value = "/records")
    public Result recordUpdate(Record record) {
        return ResultUtil.success(recordRepository.save(record));
    }

    @PutMapping(value = "/records/pass")
    public Result passRecord(Long id) {
        Record record = recordRepository.findOne(id);
        record.setStatus(1);
        return ResultUtil.success(recordRepository.save(record));
    }

    @PutMapping(value = "/records/reject")
    public Result RejectRecord(Long id) {
        Record record = recordRepository.findOne(id);
        record.setStatus(3);
        return ResultUtil.success(recordRepository.save(record));
    }

    Date DateFormatTansfer(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(time, pos);
        return strtodate;
    }

}
