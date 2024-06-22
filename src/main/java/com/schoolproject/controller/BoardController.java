package com.schoolproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolproject.entity.Board;
import com.schoolproject.entity.Grade;
import com.schoolproject.service.BoardService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    //게시판 작성 페이지
    @GetMapping("/write")
    public String showBoardCreateForm(Model model, HttpSession session) {
        model.addAttribute("board", new Board());
        String loggedInProfessorEmail = (String) session.getAttribute("loggedInProfessorEmail");
        System.out.println(loggedInProfessorEmail); // 정상적으로 저장되어있는지 콘솔에서 확인
        model.addAttribute("loggedInProfessorEmail", loggedInProfessorEmail);
        return "professor/board/boardWrite";
    }
    //게시판 작성 처리
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
     
     //게시판 수정
     @GetMapping("/edit/{id}")
     public String editBoard(@PathVariable("id") Long boardId, Model model) {
    	 Board board = boardService.getBoardById(boardId);
         model.addAttribute("board", board);
         return "professor/board/boardUpdate"; //
     }
     
     // 수정된 게시판 정보 저장
     @PostMapping("/update")
     public String updateBoard(@ModelAttribute("baord") Board updateBoard) {
     	
     	if (updateBoard.getBoardId() == null) {
             throw new IllegalArgumentException("Board ID cannot be null");
         }
     	
     	Board existingBoard = boardService.getBoardById(updateBoard.getBoardId());
     	existingBoard.setTitle(updateBoard.getTitle());
     	existingBoard.setContents(updateBoard.getContents());
     	existingBoard.setProfessorEmail(updateBoard.getProfessorEmail());
     	existingBoard.setBoardRegistrationDate(updateBoard.getBoardRegistrationDate());
         boardService.updateBoard(existingBoard);
         return "redirect:/"; // 수정 후 성적 목록 페이지로 리다이렉트
     }
     // 게시판 목록 처리
     @GetMapping("/list")
     public String listBoard (Model model, HttpSession session) {
    	 
    	 String loggedInProfessorEmail = (String) session.getAttribute("loggedInProfessorEmail");
    	 
    	 List<Board> boards;
    	 boards = boardService.findAll();
         model.addAttribute("boards", boards);
         return "professor/board/boardList"; // boardList 페이지로 전송
     }
     
     // 게시판 목록 처리
     @GetMapping("/home")
     public String homeBoard(Model model) {
         List<Board> boards = boardService.findAll();
         model.addAttribute("boards", boards);
         return "fragment/home";
     }
 
}