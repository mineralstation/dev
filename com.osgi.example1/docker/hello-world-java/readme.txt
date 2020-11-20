How to create Docker Image and run Java App (Spring Boot Jar) in a Docker Engine | Tech Primers
https://www.youtube.com/watch?v=FlSup_eelYE


JDK docker images
https://hub.docker.com/search?q=openjdk&type=image


hello-world-java
------------------------------------------------------------------------
docker build -t hello-world-java .

docker run --rm -it hello-world-java
------------------------------------------------------------------------

Sample:
------------------------------------------------------------------------
root@raspberrypi:/home/pi/docker/hello-world-java# docker build -t hello-world-java .
Sending build context to Docker daemon  3.072kB
Step 1/5 : FROM openjdk:7
7: Pulling from library/openjdk
15cfb32571a9: Pull complete 
284ec6226ea6: Pull complete 
c3f65834a938: Pull complete 
99f145832e9b: Pull complete 
06169c95c7dc: Pull complete 
21e7b223f865: Pull complete 
ce20c1969884: Pull complete 
Digest: sha256:75a05dbcd254fdde1a284c5cc47a8f7d5387cd517cbf9e66d50d45da1c695022
Status: Downloaded newer image for openjdk:7
 ---> d91d1fcddbd1
Step 2/5 : COPY . /myapp
 ---> 229745ec6178
Step 3/5 : WORKDIR /myapp
 ---> Running in 611a07e0f773
Removing intermediate container 611a07e0f773
 ---> b1791d9d1918
Step 4/5 : RUN javac Main.java
 ---> Running in 91bbff641126
Removing intermediate container 91bbff641126
 ---> 9456615cb3d2
Step 5/5 : CMD CMD ["java", "Main"]
 ---> Running in 42dd42d21f69
Removing intermediate container 42dd42d21f69
 ---> 87736c763aec
Successfully built 87736c763aec
Successfully tagged hello-world-java:latest
root@raspberrypi:/home/pi/docker/hello-world-java# 
------------------------------------------------------------------------

