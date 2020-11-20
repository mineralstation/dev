How to create Docker Image and run Java App (Spring Boot Jar) in a Docker Engine | Tech Primers
https://www.youtube.com/watch?v=FlSup_eelYE


JDK docker images
https://hub.docker.com/search?q=openjdk&type=image

Java REST service using maven
https://javahungry.blogspot.com/2019/09/spring-boot-hello-world-example-maven-eclipse.html

https://start.spring.io/
------------------------------------------------------------------------
	Project: 			Maven Project
	Language: 			Java
	Spring Boot: 		2.4.0
	Project Metadata
		Group: 			com.oceaneuropa
		Artifact: 		docker-spring-boot
		Name: 			docker-spring-boot
		Description: 	Demo project for Spring Boot
		Package name: 	com.oceaneuropa.docker-spring-boot
		Packaging: 		jar
		Java: 			11
------------------------------------------------------------------------
Press "GENERATE" button to generate and download a "docker-spring-boot.zip" file.


------------------------------------------------------------------------
// docker image for jdk1.8 that can run on raspberry pi (linux/arm/v7)
// https://hub.docker.com/r/frankwolf/rpi-oracle-java8-jdk 
docker pull frankwolf/rpi-oracle-java8-jdk

docker build -t docker-spring-boot .

// 8081 is port on host machine
// 8080 is port inside container
docker run --rm -it -p 8081:8080 yangyang4j/docker-spring-boot
docker run --rm -d -p 8081:8080 yangyang4j/docker-spring-boot
------------------------------------------------------------------------


Browser
------------------------------------------------------------------------
http://192.168.0.22:8081/
http://192.168.0.22:8081/rest/docker/hello
------------------------------------------------------------------------


https://www.techiedelight.com/spring-boot-app-shuts-down-automatically-at-startup/
Add this to pom.xml
------------------------------------------------------------------------
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
------------------------------------------------------------------------
