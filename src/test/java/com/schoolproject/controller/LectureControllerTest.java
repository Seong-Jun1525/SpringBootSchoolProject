package com.schoolproject.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.schoolproject.entity.Lecture;
import com.schoolproject.service.LectureService;
import com.schoolproject.service.ProfessorService;

@WebMvcTest(LectureController.class)
public class LectureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LectureService lectureService;

    @Mock
    private ProfessorService professorService;

    @InjectMocks
    private LectureController lectureController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        String loggedInProfessorEmail = "test@professor.com";
        String professorName = "Test Professor";

        when(professorService.findByProfessorName(loggedInProfessorEmail)).thenReturn(professorName);

        mockMvc.perform(get("/lectures/register")
                .sessionAttr("loggedInProfessorEmail", loggedInProfessorEmail))
                .andExpect(status().isOk())
                .andExpect(view().name("professor/lecture/lectureRegister"))
                .andExpect(model().attributeExists("lecture"))
                .andExpect(model().attribute("loggedInProfessorName", professorName))
                .andExpect(model().attribute("loggedInProfessorEmail", loggedInProfessorEmail));
    }

    @Test
    public void testRegisterLecture() throws Exception {
        Lecture lecture = new Lecture();
        lecture.setLectureId(1L);
        lecture.setLectureName("Test Lecture");

        mockMvc.perform(post("/lectures/register")
                .flashAttr("lecture", lecture))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testListLectures() throws Exception {
        List<Lecture> lectures = new ArrayList<>();
        Lecture lecture1 = new Lecture();
        lecture1.setLectureId(1L);
        lecture1.setLectureName("Lecture 1");
        lectures.add(lecture1);

        Lecture lecture2 = new Lecture();
        lecture2.setLectureId(2L);
        lecture2.setLectureName("Lecture 2");
        lectures.add(lecture2);

        when(lectureService.findAll()).thenReturn(lectures);

        mockMvc.perform(get("/lectures/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("professor/lecture/lectureList"))
                .andExpect(model().attribute("lectures", lectures));
    }
}