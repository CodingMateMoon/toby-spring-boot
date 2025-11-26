package cmmoon.config.autoconfig;

import cmmoon.config.ConditionalMyOnClass;
import cmmoon.config.MyAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
//@Conditional(TomcatWebServerConfig.TomcatCondition.class)
public class TomcatWebServerConfig {

    @Value("${contextPath}")
    String contextPath;

    // ServletWebServerFactory, DispatcherServlet 빈으로 등록할 경우 유연한 구성 정보 설정 가능
    @Bean("tomcatWebServerFactory")
    /*
    유저 구성 정보 로딩 후 자동 구성 정보 로딩
    같은 타입의 빈이 없는 경우에만 등록
     */
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(Environment env) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        // 모든 서블릿 매핑 앞에 해당 path가 붙음
        // curl -v "http://localhost:8080/app/hello?name=test"
        System.out.println("contextPath : " + this.contextPath);
        factory.setContextPath(this.contextPath);
//        factory.setContextPath(env.getProperty("contextPath"));
//        factory.setContextPath("/app");
        return factory;
    }


/*
    static class TomcatCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat", context.getClassLoader());
        }
    }
*/
}
