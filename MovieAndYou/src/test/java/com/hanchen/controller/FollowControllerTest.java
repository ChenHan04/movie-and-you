package com.hanchen.controller;

import com.hanchen.service.FollowService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FollowControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FollowService followService;

    @InjectMocks
    private FollowController followController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(followController).build();
    }

    @Test
    public void testFollowUser() throws Exception {
        Long followerId = 1L;
        Long followedId = 2L;

        when(followService.followUser(followerId, followedId)).thenReturn(true);

        mockMvc.perform(post("/api/follows/2")
                        .header("userId", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":200,\"message\":\"关注成功\",\"data\":null}"));
    }

    @Test
    public void testUnfollowUser() throws Exception {
        Long followerId = 1L;
        Long followedId = 2L;

        when(followService.unfollowUser(followerId, followedId)).thenReturn(true);

        mockMvc.perform(delete("/api/follows/2")
                        .header("userId", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":200,\"message\":\"取消关注成功\",\"data\":null}"));
    }
}