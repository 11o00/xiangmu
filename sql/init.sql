-- 流动摊位共享地图数据库初始化脚本
-- 创建数据库（如果不存在）
CREATE DATABASE IF NOT EXISTS stall_map CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE stall_map;

-- 用户表
CREATE TABLE IF NOT EXISTS t_user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    wx_openid VARCHAR(100) UNIQUE COMMENT '微信 openId',
    nickname VARCHAR(100) COMMENT '昵称',
    avatar_url VARCHAR(500) COMMENT '头像 URL',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 摊主表
CREATE TABLE IF NOT EXISTS t_vendor (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL COMMENT '关联用户ID',
    real_name VARCHAR(100) COMMENT '真实姓名',
    phone VARCHAR(20) COMMENT '联系电话',
    id_card VARCHAR(18) COMMENT '身份证号',
    status TINYINT DEFAULT 0 COMMENT '状态：0-待审核，1-已通过，2-已拒绝',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES t_user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='摊主表';

-- 摊位表
CREATE TABLE IF NOT EXISTS t_stall (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    vendor_id BIGINT NOT NULL COMMENT '摊主ID',
    name VARCHAR(200) NOT NULL COMMENT '摊位名称',
    category VARCHAR(50) COMMENT '分类',
    lat DECIMAL(10, 8) COMMENT '纬度',
    lng DECIMAL(11, 8) COMMENT '经度',
    address VARCHAR(500) COMMENT '详细地址',
    description TEXT COMMENT '摊位描述',
    online BOOLEAN DEFAULT TRUE COMMENT '是否营业中',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (vendor_id) REFERENCES t_vendor(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='摊位表';

-- 关注表
CREATE TABLE IF NOT EXISTS t_follow (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL COMMENT '用户ID',
    stall_id BIGINT NOT NULL COMMENT '摊位ID',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES t_user(id),
    FOREIGN KEY (stall_id) REFERENCES t_stall(id),
    UNIQUE KEY uk_user_stall (user_id, stall_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='关注表';

-- 创建索引
CREATE INDEX idx_user_wx_openid ON t_user(wx_openid);
CREATE INDEX idx_vendor_user_id ON t_vendor(user_id);
CREATE INDEX idx_stall_vendor_id ON t_stall(vendor_id);
CREATE INDEX idx_stall_category ON t_stall(category);
CREATE INDEX idx_stall_online ON t_stall(online);
CREATE INDEX idx_stall_location ON t_stall(lat, lng);
CREATE INDEX idx_follow_user_id ON t_follow(user_id);
CREATE INDEX idx_follow_stall_id ON t_follow(stall_id);

-- 插入测试数据
INSERT INTO t_user (wx_openid, nickname, avatar_url) VALUES
('test_openid_1', '测试用户1', 'https://example.com/avatar1.jpg'),
('test_openid_2', '测试用户2', 'https://example.com/avatar2.jpg');

INSERT INTO t_vendor (user_id, real_name, phone, status) VALUES
(1, '张三', '13800138000', 1),
(2, '李四', '13900139000', 1);

INSERT INTO t_stall (vendor_id, name, category, lat, lng, address, description, online) VALUES
(1, '张记烧烤', '美食', 39.9042, 116.4074, '北京市朝阳区测试路1号', '正宗烧烤，味道好', TRUE),
(1, '张记水果摊', '水果', 39.9050, 116.4080, '北京市朝阳区测试路2号', '新鲜水果，价格实惠', TRUE),
(2, '李记小吃', '美食', 39.9030, 116.4060, '北京市朝阳区测试路3号', '传统小吃，回味无穷', TRUE);
