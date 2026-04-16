-- 创建数据库
CREATE DATABASE IF NOT EXISTS stall_map CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE stall_map;

-- 基础实体表（包含公共字段）
CREATE TABLE IF NOT EXISTS t_admin (
    id BIGINT NOT NULL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    status INT DEFAULT 1,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS t_user (
    id BIGINT NOT NULL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    wx_openid VARCHAR(100),
    nickname VARCHAR(50),
    avatar_url VARCHAR(255),
    gender INT DEFAULT 0,
    birthday VARCHAR(20),
    signature VARCHAR(255),
    status INT DEFAULT 1,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS t_vendor (
    id BIGINT NOT NULL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    display_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES t_user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS t_stall (
    id BIGINT NOT NULL PRIMARY KEY,
    vendor_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    lat DECIMAL(10, 6) NOT NULL,
    lng DECIMAL(10, 6) NOT NULL,
    online BOOLEAN DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (vendor_id) REFERENCES t_vendor(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS t_review (
    id BIGINT NOT NULL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    stall_id BIGINT NOT NULL,
    rating INT NOT NULL,
    content TEXT,
    images TEXT,
    status INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES t_user(id),
    FOREIGN KEY (stall_id) REFERENCES t_stall(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS t_system_settings (
    id BIGINT NOT NULL PRIMARY KEY,
    `key` VARCHAR(100) NOT NULL UNIQUE,
    `value` TEXT NOT NULL,
    description VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 创建摊位图片表
CREATE TABLE IF NOT EXISTS t_stall_image (
    id BIGINT NOT NULL PRIMARY KEY,
    stall_id BIGINT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    sort INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (stall_id) REFERENCES t_stall(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 插入初始数据
-- 管理员数据（密码：123456，明文）
INSERT INTO t_admin (id, username, password, name, phone, status) VALUES
(1, 'admin', '123456', '管理员', '13800138000', 1);

-- 系统设置初始数据
INSERT INTO t_system_settings (id, `key`, `value`, description) VALUES
(1, 'system_name', '流动摊位共享平台', '系统名称'),
(2, 'system_version', '1.0.0', '系统版本'),
(3, 'max_stall_images', '9', '摊位最大图片数量'),
(4, 'review_audit', 'true', '评价是否需要审核'),
(5, 'vendor_audit', 'true', '摊主是否需要审核'),
(6, 'stall_audit', 'true', '摊位是否需要审核');
