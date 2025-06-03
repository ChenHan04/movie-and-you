package com.hanchen.controller;

import com.hanchen.entity.Moment;
import com.hanchen.service.MomentService;
import com.hanchen.utils.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MomentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MomentService momentService;

    @InjectMocks
    private MomentController momentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(momentController).build();
    }

    @Test
    public void testPublishMomentSuccess() throws Exception {
        Moment moment = new Moment();
        moment.setUserId(1L);
        moment.setContent("Test moment");

        when(momentService.publishMoment(moment)).thenReturn(1);

        mockMvc.perform(post("/api/moments/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(moment)))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":200,\"message\":\"发布成功\",\"data\":null}"));
    }

    @Test
    public void testPublishMomentFailure() throws Exception {
        Moment moment = new Moment();
        moment.setUserId(1L);
        moment.setContent("Test moment");

        when(momentService.publishMoment(moment)).thenReturn(0);

        mockMvc.perform(post("/api/moments/publish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(moment)))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":500,\"message\":\"发布失败\",\"data\":null}"));
    }

    @Test
    public void testGetMomentsByUserId() throws Exception {
        Moment moment = new Moment();
        moment.setId(1L);
        moment.setUserId(1L);
        moment.setContent("Test moment");
        List<Moment> moments = Arrays.asList(moment);

        when(momentService.getMomentsByUserId(1L)).thenReturn(moments);

        mockMvc.perform(post("/api/moments/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\":200,\"message\":\"成功\",\"data\":[{\"id\":1,\"userId\":1,\"movieId\":null,\"content\":\"Test moment\",\"images\":null,\"tags\":null,\"createTime\":null,\"likeCount\":0,\"commentCount\":0}]}"));
    }
}