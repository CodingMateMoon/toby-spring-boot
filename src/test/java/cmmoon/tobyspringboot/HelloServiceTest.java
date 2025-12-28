package cmmoon.tobyspringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest {
}

@Retention(RetentionPolicy.RUNTIME)
// ANNOTATION_TYPE을 주고 다른 애노테이션의 메타 애노테이션으로 활용
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Test
@interface UnitTest {
}

public class HelloServiceTest {
    @UnitTest
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService(new HelloRepository() {
            @Override
            public Hello findHello(String name) {
                return null;
            }

            @Override
            public void increaseCount(String name) {

            }
        });
/*
관심사는 DB 액세스 보다 Service의 sayHello 함수. 의존 오브젝트
 */
        String ret = helloService.sayHello("Test");
        Assertions.assertThat(ret).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator() {
/*
        HelloDecorator decorator = new HelloDecorator(new HelloService() {
            @Override
            public String sayHello(String name) {
                return name;
            }
        });
*/
        HelloDecorator decorator = new HelloDecorator(name -> name);
        String ret = decorator.sayHello("Test");
        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}
