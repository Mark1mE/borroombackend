package com.borroom.backend.repository;

import com.borroom.backend.domain.InfoMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoMatchRepository extends JpaRepository<InfoMatch, String> {
    //通过idnum查询用户信息匹配
    InfoMatch findByIdnum(String idnum);

    //通过category查询用户信息匹配
    List<InfoMatch> findByCategory(Integer category);
}
