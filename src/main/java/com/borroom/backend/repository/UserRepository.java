package com.borroom.backend.repository;

import com.borroom.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    //通过导师来查询
    List<User> findByTeacher(String teacher);

    //通过是否是管理员来查询
    List<User> findByIsadmin(Boolean isadmin);
}
