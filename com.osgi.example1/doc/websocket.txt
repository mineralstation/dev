jar download:
https://mvnrepository.com/artifact/javax.websocket

example code:
(1) http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/HomeWebsocket/WebsocketHome.html

Deploying WebSocket annotated & programmatic server endpoints together
(using javax.server.ServerApplicationConfig)
(2) https://abhirockzz.wordpress.com/2016/10/20/deploying-websocket-annotated-programmatic-server-endpoints-together/

using ServerApplicationConfig (for server) and ClientEndpointConfig (for client)
(3) https://dzone.com/articles/programmatic-websocket-endpoints-in-java-ee-7

(4) https://tyrus-project.github.io/documentation/1.13.1/index/websocket-api.html

Apache Tomcat Websocket Tutorial
(5) https://examples.javacodegeeks.com/enterprise-java/tomcat/apache-tomcat-websocket-tutorial/


JSR 356, Java API for WebSocket
http://www.oracle.com/technetwork/articles/java/jsr356-1937161.html

Failover and Replication in a Cluster
https://docs.oracle.com/cd/E13222_01/wls/docs90/cluster/failover.html#1022034

Using websocket with Jetty 
https://examples.javacodegeeks.com/enterprise-java/jetty/jetty-websocket-example/
https://github.com/jetty-project/embedded-jetty-websocket-examples

Use ping/pong to maintain keep connection alive
https://stackoverflow.com/questions/26971026/handling-connection-loss-with-websockets

About bundle.info file
https://www.eclipse.org/virgo/documentation/virgo-documentation-3.6.4.RELEASE/docs/virgo-user-guide/html/ch13.html#configuring-framework-bundles

<bsn>,<version>,<jar-location>,<start-level>,<toStart?>

bsn - the bundle's symbolic name string
version - the bundle's version string
jar-location - relative or absolute path to the jar file
start-level - a digit indicating the bundle's start level
toStart? - true or false value indicating whether a bundle should be started or not



Import-Package: 
javax.servlet;version="[2.6.0,3.2)",
javax.servlet.annotation;version="[2.6.0,3.2)",
javax.servlet.http;version="[2.6.0,3.2)",
org.eclipse.jetty.http.pathmap;version="[9.3.21,9.3.22)",
org.eclipse.jetty.server;version="[9.3.21,9.3.22)",
org.eclipse.jetty.server.handler;version="[9.3.21,9.3.22)",
org.eclipse.jetty.servlet;version="[9.3.21,9.3.22)",
org.eclipse.jetty.util;version="[9.3.21,9.3.22)",
org.eclipse.jetty.util.log;version="[9.3.21,9.3.22)",
org.eclipse.jetty.util.thread;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.api;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.api.extensions;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.api.util;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.common;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.common.events;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.common.events.annotated;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.common.scopes;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.jsr356;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.jsr356.annotations;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.jsr356.client;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.jsr356.endpoints;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.jsr356.metadata;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.jsr356.server;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.server;version="[9.3.21,9.3.22)",
org.eclipse.jetty.websocket.servlet;version="[9.3.21,9.3.22)",
javax.websocket;version="[1.0,2)",
javax.websocket.server;version="[1.0,2)"

Require-Capability: 
osgi.extender;filter:="(osgi.extender=osgi.serviceloader.registrar)";resolution:=optional,
osgi.ee;filter:="(&(osgi.ee=JavaSE)(version=1.8))"



