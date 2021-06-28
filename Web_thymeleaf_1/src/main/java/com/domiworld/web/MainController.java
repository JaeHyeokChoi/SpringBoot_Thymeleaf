package com.domiworld.web;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "main")
public class MainController {
	
	@GetMapping(value = "/index.do")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "도미");
        map.put("date", LocalDateTime.now());
        
        modelAndView.addObject("data", map);
        modelAndView.setViewName("views/main");
        
        return modelAndView;
    }
	
}
