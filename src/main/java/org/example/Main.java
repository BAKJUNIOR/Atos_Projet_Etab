package org.example;

import org.example.model.Utilisateur;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.initialiser();

        if (utilisateur.Authentification()) {
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            menuPrincipal.afficherMenu();
        }
    }
}
