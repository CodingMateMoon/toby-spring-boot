package cmmoon.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/* Bean 오브젝트 팩토리 메서드를 가진 클래스임을 명시. AnnotationConfig를 이용하는 ApplicationContext에 처음으로 등록됨(@Configuration 붙은 클래스)
 */
@Configuration
//해당 클래스가 속한 패키지부터 시작해서 하위 패키지를 전부 탐색해서 컴포넌트가 붙은 클래스들을 전부 빈으로 등록
@ComponentScan
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
        /*
        자바 코드로 만든 Configuration 정보를 읽기 위한 클래스로 변경
        curl -v "http://localhost:8080/hello?name=test"
         */
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
            @Override
            protected void onRefresh() {
                super.onRefresh();
//                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                dispatcherServlet.setApplicationContext(this);
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet", dispatcherServlet).addMapping("/*");
                });
                webServer.start();
            }
        };
        applicationContext.register(TobySpringBootApplication.class);
        // bean 오브젝트 생성
        applicationContext.refresh();

    }
}
