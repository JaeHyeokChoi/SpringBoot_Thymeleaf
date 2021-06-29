package com.domiworld.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domiworld.vo.UserVO;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long> {
}
