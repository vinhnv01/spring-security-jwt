
# khai báo image gốc cho Dockerfile
FROM openjdk:17-jdk-alpine
# khai báo rằng container sẽ lắng nghe kết nối đến cổng 8080
#EXPOSE 8080
# gán giá trị là đường dẫn tới file JAR của ứng dụng
ARG JAR_FILE=target/user-managerment-0.0.1-SNAPSHOT.jar
# Lệnh này thêm file JAR của ứng dụng vào container
ADD ${JAR_FILE} user-managerment.jar
ENTRYPOINT ["java","-jar","/user-managerment.jar"]