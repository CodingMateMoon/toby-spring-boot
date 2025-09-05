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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

/* Bean 오브젝트 팩토리 메서드를 가진 클래스임을 명시. AnnotationConfig를 이용하는 ApplicationContext에 처음으로 등록됨(@Configuration 붙은 클래스)
 */
@Configuration
public class TobySpringBootApplication {

    @Bean
    public HelloController helloController(HelloService helloService){
        return new HelloController(helloService);
    }

    // 스프링 컨테이너의 Bean 오브젝트를 만드는 팩토리 메서드임을 명시
    @Bean
    public HelloService helloService(){
        return new SimpleHelloService();
    }

    public static void main(String[] args) {
        /*
        자바 코드로 만든 Configuration 정보를 읽기 위한 클래스로 변경
         */
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
            @Override
            protected void onRefresh() {
                super.onRefresh();
                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this)).addMapping("/*");
                });
                webServer.start();
            }
        };
        applicationContext.register(TobySpringBootApplication.class);
        // bean 오브젝트 생성
        applicationContext.refresh();

    }
}
