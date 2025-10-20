package cmmoon.study;


import org.junit.jupiter.api.Test;

public class ConfigurationTest {
    @Test
    void configuration() {

    }

    static class Bean1 {
        private final Common common;

        Bean1(Common common){
            this.common = common
        }
    }

    static class Bean2 {
        private final Common common;

        Bean2(Common common){
            this.common = common
        }
    }


    private static class Common {
    }
}
