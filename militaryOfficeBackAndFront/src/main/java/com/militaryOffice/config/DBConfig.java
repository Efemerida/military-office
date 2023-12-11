package com.militaryOffice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.militaryOffice")
@EntityScan("com.militaryOffice")
public class DBConfig {
}
