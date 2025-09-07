package cmmoon.tobyspringboot;

import org.springframework.stereotype.Controller;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*

 */
@Retention(RetentionPolicy.RUNTIME) // 애노테이션 언제까지 유지할 것인가
@Target(ElementType.TYPE) // 애노테이션 적용 대상 지정
@Controller
public @interface MyComponent {
}
