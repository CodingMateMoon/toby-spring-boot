package cmmoon.tobyspringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
/*
@Component
메타 애노테이션 : 애노테이션 위에 붙은 애노테이션
 */
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }
    /*
    // 생성자를 통해 인스턴스가 만들어진 이후에 setApplicationContext 호출하기 때문에 final은 불가
    private final ApplicationContext applicationContext;

    public HelloController(HelloService helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;
        this.applicationContext = applicationContext;

        System.out.println(applicationContext);
    }

     */

    @GetMapping("/hello")
//    @ResponseBody
    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }

    /*
    //Root WebApplicationContext, started on Tue Sep 09 08:55:05 KST 2025
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext);
        this.applicationContext = applicationContext;
    }
     */
}
