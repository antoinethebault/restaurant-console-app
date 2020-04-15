package dev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JpaTestConfig.class)
public class RepositoryConfig {

}
