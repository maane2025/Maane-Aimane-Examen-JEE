package ma.maane.examjee.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "ma.maane.examjee.entities")
@EnableJpaRepositories(basePackages = "ma.maane.examjee.repositories")
public class TestConfig {
} 