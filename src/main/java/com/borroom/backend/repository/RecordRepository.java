package com.borroom.backend.repository;

import com.borroom.backend.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    // 通过userid查询记录
    List<Record> findByUserid(String userid);

    //通过roomnum查询记录
    List<Record> findByRoomnum(String roomnum);

    //通过status查询记录
    List<Record> findByStatus(Integer status);

    //通过userid和status查询记录
    List<Record> findByUseridAndStatus(String userid, Integer Status);

    List<Record> findByRoomnumAndStarttimeAfterAndStatusIn(String roomnum, Date starttime, ArrayList statusList);

}
