package org.example.model;

import org.example.MenuPrincipal;
import org.example.dao.SingletonDataBase;
import org.example.exceptions.MenuNotFoundException;
import org.example.services.IUtilisateurService;
import org.example.services.impl.UtilisateurServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

/**
 * La classe {@code Utilisateur} gère l'authentification des utilisateurs et la gestion de leurs comptes.
 * Elle fournit des méthodes pour vérifier les identifiants, ajouter, modifier et supprimer des comptes,
 * ainsi qu'afficher le menu principal pour la gestion des utilisateurs.
 */
public class Utilisateur {

    private int id;
    private String identifiant;
    private String motDePass;

    public Utilisateur() {
    }
    public Utilisateur(int id, String identifiant, String motDePass) {
        this.id = id;
        this.identifiant = identifiant;
        this.motDePass = motDePass;
    }
    static Scanner scanner = new Scanner(System.in);

    /**
     * Permet à un utilisateur de se connecter en fournissant un identifiant et un mot de passe.
     * La méthode vérifie les identifiants et informe l'utilisateur si la connexion est réussie ou non.
     *
     * @return {@code true} si les identifiants sont corrects, {@code false} sinon.
     */
    public boolean Authentification() throws SQLException {
        boolean auth = false;
        System.out.println("     * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n" +
                "     " +
                "      BIENVENU DANS L’APPLICATION ETAB v1.2 \n" +
                "      * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n\n" +
                "CONNEXION\n");

        while (!auth) {  // Boucle jusqu'à ce que l'authentification soit réussie
            System.out.print("Identifiant : ");
            String username = scanner.nextLine();
            System.out.print("Mot de passe : ");
            String password = scanner.nextLine();
            IUtilisateurService utilisateurService = new UtilisateurServiceImpl();
            auth = utilisateurService.authentification(username, password);

            if (auth) {
                System.out.println("Connexion réussie ! \n\n");
            } else {
                System.out.println("Identifiant ou Mot de passe incorrect. Veuillez réessayer.\n\n");
            }
        }
        return auth;

    }


    private static final String QUERY_VERIFIER_UTILISATEUR = "SELECT COUNT(*) FROM Utilisateur WHERE pseudo = ?";
    private static final String QUERY_AJOUTER_UTILISATEUR = "INSERT INTO Utilisateur (pseudo, motDePasse, dateCreation) VALUES (?, ?, NOW())";

    public static void initialiser() {
        try (Connection connexion = SingletonDataBase.getInstance()) {

            // Vérifier si l'utilisateur existe déjà
            try (PreparedStatement instructionVerifier = connexion.prepareStatement(QUERY_VERIFIER_UTILISATEUR)) {
                instructionVerifier.setString(1, "admin");

                try (ResultSet resultat = instructionVerifier.executeQuery()) {
                    if (resultat.next() && resultat.getInt(1) > 0) {
                        System.out.println("Utilisateur existe déjà.");
                        return;
                    }
                }
            }

            // Ajouter l'utilisateur si non existant
            try (PreparedStatement instructionAjouter = connexion.prepareStatement(QUERY_AJOUTER_UTILISATEUR)) {
                instructionAjouter.setString(1, "admin");
                instructionAjouter.setString(2, "admin");
                instructionAjouter.executeUpdate();
                System.out.println("Utilisateur 'admin' ajouté avec succès.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }










    /**
     * Affiche le menu principal pour la gestion des utilisateurs et traite les choix de l'utilisateur.
     * La méthode gère les opérations telles que l'ajout, la suppression, la modification des utilisateurs,
     * et l'affichage de la liste des utilisateurs.
     */
    public static void afficherMenu() {
        Instant debutSession = Instant.now();  // Capturer l'instant du début de la session

        int choix;

        do {
            System.out.println("     * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n" +
                    "     " +
                    "     GESTION DES UTILISATEURS \n" +
                    "      * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n" + "\n\n" +

                    "  MENU: \n\n" + "    " +
                    "1: Ajouter un utilisateur \n" + "    " +
                    "2: Supprimer un utilisateur \n" + "    " +
                    "3: Modifier les informations d'utilisateur\n" + "    " +
                    "4: Lister les utilisateur \n" + "    " +
                    "5: Retour \n" + "    " +
                    "0: Quitter\n");
            System.out.print("Votre choix : ");

            choix = MenuNotFoundException.obtenirChoixUtilisateur(scanner, 6);

            switch (choix) {
                case 1:
                    // Implémentation de l'ajout d'un utilisateur à ajouter ici
                    break;
                case 5:
                    new MenuPrincipal().afficherMenu();
                    break;
                case 0:
                    Instant finSession = Instant.now();  // Capturer l'instant de la fin de la session
                    Duration duree = Duration.between(debutSession, finSession);

                    long heures = duree.toHours();
                    long minutes = duree.toMinutes() % 60;
                    long secondes = duree.getSeconds() % 60;

                    System.out.println("Merci d'avoir utilisé l'application ETAB. Au revoir !");
                    System.out.println("Durée de la session : " + heures + " heures, " + minutes + " minutes, " + secondes + " secondes.");
                    System.exit(0);

                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 6);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotDePass() {
        return motDePass;
    }

    public void setMotDePass(String motDePass) {
        this.motDePass = motDePass;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Utilisateur.scanner = scanner;
    }
}
