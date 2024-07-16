FROM amazoncorretto:17
LABEL name=app developer=NguyenHoangSon
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=test"]