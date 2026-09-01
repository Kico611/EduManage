package com.kristijanbalic.edumanage.security;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TestSecurityController.class)
@Import(SecurityConfig.class)
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    /*
     * SecurityConfig zahtijeva ovaj bean kroz constructor.
     * Za ove testove nam njegova prava logika nije potrebna.
     */
    @MockBean
    private CustomAuthenticationSuccessHandler successHandler;


    // =========================
    // ADMIN
    // =========================

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void adminShouldAccessStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void adminShouldAccessProfesors() throws Exception {
        mockMvc.perform(get("/profesors"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void adminShouldAccessCourses() throws Exception {
        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void adminShouldAccessUpisi() throws Exception {
        mockMvc.perform(get("/upisi"))
                .andExpect(status().isOk());
    }


    // =========================
    // PROFESSOR
    // =========================

    @Test
    @WithMockUser(username = "professor", roles = "PROFESSOR")
    void professorShouldAccessCourses() throws Exception {
        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "professor", roles = "PROFESSOR")
    void professorShouldAccessUpisi() throws Exception {
        mockMvc.perform(get("/upisi"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "professor", roles = "PROFESSOR")
    void professorShouldNotAccessStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "professor", roles = "PROFESSOR")
    void professorShouldNotAccessProfesors() throws Exception {
        mockMvc.perform(get("/profesors"))
                .andExpect(status().isForbidden());
    }


    // =========================
    // STUDENT
    // =========================

    @Test
    @WithMockUser(username = "IB001", roles = "STUDENT")
    void studentShouldAccessStudentDashboard() throws Exception {
        mockMvc.perform(get("/student/dashboard"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "IB001", roles = "STUDENT")
    void studentShouldNotAccessStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "IB001", roles = "STUDENT")
    void studentShouldNotAccessProfesors() throws Exception {
        mockMvc.perform(get("/profesors"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "IB001", roles = "STUDENT")
    void studentShouldNotAccessCourses() throws Exception {
        mockMvc.perform(get("/courses"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "IB001", roles = "STUDENT")
    void studentShouldNotAccessUpisi() throws Exception {
        mockMvc.perform(get("/upisi"))
                .andExpect(status().isForbidden());
    }


    // =========================
    // API
    // =========================

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void adminShouldAccessApi() throws Exception {
        mockMvc.perform(get("/api/test"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "professor", roles = "PROFESSOR")
    void professorShouldAccessApi() throws Exception {
        mockMvc.perform(get("/api/test"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "IB001", roles = "STUDENT")
    void studentShouldAccessApi() throws Exception {
        mockMvc.perform(get("/api/test"))
                .andExpect(status().isOk());
    }

    @Test
    void unauthenticatedUserShouldNotAccessApi() throws Exception {
        mockMvc.perform(get("/api/test"))
                .andExpect(status().is3xxRedirection());
    }


    // =========================
    // GENERAL
    // =========================

    @Test
    @WithMockUser(username = "professor", roles = "PROFESSOR")
    void authenticatedUserShouldAccessHome() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk());
    }

    @Test
    void unauthenticatedUserShouldNotAccessHome() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void unauthenticatedUserShouldAccessLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    void unauthenticatedUserShouldNotAccessStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(status().is3xxRedirection());
    }
}