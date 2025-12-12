package cmmoon.tobyspringboot;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TobySpringBootApplication.class)
// application.properties 등록하는 건 스프링 프레임워크에서 기본으로 해주는게 아니라
// 스프링 부트의 초기화 과정에서 추가되기 때문에 별도로 읽어오는 작업을 정의해야함
@TestPropertySource("classpath:/application.properties")
@Transactional
public @interface HelloBootTest {
}
