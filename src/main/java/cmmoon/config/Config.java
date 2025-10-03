package cmmoon.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/*
컴포넌트 스캔에 대한 범위를 명시하지 않을 경우 같은 패키지 또는 하위 패키지의 클래스들을 대상으로 스캔하는게 기본 설정이기 때문에
다른 패키지에 존재하는 컴포넌트는 스캔 대상에서 제외됨.
 */
@Configuration
public class Config {
    // ServletWebServerFactory, DispatcherServlet 빈으로 등록할 경우 유연한 구성 정보 설정 가능
    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }
}
