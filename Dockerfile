FROM openjdk:11-jre-slim

# 작업 디렉토리를 /app으로 설정합니다.
WORKDIR /app

COPY src /app/src

# 현재 디렉토리의 jar 파일을 컨테이너의 /app 디렉토리로 복사합니다.
COPY target/application-1.0-SNAPSHOT-jar-with-dependencies.jar /app/target/application-1.0-SNAPSHOT-jar-with-dependencies.jar

COPY src/main/resources/database /app/src/main/resources/database

# jar 파일 실행 명령어를 지정합니다.
CMD ["java", "-jar", "target/application-1.0-SNAPSHOT-jar-with-dependencies.jar"]