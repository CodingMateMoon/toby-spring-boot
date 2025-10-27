package cmmoon.config.autoconfig;

import cmmoon.config.MyAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@MyAutoConfiguration
public class TomcatWebServerConfig {
    // ServletWebServerFactory, DispatcherServlet 빈으로 등록할 경우 유연한 구성 정보 설정 가능
    @Bean("TomcatWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }
}
