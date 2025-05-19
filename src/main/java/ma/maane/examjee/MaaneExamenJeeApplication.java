package ma.maane.examjee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ma.maane.examjee.repositories")
public class MaaneExamenJeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaaneExamenJeeApplication.class, args);
    }
} 