package ma.maane.examjee.config;

import ma.maane.examjee.services.TestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializationConfig {

    @Autowired
    private TestDataService testDataService;

    @Bean
    public CommandLineRunner initializeTestData() {
        return args -> {
            testDataService.genererDonneesTest();
        };
    }
} 