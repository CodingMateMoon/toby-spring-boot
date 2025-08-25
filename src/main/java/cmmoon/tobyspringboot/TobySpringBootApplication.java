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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

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
            servletContext.addServlet("frontcontroller", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    /*
                    인증, 보안, 다국어, 공통 기능
                    Request: Method, Path
                     */
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");
                        resp.setStatus(HttpStatus.OK.value());
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println("Hello " + name);
                    } else if (req.getRequestURI().equals("/user")) {

                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            }).addMapping("/*");
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
