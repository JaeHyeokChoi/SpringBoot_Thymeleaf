package com.domiworld.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.domiworld.vo.DomiWorldVO;

@Repository
public interface DomiWorldMapper {
	public List<DomiWorldVO> getAll() throws Exception;
	public DomiWorldVO getOne() throws Exception;
	public void insertDomiWorld() throws Exception;
}
