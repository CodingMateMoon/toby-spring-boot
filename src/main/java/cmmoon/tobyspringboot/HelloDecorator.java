package cmmoon.tobyspringboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'cmmoon.tobyspringboot.HelloService' available: expected single matching bean but found 2: helloDecorator,simpleHelloService
@Service
// HelloController가 필요한 빈이 2개일 때 @Primary 붙은 빈을 우선적으로 주입
@Primary
public class HelloDecorator implements HelloService{
    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return "*" + helloService.sayHello(name) + "*";
    }

    @Override
    public int countOf(String name) {
        return helloService.countOf(name);
    }
}
