package com.domiworld.service;

import java.util.List;

import com.domiworld.vo.UserVO;

public interface MainService {
	 UserVO createUsers(UserVO vo) throws Exception;

     void deleteUsers(Long id) throws Exception;

     List<UserVO> getAllUsers() throws Exception;
}
