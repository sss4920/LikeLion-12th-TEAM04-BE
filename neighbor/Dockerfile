# baseimage  ->  컨테이너의 내 에플리케이션이 돌아갈 수 있는 환경을 제공해주는 이미지
FROM amd64/amazoncorretto:17

# baseimage를 바탕으로 다음 설정들을 진행하게 된다.

# RUN, CMD, ENTRYPOINT 등 명령어들이 실행될 컨테이너 속 작업 디렉토리 설정
WORKDIR /app

# host machine의 파일/디렉토리를 컨테이너 내 경로에 복사
COPY ./build/libs/neighbor-0.0.1-SNAPSHOT.jar /app/neighbor.jar

# 컨테이너가 실행될 때 실행할 명령어 지정
CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=prod", "neighbor.jar"]
