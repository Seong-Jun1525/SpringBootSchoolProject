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

import com.schoolproject.entity.Professor;
import com.schoolproject.exception.ProfessorLoginException;
import com.schoolproject.service.ProfessorService;

import jakarta.servlet.http.HttpSession;

@WebMvcTest(ProfessorController.class)
public class ProfessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProfessorService professorService;

    @InjectMocks
    private ProfessorController professorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/professors/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("professor/professorRegister"))
                .andExpect(model().attributeExists("professor"));
    }

    @Test
    public void testRegisterProfessor() throws Exception {
        Professor professor = new Professor();
        professor.setProfessorId(1L);
        professor.setProfessorName("Test Professor");
        professor.setProfessorEmail("test@professor.com");
        professor.setProfessorPw("password");

        mockMvc.perform(post("/professors/register")
                .flashAttr("professor", professor))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testShowLoginPage() throws Exception {
        mockMvc.perform(get("/professors/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("professor/professorLogin"))
                .andExpect(model().attributeExists("professor"))
                .andExpect(model().attribute("professorEmail", ""));
    }

    @Test
    public void testLoginSuccess() throws Exception {
        String professorEmail = "test@professor.com";
        String professorName = "Test Professor";

        Professor professor = new Professor();
        professor.setProfessorId(1L);
        professor.setProfessorName(professorName);
        professor.setProfessorEmail(professorEmail);
        professor.setProfessorPw("password");

        when(professorService.login(professorEmail, "password")).thenReturn(true);
        when(professorService.findByProfessorName(professorEmail)).thenReturn(professorName);

        mockMvc.perform(post("/professors/login")
                .flashAttr("professor", professor)
                .sessionAttr("loggedInProfessorEmail", professorEmail))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attribute("loggedInProfessorEmail", professorEmail))
                .andExpect(flash().attribute("loggedInProfessorName", professorName));
    }

    @Test
    public void testLoginFailure() throws Exception {
        String professorEmail = "test@professor.com";

        Professor professor = new Professor();
        professor.setProfessorId(1L);
        professor.setProfessorEmail(professorEmail);
        professor.setProfessorPw("wrong_password");

        when(professorService.login(professorEmail, "wrong_password")).thenReturn(false);

        mockMvc.perform(post("/professors/login")
                .flashAttr("professor", professor)
                .sessionAttr("loggedInProfessorEmail", professorEmail))
                .andExpect(status().isOk())
                .andExpect(view().name("professor/professorLogin"))
                .andExpect(model().attributeExists("professor"))
                .andExpect(model().attribute("professorEmail", ""))
                .andExpect(model().attributeErrorCount("professor", 1))
                .andExpect(model().attributeHasFieldErrorCode("professor", "professorPw", "NotEmpty"))
                .andExpect(model().attributeExists("org.springframework.validation.BindingResult.professor"))
                .andExpect(result -> {
                    Throwable exception = result.getResolvedException();
                    if (!(exception instanceof ProfessorLoginException)) {
                        throw new AssertionError("Expected ProfessorLoginException");
                    }
                });
    }

    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(get("/professors/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testListProfessors() throws Exception {
        mockMvc.perform(get("/professors/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("professor/professorList"))
                .andExpect(model().attributeExists("professors"));
    }
}