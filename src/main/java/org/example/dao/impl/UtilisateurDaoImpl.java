package org.example.dao.impl;

import org.example.MenuPrincipal;
import org.example.dao.IUtilisateurDao;
import org.example.dao.SingletonDataBase;
import org.example.exceptions.MenuNotFoundException;
import org.example.model.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class UtilisateurDaoImpl implements IUtilisateurDao {
    static Scanner scanner = new Scanner(System.in);
    public static void afficherMenu() {
        Instant debutSession = Instant.now();

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



    private static final String QUERY_GET_USER_BY_PSEUDO_AND_PASSWORD = "SELECT * FROM utilisateur WHERE pseudo = ? AND motDePasse = ?";

    public Utilisateur getUser(String identifiant, String motDePasse) throws SQLException {
        Connection connection = SingletonDataBase.getInstance();

        if (connection == null || connection.isClosed()) {
            System.out.println("Tentative de réouverture de la connexion...");
            connection = SingletonDataBase.getInstance(); // Tentative de réouverture
            if (connection == null || connection.isClosed()) {
                throw new SQLException("La connexion à la base de données est fermée ou non disponible.");
            }
        }

        Utilisateur utilisateur = null;

        try (PreparedStatement statement = connection.prepareStatement(QUERY_GET_USER_BY_PSEUDO_AND_PASSWORD)) {
            statement.setString(1, identifiant);
            statement.setString(2, motDePasse);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String pseudo = resultSet.getString("pseudo");
                    String motDePass = resultSet.getString("motDePasse");
                    utilisateur = new Utilisateur(id, pseudo, motDePass);
                }
            }
        }

        return utilisateur;
    }


    @Override
    public Utilisateur updateUser(String identifiant, String motDePasse) {
        return null;
    }

    @Override
    public void deleteUser(String identifiant, String motDePasse) {

    }

    @Override
    public List<Utilisateur> listeUtilisateur() {
        return null;
    }
}
