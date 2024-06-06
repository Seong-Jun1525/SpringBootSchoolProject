package com.schoolproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolproject.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    Optional<Student> findByStudentNumberAndStudentMajor(Integer studentNumber, String studentMajor);
    // findByStudentNumberAndStudentMajor 여기서 StudentNumber와 StudentMajor가 엔티티 속성 변수명과 일치해야함
    Optional<Student> findByStudentEmailAndStudentPw(String email, String password);
    

}
