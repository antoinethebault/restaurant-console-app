package dev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcTestConfig.class, JpaConfig.class })
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class JpaTestConfig {

}