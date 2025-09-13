package cmmoon.tobyspringboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> applicationClass, String... args) {
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
//        applicationContext.register(TobySpringBootApplication.class);
        applicationContext.register(applicationClass);
        // bean 오브젝트 생성
        applicationContext.refresh();
    }
}
