package cmmoon.tobyspringboot;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

public class TobySpringBootApplication {

    public static void main(String[] args) {
        System.out.println("Hello Containerless Standalone Application");
//        new Tomcat().start();

        /*
        $ http -v http://localhost:8080 (HTTPie)
        $ curl -v http://localhost:8080
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
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class);
        // container에 HelloService 인터페이스를 구현한 bean 찾아서 의존성 주입
        applicationContext.registerBean(SimpleHelloService.class);
        // bean 오브젝트 생성
        applicationContext.refresh();

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("dispacherServlet", new DispatcherServlet(applicationContext)).addMapping("/*");
        });
        /*
$ curl -v "http://localhost:8080/hello?name=test"
* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
* using HTTP/1.x
> GET /hello?name=test HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.12.1
> Accept: *\/*
>
< HTTP/1.1 200
                < Content-Type: text/plain;charset=ISO-8859-1
                < Content-Length: 12
                < Date: Thu, 21 Aug 2025 12:09:44 GMT
                <
                        Hello test
* Connection #0 to host localhost left intact

                */

        webServer.start();
    }
}
