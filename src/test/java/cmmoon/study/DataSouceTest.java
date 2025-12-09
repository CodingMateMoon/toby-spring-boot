package cmmoon.study;

import cmmoon.tobyspringboot.TobySpringBootApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TobySpringBootApplication.class)
// application.properties 등록하는 건 스프링 프레임워크에서 기본으로 해주는게 아니라
// 스프링 부트의 초기화 과정에서 추가되기 때문에 별도로 읽어오는 작업을 정의해야함
@TestPropertySource("classpath:/application.properties")
public class DataSouceTest {
    @Autowired
    DataSource dataSource;

    @Test
    void connect() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
