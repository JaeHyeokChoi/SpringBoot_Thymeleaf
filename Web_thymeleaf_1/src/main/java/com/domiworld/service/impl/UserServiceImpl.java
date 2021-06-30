package com.domiworld.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domiworld.repository.UserRepository;
import com.domiworld.service.UserService;
import com.domiworld.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public UserVO createUsers(UserVO vo) throws Exception {
		UserVO reVO = new UserVO();
		reVO = userRepository.save(vo);
		
		return reVO;
	}

	@Override
	public void deleteUsers(Long id) throws Exception{
		userRepository.deleteById(id);
		
	}

	@Override
	public List<UserVO> getAllUsers() throws Exception{
		// TODO Auto-generated method stub
		List<UserVO> list = new ArrayList<UserVO>();
		list = userRepository.findAll();
		
		return list;
	}

}
