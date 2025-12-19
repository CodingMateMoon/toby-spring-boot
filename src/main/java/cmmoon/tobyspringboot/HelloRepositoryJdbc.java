package cmmoon.tobyspringboot;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepositoryJdbc implements HelloRepository {
    private final JdbcTemplate jdbcTemplate;

    public HelloRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Hello findHello(String name) {
        return null;
    }

    @Override
    public void increaseCount(String name) {

    }
}
