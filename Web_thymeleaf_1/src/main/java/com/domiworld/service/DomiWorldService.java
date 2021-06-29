package com.domiworld.service;

import java.util.List;

import com.domiworld.vo.DomiWorldVO;

public interface DomiWorldService {
	 List<DomiWorldVO> getAll() throws Exception;
	 DomiWorldVO getOne() throws Exception;
     void insertDomiWorld(DomiWorldVO vo) throws Exception;

     
}
