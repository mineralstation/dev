Intro to Docker using a Raspberry Pi 4
https://www.youtube.com/watch?v=nBwJm0onzeo

hello-world-node
-----------------------------------------------------------------
sudo -s
s
root@raspberrypi:/home/pi/docker/hello-world-node#

docker build -t simple-node-webserver .

// when accessing raspberry pi's 4000 port, it will access container instance's 80 port.
docker run --rm -it -p 4000:80 simple-node-webserver

// run a second one
docker run --rm -it -p 4001:80 simple-node-webserver
----------------------------------------------------------------- 

Sample:
-----------------------------------------------------------------
root@raspberrypi:/home/pi/docker/node-webserver# docker build -t simple-node-webserver .
Sending build context to Docker daemon  3.072kB
Step 1/5 : FROM node
 ---> 338eedef62b1
Step 2/5 : COPY . /myapp
 ---> 614e4c0cbbe9
Step 3/5 : WORKDIR /myapp
 ---> Running in 72c86a98f8f1
Removing intermediate container 72c86a98f8f1
 ---> 09e598773fce
Step 4/5 : EXPOSE 80
 ---> Running in 0c8345507d97
Removing intermediate container 0c8345507d97
 ---> 16378e374d43
Step 5/5 : CMD ["node","index.js"]
 ---> Running in 21e6d7e9edd8
Removing intermediate container 21e6d7e9edd8
 ---> 4686bdf6370a
Successfully built 4686bdf6370a
Successfully tagged simple-node-webserver:latest

root@raspberrypi:/home/pi/docker/node-webserver# docker run --rm -it -p 4000:80 simple-node-webserver
server is listening on ${port}
-----------------------------------------------------------------


Browser: 
http://192.168.0.22:4000
-----------------------------------------------------------------
Hello Node.js Server! - 2020-11-16T22:16:33.945Z
-----------------------------------------------------------------

http://192.168.0.22:4001
-----------------------------------------------------------------
Hello Node.js Server! - 2020-11-16T22:21:56.683Z
-----------------------------------------------------------------
