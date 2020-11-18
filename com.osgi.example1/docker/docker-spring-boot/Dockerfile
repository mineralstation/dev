# FROM openjdk:8
FROM frankwolf/rpi-oracle-java8-jdk

# ADD target/docker-spring-boot.jar docker-spring-boot.jar
COPY . /myapp
WORKDIR /myapp
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-spring-boot.jar"]