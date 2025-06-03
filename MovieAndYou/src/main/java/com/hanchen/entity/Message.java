package com.hanchen.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Message {
    private Long id;
    private Long fromUserId;
    private Long toUserId;
    private String content;
    private Date createTime;
    private boolean isRead;
    public void setIsRead( boolean isRead) {
        this.isRead = isRead;
    }






}