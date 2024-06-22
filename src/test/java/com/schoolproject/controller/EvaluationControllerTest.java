package com.schoolproject.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.schoolproject.entity.Enrolment;
import com.schoolproject.entity.Evaluation;
import com.schoolproject.entity.Student;
import com.schoolproject.exception.EvaluationNotFoundException;
import com.schoolproject.exception.StudentNotFoundException;
import com.schoolproject.service.EnrolmentService;
import com.schoolproject.service.EvaluationService;
import com.schoolproject.service.StudentService;

@WebMvcTest(EvaluationController.class)
public class EvaluationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EvaluationService evaluationService;

    @Mock
    private StudentService studentService;

    @Mock
    private EnrolmentService enrolmentService;

    @InjectMocks
    private EvaluationController evaluationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterEvaluationForm() throws Exception {
        String studentEmail = "test@student.com";
        String lectureName = "Test Lecture";

        mockMvc.perform(get("/evaluations/register")
                .sessionAttr("loggedInStudentEmail", studentEmail)
                .param("item", lectureName))
                .andExpect(status().isOk())
                .andExpect(view().name("student/lecture/lectureEvaluation"))
                .andExpect(model().attributeExists("evaluation"))
                .andExpect(model().attribute("loggedInStudentEmail", studentEmail))
                .andExpect(model().attribute("lectureName", lectureName));
    }

    @Test
    public void testUpdateEvaluation() throws Exception {
        Evaluation evaluation = new Evaluation();
        when(evaluationService.registerEvaluation(any(Evaluation.class))).thenReturn(evaluation);

        mockMvc.perform(post("/evaluations/register")
                .flashAttr("evaluation", evaluation))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testListEvaluations() throws Exception {
        String studentEmail = "test@student.com";
        Student student = new Student();
        student.setStudentNumber(123);
        List<Enrolment> enrolments = new ArrayList<>();
        Enrolment enrolment = new Enrolment();
        enrolment.setLectureName("Test Lecture");
        enrolments.add(enrolment);

        when(studentService.findByEnrolmentStudentInfo(studentEmail)).thenReturn(Optional.of(student));
        when(enrolmentService.findByMyEnrolment(student.getStudentNumber())).thenReturn(enrolments);

        mockMvc.perform(get("/evaluations/list")
                .sessionAttr("loggedInStudentEmail", studentEmail))
                .andExpect(status().isOk())
                .andExpect(view().name("student/lecture/lectureEvaluationList"))
                .andExpect(model().attribute("lectureList", enrolments.stream().map(Enrolment::getLectureName).toList()))
                .andExpect(model().attribute("studentNumber", student.getStudentNumber()));
    }
    
    @Test
    public void testShowEvaluationList() throws Exception {
        String studentEmail = "test@student.com";
        Student student = new Student();
        student.setStudentNumber(123);
        List<Evaluation> evaluations = new ArrayList<>();
        Evaluation evaluation = new Evaluation();
        evaluations.add(evaluation);

        when(studentService.findByEnrolmentStudentInfo(studentEmail)).thenReturn(Optional.of(student));
        when(evaluationService.findByMyEvaluation(student.getStudentNumber())).thenReturn(evaluations);

        mockMvc.perform(get("/evaluations/evaluationList")
                .sessionAttr("loggedInStudentEmail", studentEmail))
                .andExpect(status().isOk())
                .andExpect(view().name("student/lecture/myEvaluationList"))
                .andExpect(model().attribute("evaluations", evaluations))
                .andExpect(model().attribute("studentNumber", student.getStudentNumber()));
    }
}