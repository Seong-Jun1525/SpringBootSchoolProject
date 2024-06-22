package com.schoolproject.entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "board")
public class Board {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long boardId;
	 
	 @Column(name = "title", nullable = false, columnDefinition = "varchar(255) default ''")
	 private String title;
	 
	 @Column(name = "contents", nullable = false, columnDefinition = "varchar(4000) default ''")
	 private String contents;
	 
	 @Column(name = "professor_email", nullable = false, columnDefinition = "varchar(100) default ''")
	 private String professorEmail;
	 
	 @Column(name = "borde_registration_date", nullable = false)
	 private LocalDate boardRegistrationDate;

	

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getProfessorEmail() {
		return professorEmail;
	}

	public void setProfessorEmail(String professorEmail) {
		this.professorEmail = professorEmail;
	}

	public LocalDate getBoardRegistrationDate() {
		return boardRegistrationDate;
	}

	public void setBoardRegistrationDate(LocalDate boardRegistrationDate) {
		this.boardRegistrationDate = boardRegistrationDate;
	}
	 
}
