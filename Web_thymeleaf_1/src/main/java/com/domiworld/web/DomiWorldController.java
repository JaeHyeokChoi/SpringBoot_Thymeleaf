package com.domiworld.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.domiworld.service.DomiWorldService;
import com.domiworld.vo.DomiWorldVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "main")
public class DomiWorldController {
	
	@Autowired
	private DomiWorldService domiWorldService;
	
	@GetMapping(value = "/index.do")
    public ModelAndView main() throws Exception {
		log.info("MAIN2 CONTROLLER INDEX START");
        ModelAndView modelAndView = new ModelAndView();
      
        List<DomiWorldVO> userList = new ArrayList<DomiWorldVO>();
        
        userList = domiWorldService.getAll();
        log.info("USER_LIST : {}",userList);
        
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("views/main2");
        
        return modelAndView;
    }
	
}
