package com.domiworld.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domiworld.mapper.DomiWorldMapper;
import com.domiworld.service.DomiWorldService;
import com.domiworld.vo.DomiWorldVO;

@Service
public class DomiWorldServiceImpl implements DomiWorldService {
	
	@Autowired
    private DomiWorldMapper domiWorldMapper;

	@Override
	public List<DomiWorldVO> getAll() throws Exception {
		List<DomiWorldVO> list = new ArrayList<DomiWorldVO>();
		list = domiWorldMapper.getAll();
		return list;
	}

	@Override
	public DomiWorldVO getOne() throws Exception {
		DomiWorldVO domiVO = new DomiWorldVO();
		domiVO = domiWorldMapper.getOne();
		
		return domiVO;
	}

	@Override
	public void insertDomiWorld(DomiWorldVO vo) throws Exception {
		domiWorldMapper.insertDomiWorld();
	}

	

}
