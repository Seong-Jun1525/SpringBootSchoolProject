package com.schoolproject.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.schoolproject.entity.Student;
import com.schoolproject.exception.StudentNotFoundException;
import com.schoolproject.service.StudentService;

import jakarta.servlet.http.HttpSession;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/students/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/studentRegister"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    public void testRegisterStudent() throws Exception {
        Student student = new Student();
        student.setStudentId(1L);
        student.setStudentName("Test Student");
        student.setStudentEmail("test@student.com");
        student.setStudentPw("password");

        mockMvc.perform(post("/students/register")
                .flashAttr("student", student))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        mockMvc.perform(get("/students/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/register"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student student = new Student();
        student.setStudentId(1L);
        student.setStudentName("Updated Student");
        student.setStudentEmail("test@student.com");
        student.setStudentPw("password");

        mockMvc.perform(post("/students/update")
                .flashAttr("student", student))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testListStudents() throws Exception {
        mockMvc.perform(get("/students/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/studentList"))
                .andExpect(model().attributeExists("students"));
    }

    @Test
    public void testShowLoginPage() throws Exception {
        mockMvc.perform(get("/students/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/studentLogin"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().attribute("studentEmail", ""));
    }

    @Test
    public void testLoginSuccess() throws Exception {
        String studentEmail = "test@student.com";
        String studentName = "Test Student";

        Student student = new Student();
        student.setStudentId(1L);
        student.setStudentName(studentName);
        student.setStudentEmail(studentEmail);
        student.setStudentPw("password");

        when(studentService.login(studentEmail, "password")).thenReturn(true);
        when(studentService.findBystudentName(studentEmail)).thenReturn(studentName);

        mockMvc.perform(post("/students/login")
                .flashAttr("student", student)
                .sessionAttr("loggedInStudentEmail", studentEmail))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attribute("loggedInStudentEmail", studentEmail))
                .andExpect(flash().attribute("loggedInStudentName", studentName));
    }

    @Test
    public void testLoginFailure() throws Exception {
        String studentEmail = "test@student.com";

        Student student = new Student();
        student.setStudentId(1L);
        student.setStudentEmail(studentEmail);
        student.setStudentPw("wrong_password");

        when(studentService.login(studentEmail, "wrong_password")).thenReturn(false);

        mockMvc.perform(post("/students/login")
                .flashAttr("student", student)
                .sessionAttr("loggedInStudentEmail", studentEmail))
                .andExpect(status().isOk())
                .andExpect(view().name("student/studentLogin"))
                .andExpect(model().attributeExists("student"))
                .andExpect(model().attribute("studentEmail", ""))
                .andExpect(model().attributeErrorCount("student", 1))
                .andExpect(model().attributeHasFieldErrorCode("student", "studentPw", "NotEmpty"))
                .andExpect(model().attributeExists("org.springframework.validation.BindingResult.student"))
                .andExpect(result -> {
                    Throwable exception = result.getResolvedException();
                    if (!(exception instanceof StudentNotFoundException)) {
                        throw new AssertionError("Expected StudentNotFoundException");
                    }
                });
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/students/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}