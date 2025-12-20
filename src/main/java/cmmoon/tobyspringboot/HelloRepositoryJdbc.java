package cmmoon.tobyspringboot;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class HelloRepositoryJdbc implements HelloRepository {
    private final JdbcTemplate jdbcTemplate;

    public HelloRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Hello findHello(String name) {
        return jdbcTemplate.queryForObject("select * from hello where name = '" + name + "'",
                new RowMapper<Hello>() {

                    @Override
                    public Hello mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return null;
                    }
                });
    }

    @Override
    public void increaseCount(String name) {

    }
}
