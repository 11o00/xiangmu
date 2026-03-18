# 阶段 1：构建
FROM openjdk:17-jdk-slim AS builder
WORKDIR /app

# 复制 Maven 配置文件
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# 下载依赖（利用 Docker 缓存层）
RUN ./mvnw dependency:go-offline -B

# 复制源代码
COPY src src

# 构建项目（跳过测试）
RUN ./mvnw clean package -DskipTests

# 阶段 2：运行
FROM openjdk:17-jdk-slim
WORKDIR /app

# 从构建阶段复制 jar 文件
COPY --from=builder /app/target/*.jar app.jar

# 暴露端口
EXPOSE 3000

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]
