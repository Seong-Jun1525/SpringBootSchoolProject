package com.schoolproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolproject.entity.Board;
import com.schoolproject.service.BoardService;



@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
    private BoardService boardService;
	
	@GetMapping("/")
	public String MainPage(Model model) {
		List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
		return "index";
	}
	
	
	
}
