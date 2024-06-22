package com.schoolproject.service;



import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.entity.Board;
import com.schoolproject.repository.BoardRepository;

import jakarta.transaction.Transactional;

@Service
public class BoardService {
    
    @Autowired
    private BoardRepository boardRepository;
    
    @Transactional
    public void writeBoard(Board board) {
    	board.setBoardRegistrationDate(LocalDate.now());
        boardRepository.save(board);
    }
    
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public Board save(Board board) {
        return boardRepository.save(board);
    }

}
