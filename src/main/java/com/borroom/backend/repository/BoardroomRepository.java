package com.borroom.backend.repository;

import com.borroom.backend.domain.Boardroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardroomRepository extends JpaRepository<Boardroom, String> {
    //通过maxcap查询会议室
    List<Boardroom> findByMaxcapGreaterThan(Integer maxcap);

    //通过projector查询会议室
    List<Boardroom> findByProjector(Boolean projector);

    //通过available查询会议室
    List<Boardroom> findByAvailable(Boolean available);
}
