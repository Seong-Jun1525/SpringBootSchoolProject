package com.schoolproject.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.schoolproject.entity.Lecture;
import com.schoolproject.service.EnrolmentService;
import com.schoolproject.service.LectureService;
import com.schoolproject.service.StudentService;

@WebMvcTest(EnrolmentController.class)
public class EnrolmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LectureService lectureService;

    @MockBean
    private StudentService studentService;

    @MockBean
    private EnrolmentService enrolmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowEnrolmentPage() throws Exception {
        mockMvc.perform(get("/enrolments/lectureList"))
            .andExpect(status().isOk())
            .andExpect(view().name("student/enrolment/lectureList"))
            .andExpect(model().attributeExists("currentYear"))
            .andExpect(model().attributeExists("lecture"));
    }

    @Test
    public void testSearchLectures() throws Exception {
        List<Lecture> lectures = new ArrayList<>();
        when(lectureService.findBySearchEnrolmentLecture(new Lecture())).thenReturn(lectures);

        mockMvc.perform(post("/enrolments/search")
                .flashAttr("lecture", new Lecture()))
            .andExpect(status().isOk())
            .andExpect(view().name("student/enrolment/lectureResults :: lectureResultsFragment"))
            .andExpect(model().attribute("lectures", lectures));
    }

    @Test
    public void testShowMyEnrolmentPage() throws Exception {
        mockMvc.perform(get("/enrolments/enrolmentList"))
            .andExpect(status().isOk())
            .andExpect(view().name("student/enrolment/enrolmentList"))
            .andExpect(model().attributeExists("enrolment"));
    }

    @Test
    public void testDeleteEnrolment() throws Exception {
        mockMvc.perform(post("/enrolments/delete/1"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/"))
            .andExpect(flash().attributeExists("message"));
    }
}