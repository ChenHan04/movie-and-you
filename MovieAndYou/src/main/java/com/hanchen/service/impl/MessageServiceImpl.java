package com.hanchen.service.impl;

import com.hanchen.entity.Message;
import com.hanchen.mapper.MessageMapper;
import com.hanchen.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public boolean sendMessage(Message message) {
        return messageMapper.insertMessage(message) > 0;
    }
}