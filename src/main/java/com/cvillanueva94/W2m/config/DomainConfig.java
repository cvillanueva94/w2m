package com.cvillanueva94.W2m.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.cvillanueva94.W2m.domain")
@EnableJpaRepositories("com.cvillanueva94.W2m.repos")
@EnableTransactionManagement
public class DomainConfig {
}
