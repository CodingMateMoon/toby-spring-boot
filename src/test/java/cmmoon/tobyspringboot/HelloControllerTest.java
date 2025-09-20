package cmmoon.tobyspringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {
    @Test
    void helloController() {
        HelloController helloController = new HelloController(name -> name);
/*
        HelloController helloController = new HelloController(new HelloService()) {
            @Override
            public String sayHello(String name) {
                return "";
            }
        }
*/
        String ret = helloController.hello("Test");
        Assertions.assertThat(ret).isEqualTo("Test");
    }

    @Test
    void failsHelloController() {
        HelloController helloController = new HelloController(name -> name);

//        String ret = helloController.hello(null);
        Assertions.assertThatThrownBy(()-> {String ret = helloController.hello(null);}).isInstanceOf(NullPointerException.class);
    }
}
