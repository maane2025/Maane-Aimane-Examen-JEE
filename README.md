# Système de Gestion des Crédits Bancaires

## Description
Application de gestion des crédits bancaires développée avec Spring Boot et Angular.

## Fonctionnalités
- Gestion des clients
- Création de différents types de crédits
  - Crédit Personnel
  - Crédit Immobilier
  - Crédit Professionnel
- Acceptation/Rejet des crédits
- Documentation API avec Swagger

## Prérequis
- Java 17+
- Maven
- IDE recommandé : IntelliJ IDEA ou Spring Tool Suite

## Configuration
1. Cloner le repository
2. Importer le projet comme un projet Maven
3. Exécuter `mvn clean install`

## Lancement de l'application
```bash
mvn spring-boot:run
```

## Documentation API
Accéder à Swagger UI :
- URL : `http://localhost:8085/swagger-ui.html`
- Documentation OpenAPI : `http://localhost:8085/v3/api-docs`

## Base de Données
- Base de données H2 en mémoire
- Console H2 accessible à : `http://localhost:8085/h2-console`
  - JDBC URL : `jdbc:h2:mem:creditdb`
  - Utilisateur : `sa`
  - Mot de passe : (vide)

## Points d'API Principaux
- `/api/clients` : Gestion des clients
- `/api/credits` : Gestion des crédits
  - `/personnel`
  - `/immobilier`
  - `/professionnel`

## Technologies
- Backend : Spring Boot
- Mapping : MapStruct
- Documentation : Swagger (OpenAPI)
- Base de données : H2
- Validation : Bean Validation
- Logging : SLF4J

## Auteur
Support Technique Banque
support@banque.ma 