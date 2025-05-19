package ma.maane.examjee.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Système de Gestion des Crédits Bancaires",
        version = "1.0.0",
        description = "API pour la gestion des crédits bancaires",
        contact = @Contact(
            name = "Support Technique",
            email = "support@banque.ma"
        )
    ),
    servers = {
        @Server(url = "http://localhost:8085", description = "Serveur de développement")
    }
)
public class SwaggerConfig {
    // Configuration de base pour OpenAPI
} 