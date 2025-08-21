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
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
/*
        WebServer webServer = serverFactory.getWebServer(new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {

            }
        });
*/
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("hello", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    resp.setStatus(200);
                    resp.setHeader("Content-Type", "text/plain");
                    resp.getWriter().println("Hello Servlet");
                }
            }).addMapping("/hello");
        });
        /*
        $ curl -v http://localhost:8080/hello
* Host localhost:8080 was resolved.
* IPv6: ::1
* IPv4: 127.0.0.1
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
* using HTTP/1.x
> GET /hello HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.12.1
> Accept: *\/*
>
< HTTP/1.1 200
                < Content-Type: text/plain;charset=ISO-8859-1
                < Content-Length: 15
                < Date: Wed, 20 Aug 2025 06:53:24 GMT
                <
                        Hello Servlet
* Connection #0 to host localhost left intact


                */

        webServer.start();
    }
}
