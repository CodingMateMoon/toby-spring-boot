package cmmoon.tobyspringboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;

public class TobySpringBootApplication {

    public static void main(String[] args) {
        System.out.println("Hello Containerless Standalone Application");
//        new Tomcat().start();

        /*
        $ http -v http://localhost:8080 (HTTPie)
        $ curl -v http://localhost:8080ㄹ
* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
* using HTTP/1.x
> GET / HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.12.1
> Accept: *\/*

 Request completely sent off
 HTTP/1.1 404
                < Content-Type: text/html;charset=utf-8
                < Content-Language: en
                < Content-Length: 683
                < Date: Tue, 19 Aug 2025 06:41:12 GMT
         */
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer();
        webServer.start();
    }
}
