-- 用户表
CREATE TABLE user_info (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           username VARCHAR(30) UNIQUE NOT NULL COMMENT '用户名（唯一）',
                           password VARCHAR(100) NOT NULL COMMENT '加密密码',
                           nickname VARCHAR(50) COMMENT '昵称',
                           avatar_url VARCHAR(200) DEFAULT 'https://example.com/default_avatar.png' COMMENT '头像地址',
                           create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 电影表
CREATE TABLE movie_base (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            title_cn VARCHAR(100) NOT NULL COMMENT '中文名',
                            title_en VARCHAR(100) COMMENT '英文名',
                            director VARCHAR(50) COMMENT '导演',
                            actors VARCHAR(500) COMMENT '主演（JSON数组）',
                            douban_score DECIMAL(3,1) COMMENT '豆瓣评分',
                            poster_url VARCHAR(200) COMMENT '海报地址',
                            genres VARCHAR(100) COMMENT '类型',
                            release_date DATE COMMENT '上映日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 动态表
CREATE TABLE moment (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT NOT NULL COMMENT '发布者ID',
                        movie_id BIGINT COMMENT '关联电影ID（可选）',
                        content TEXT COMMENT '内容正文',
                        images VARCHAR(500) COMMENT '图片地址（JSON数组）',
                        tags VARCHAR(200) COMMENT '话题标签（JSON数组）',
                        create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
                        like_count INT DEFAULT 0 COMMENT '点赞数',
                        comment_count INT DEFAULT 0 COMMENT '评论数',
                        FOREIGN KEY (user_id) REFERENCES user_info(id),
                        FOREIGN KEY (movie_id) REFERENCES movie_base(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- 插入测试用户
INSERT INTO user_info (username, password, nickname)
VALUES ('user1', '$2a$10$eZ65JZ6JZpZJZpZJZpZJ.ZH1lCw95aGv7kYI2X1aC7kYI2X1aC7', '小明'); -- 密码明文：123456（BCrypt加密后）

-- 插入测试电影
INSERT INTO movie_base (title_cn, director, douban_score, poster_url)
VALUES ('奥本海默', '诺兰', 8.8, 'https://example.com/oppenheimer.jpg');

-- 插入测试动态
INSERT INTO moment (user_id, movie_id, content, tags)
VALUES (1, 1, '诺兰的神作！', '["诺兰", "科幻", "传记"]');