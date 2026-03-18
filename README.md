# 流动摊位共享地图（后端）

技术栈：Java 17 / Spring Boot 3 / Maven / MySQL 8 / MyBatis-Plus / Lombok / Hibernate Validation

## 本地启动

1. 准备 MySQL 8，创建数据库（示例）：

```sql
CREATE DATABASE stall_map DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 修改 `src/main/resources/application.yml` 的数据库连接信息。
3. 启动：

```bash
mvn spring-boot:run
```

启动成功后默认端口：`8080`

## Devbox 环境启动（推荐）

如果你使用 Devbox 管理依赖，请在项目根目录执行：

```bash
devbox shell
```

进入 shell 后启动 MySQL（方式二选一，取决于你是否用 Devbox service）：

```bash
mysql.server start
```

然后运行：

```bash
mvn spring-boot:run
```

当前端口以 `application.yml` 为准（你已配置为 `3000`）。

## 推送到 GitHub（协同开发）

### 重要：不要提交数据库密码

本项目已将数据库账号密码改为环境变量读取：

- `DB_USER`：默认 `root`
- `DB_PASS`：无默认值（需要你在运行环境里设置）

例如：

```bash
export DB_USER=root
export DB_PASS='你的密码'
```

### 初始化仓库并推送

在项目根目录执行：

```bash
git init
git add .
git commit -m "init: spring boot scaffold"
```

在 GitHub 创建空仓库后，绑定远程并推送（推荐 HTTPS）：

```bash
git branch -M main
git remote add origin https://github.com/<你的组织或用户名>/<仓库名>.git
git push -u origin main
```

# Node.js Example Project

This is a simple Node.js server application example that demonstrates basic HTTP server functionality.

## Project Description

This project creates a basic HTTP server that listens on 0.0.0.0:corresponding port and returns a "Hello World!" message. The project supports both development and production environment modes.

## Environment

This project runs on a Debian 12 system with Node.js, which is pre-configured in the Devbox environment. You don't need to worry about setting up Node.js or system dependencies yourself. The development environment includes all necessary tools for building and running Node.js applications. If you need to make adjustments to match your specific requirements, you can modify the configuration files accordingly.

## Project Execution
**Development mode:** For normal development environment, simply enter Devbox and run `bash entrypoint.sh` in the terminal.
**Production mode:** After release, the project will be automatically packaged into a Docker image and deployed according to the `entrypoint.sh` script and command parameters.

Within Devbox, you only need to focus on development - you can trust that everything is application-ready XD


DevBox: Code. Build. Deploy. We've Got the Rest.

With DevBox, you can focus entirely on writing great code while we handle the infrastructure, scaling, and deployment. Seamless development from start to production. 