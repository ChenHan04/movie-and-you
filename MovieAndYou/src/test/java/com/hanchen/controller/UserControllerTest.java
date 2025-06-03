package com.hanchen.controller;

import com.hanchen.entity.User;
import com.hanchen.service.UserService;
import com.hanchen.utils.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        when(userService.register(user)).thenReturn(1);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":200,\"message\":\"注册成功\",\"data\":null}"));
    }

    @Test
    public void testRegisterFailure() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        when(userService.register(user)).thenReturn(0);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":500,\"message\":\"注册失败\",\"data\":null}"));
    }

    @Test
    public void testLoginSuccess() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        User dbUser = new User();
        dbUser.setId(1L);
        dbUser.setUsername("testuser");
        dbUser.setPassword("encodedpassword");

        when(userService.login(user.getUsername(), user.getPassword())).thenReturn(dbUser);

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\":200,\"message\":\"成功\",\"data\":{\"id\":1,\"username\":\"testuser\",\"password\":\"encodedpassword\",\"nickname\":null,\"avatarUrl\":null,\"createTime\":null}}"));
    }

    @Test
    public void testLoginFailure() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        when(userService.login(user.getUsername(), user.getPassword())).thenReturn(null);

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":500,\"message\":\"用户名或密码错误\",\"data\":null}"));
    }
}