# ETAB v0.1

**ETAB v0.1** est une application Java console qui permet la gestion des élèves, des professeurs, et des utilisateurs au sein d'un établissement. Le projet utilise JDBC pour la connexion à une base de données MySQL.

## Fonctionnalités

### 1. Connexion
- Les utilisateurs peuvent se connecter avec les identifiants par défaut :
  - **Identifiant** : `admin`
  - **Mot de passe** : `admin`

### 2. Gestion des élèves
- Ajouter un élève
- Supprimer un élève
- Modifier les informations d'un élève
- Lister tous les élèves
- Obtenir le dernier élève ajouté

### 3. Gestion des professeurs
- Ajouter un professeur
- Supprimer un professeur
- Modifier les informations d'un professeur
- Lister tous les professeurs
- Obtenir le dernier professeur ajouté

### 4. Gestion des utilisateurs
- Ajouter un utilisateur
- Supprimer un utilisateur
- Modifier les informations d'un utilisateur
- Lister tous les utilisateurs

## Structure du Projet

Le projet est organisé selon les packages suivants :
- **dao** : Gestion des accès aux données avec l'implémentation DAO pour les élèves, les professeurs, et les utilisateurs.
- **model** : Contient les classes de base (`Eleve`, `Professeur`, `Utilisateur`, etc.).
- **services** : Implémentation des services pour gérer les différentes entités (`EleveService`, `ProfesseurService`, etc.).
- **exceptions** : Gère les exceptions spécifiques à l'application.
- **Main** : Point d'entrée principal de l'application avec le menu.

## Base de Données

L'application se connecte à une base de données MySQL nommée `etab_db`. La connexion est gérée via un modèle Singleton, comme illustré dans la classe `SingletonDataBase`.

```java
public class SingletonDataBase {

    private static final String URL = "jdbc:mysql://localhost:3306/etab_db";
    private static final String USER = "bakus";
    private static final String PASSWORD = "Todieviviane58@";
    private static Connection connection = null;

    private SingletonDataBase() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }

    public static Connection getInstance() {
        try {
            if (connection == null || connection.isClosed()) {
                new SingletonDataBase();
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la vérification de l'état de la connexion : " + e.getMessage());
        }
        return connection;
    }
}



## Installation et Exécution

Prérequis
Java 17 ou version supérieure
MySQL
Un IDE de preference IJ


Auteurs
BAKAYOKO BASSINDOU JUNIOR
