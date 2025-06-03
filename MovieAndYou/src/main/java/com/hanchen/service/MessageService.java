package com.hanchen.service;

import com.hanchen.entity.Message;

public interface MessageService {
    boolean sendMessage(Message message);
}