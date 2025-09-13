package cmmoon.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/* Bean 오브젝트 팩토리 메서드를 가진 클래스임을 명시. AnnotationConfig를 이용하는 ApplicationContext에 처음으로 등록됨(@Configuration 붙은 클래스)
 */
@Configuration
//해당 클래스가 속한 패키지부터 시작해서 하위 패키지를 전부 탐색해서 컴포넌트가 붙은 클래스들을 전부 빈으로 등록
@ComponentScan
//@SpringBootApplication
public class TobySpringBootApplication {
    // ServletWebServerFactory, DispatcherServlet 빈으로 등록할 경우 유연한 구성 정보 설정 가능
    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    public static void main(String[] args) {
        // Configuration, ComponantScan 팩토리 메서드를 가지고 스프링 컨테이너에게 애플리케이션 구성을 어떻게 할 것인가에 대한 정보를 가진 클래스
        MySpringApplication.run(TobySpringBootApplication.class, args);
//        SpringApplication.run(TobySpringBootApplication.class, args);
    }
}
