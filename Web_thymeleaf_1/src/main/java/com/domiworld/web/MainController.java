package com.domiworld.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domiworld.service.UserService;
import com.domiworld.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "main")
public class MainController {
	
	/**
	 * 
	 * JPA - H2 데이터 main view
	 * 
	 * */
	
	@Autowired
	private UserService mainService;
	
	@GetMapping(value = "/index.do")
    public ModelAndView main() throws Exception {
		log.info("MAIN CONTROLLER INDEX START");
        ModelAndView modelAndView = new ModelAndView();
      
        List<UserVO> userList = new ArrayList<UserVO>();
        
        userList = mainService.getAllUsers();
        log.info("USER_LIST : {}",userList);
        
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("views/main");
        
        return modelAndView;
    }
	
}
