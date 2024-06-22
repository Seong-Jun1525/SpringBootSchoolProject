package com.schoolproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolproject.entity.Board;
import com.schoolproject.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    
    @GetMapping("/write")
    public String showBoardCreateForm(Model model, HttpSession session) {
        model.addAttribute("board", new Board());
        String loggedInProfessorEmail = (String) session.getAttribute("loggedInProfessorEmail");
        System.out.println(loggedInProfessorEmail); // 정상적으로 저장되어있는지 콘솔에서 확인
        model.addAttribute("loggedInProfessorEmail", loggedInProfessorEmail);
        return "professor/board/boardWrite";
    }
    
     @PostMapping("/write")
     public String writeBoard(@ModelAttribute Board board, Model model) {
        try {
             boardService.writeBoard(board);
             return "redirect:/";
            } catch (Exception e) {
            	model.addAttribute("errorMessage", "더 이상 게시글을 작성할 수 없습니다.");
            	return "redirect:/error";
            }
    }
     
     @GetMapping("/list")
     public String listBoard(Model model) {
         List<Board> boards = boardService.findAll();
         model.addAttribute("boards", boards);
         return "professor/board/boardList"; // boardList 페이지로 전송
     }
     
     @GetMapping("/home")
     public String homeBoard(Model model) {
         List<Board> boards = boardService.findAll();
         model.addAttribute("boards", boards);
         return "fragment/home";
     }
 
}