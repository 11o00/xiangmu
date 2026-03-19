# 流动摊位共享项目

## 项目简介
流动摊位共享地图后端服务，基于Java 17、Spring Boot 3、MySQL 8等技术栈。

## 技术栈
- Java 17
- Spring Boot 3
- MyBatis-Plus
- MySQL 8
- Docker

## 环境要求
- Java 17+
- Maven 3.9+
- MySQL 8+

## 启动方法
1. 克隆仓库：`git clone https://github.com/11o00/xiangmu.git`
2. 进入项目目录：`cd xiangmu`
3. 下载依赖：`mvn clean install`
4. 启动项目：`mvn spring-boot:run`
5. 访问地址：`http://localhost:3000`

## 数据库配置
- 外部数据库地址：`dbconn.sealoshzh.site:39616`
- 用户名：`root`
- 密码：`nnkjw875`

## 项目结构
- `src/main/java`：Java源代码
- `src/main/resources`：配置文件
- `docker-compose.yml`：Docker部署配置

## 团队协作
1. 创建功能分支：`git checkout -b feature/your-feature`
2. 开发完成后提交：`git add . && git commit -m "描述" && git push origin feature/your-feature`
3. 创建Pull Request合并到main分支