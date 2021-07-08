package com.example.arch.infrastructure.query;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserQueryRepo {
    private final JdbcTemplate jdbcTemplate;
    private final OrganUnitQueryRepo queryRepo;
}
