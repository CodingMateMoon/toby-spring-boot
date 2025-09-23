package cmmoon.tobyspringboot;

import org.springframework.stereotype.Service;

// Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'cmmoon.tobyspringboot.HelloService' available: expected single matching bean but found 2: helloDecorator,simpleHelloService
@Service
public class HelloDecorator implements HelloService{
    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return "*" + helloService.sayHello(name) + "*";
    }
}
