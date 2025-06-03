package com.hanchen.controller;

import com.hanchen.entity.Message;
import com.hanchen.service.MessageService;
import com.hanchen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
@CrossOrigin
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public Result sendMessage(@RequestHeader("userId") Long fromUserId, @RequestBody Message message) {
        message.setFromUserId(fromUserId);
        message.setCreateTime(new Date());
        message.setIsRead(false);
        boolean success = messageService.sendMessage(message);
        return success ? Result.success("消息发送成功") : Result.error("消息发送失败");
    }
}