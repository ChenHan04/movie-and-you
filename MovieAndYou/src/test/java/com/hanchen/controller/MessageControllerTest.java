package com.hanchen.controller;

import com.hanchen.entity.Message;
import com.hanchen.service.MessageService;
import com.hanchen.utils.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
    }

    @Test
    public void testSendMessage() throws Exception {
        Long fromUserId = 1L;
        Message message = new Message();
        message.setToUserId(2L);
        message.setContent("你好");
        message.setCreateTime(new Date());
        message.setIsRead(false);

        when(messageService.sendMessage(message)).thenReturn(true);

        mockMvc.perform(post("/api/messages/send")
                        .header("userId", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(message)))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":200,\"message\":\"消息发送成功\",\"data\":null}"));
    }
}