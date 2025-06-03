package com.hanchen.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Moment {
    private Long id;
    private Long userId; // 发布者ID
    private Long movieId; // 关联电影ID（可选）
    private String content; // 文字内容
    private String images; // 图片URL数组（JSON格式）
    private String tags; // 标签数组（JSON格式）
    private Date createTime;
    private int likeCount;
    private int commentCount;
}