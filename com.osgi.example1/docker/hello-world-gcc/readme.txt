Intro to Docker using a Raspberry Pi 4
https://www.youtube.com/watch?v=nBwJm0onzeo

hello-world-gcc
-----------------------------------------------------------------
/docker/hello-world-gcc

sudo -s

// build a docker container with tag being "hello-world-gcc" from current dir
docker build -t hello-world-gcc .

docker run --rm -it hello-world-gcc
docker run --rm -dit hello-world-gcc
-----------------------------------------------------------------

Sample:
-----------------------------------------------------------------
pi@raspberrypi:~/docker/hello-world-gcc $ sudo -s
root@raspberrypi:/home/pi/docker/hello-world-gcc# docker build -t hello-world-gcc .
Sending build context to Docker daemon  3.072kB
Step 1/5 : FROM gcc:4.9
4.9: Pulling from library/gcc
e925dd4ffa2a: Pull complete 
c9bfbf7dfc78: Pull complete 
015138dd660d: Pull complete 
d88b2b5023e5: Pull complete 
4d0d77a38079: Pull complete 
996bfab2b29c: Pull complete 
d27243b445c7: Pull complete 
2f949e025be6: Pull complete 
Digest: sha256:6356ef8b29cc3522527a85b6c58a28626744514bea87a10ff2bf67599a7474f5
Status: Downloaded newer image for gcc:4.9
 ---> 69a20488ee66
Step 2/5 : COPY . /myapp
 ---> 966d38bd23bf
Step 3/5 : WORKDIR /myapp
 ---> Running in 7d71a8a2f494
Removing intermediate container 7d71a8a2f494
 ---> 0c4f1752f613
Step 4/5 : RUN gcc -o hello hellow.c
 ---> Running in e726d8dfde89
Removing intermediate container e726d8dfde89
 ---> 5e226f68a082
Step 5/5 : CMD ["./hello"]
 ---> Running in f8f236ae1a49
Removing intermediate container f8f236ae1a49
 ---> a9d18bea43db
Successfully built a9d18bea43db
Successfully tagged hello-world-gcc:latest
root@raspberrypi:/home/pi/docker/hello-world-gcc#
-----------------------------------------------------------------
