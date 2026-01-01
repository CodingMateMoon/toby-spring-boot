package cmmoon.tobyspringboot;

import cmmoon.config.MySpringBootApplication;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@MySpringBootApplication
/* Bean 오브젝트 팩토리 메서드를 가진 클래스임을 명시. AnnotationConfig를 이용하는 ApplicationContext에 처음으로 등록됨(@Configuration 붙은 클래스)
 */
/*
@Configuration
//해당 클래스가 속한 패키지부터 시작해서 하위 패키지를 전부 탐색해서 컴포넌트가 붙은 클래스들을 전부 빈으로 등록
@ComponentScan
*/
//@SpringBootApplication
public class TobySpringBootApplication {
    private final JdbcTemplate jdbcTemplate;

    public TobySpringBootApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init() {
        this.jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    @Bean
    ApplicationRunner applicationRunner(Environment env){
        return args -> {
            String name = env.getProperty("my.name");
//            System.out.println("my.name: " + name);
        };
    }

    public static void main(String[] args) {
        // Configuration, ComponantScan 팩토리 메서드를 가지고 스프링 컨테이너에게 애플리케이션 구성을 어떻게 할 것인가에 대한 정보를 가진 클래스
        // curl -v "http://localhost:8080/hello?name=test"
//        MySpringApplication.run(TobySpringBootApplication.class, args);
        SpringApplication.run(TobySpringBootApplication.class, args);
    }
}
