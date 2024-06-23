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
    
    public Board getBoardById(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow();
    }
    
    public boolean deleteBoard(Long boardId) {
        // 게시글을 찾아서 삭제
        Board board = boardRepository.findById(boardId).orElse(null);
        if (board != null) {
            boardRepository.delete(board);
            return true;
        }
        return false;
    }

    @Transactional
    public void updateBoard(Board updateBoard) {
        // 기존 데이터 가져오기
        Board existingBoard = getBoardById(updateBoard.getBoardId());
        // 업데이트할 필드들 설정
        existingBoard.setTitle(updateBoard.getTitle());
        existingBoard.setContents(updateBoard.getContents());
        existingBoard.setProfessorEmail(updateBoard.getProfessorEmail());
        existingBoard.setBoardRegistrationDate(LocalDate.now());

        // 업데이트된 성적 정보를 저장
        boardRepository.save(existingBoard);
    }
    
}
