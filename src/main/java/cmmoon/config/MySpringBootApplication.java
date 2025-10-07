package cmmoon.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 런타임까지 애노테이션 정보가 유지되도록 설정
@Retention(RetentionPolicy.RUNTIME)
// 클래스, 인터페이스, enum 등에 적용할 때 사용
@Target(ElementType.TYPE)
@Configuration
@ComponentScan
@EnableMyAutoConfiguration
public @interface MySpringBootApplication {
}
