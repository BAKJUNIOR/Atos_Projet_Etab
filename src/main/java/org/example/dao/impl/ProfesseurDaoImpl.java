package org.example.dao.impl;

import org.example.MenuPrincipal;
import org.example.dao.IProfesseurDAO;
import org.example.exceptions.MenuNotFoundException;
import org.example.model.Professeur;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProfesseurDaoImpl implements IProfesseurDAO {

    Instant debutSession = Instant.now();

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private List<Professeur> professeurs = new ArrayList<>();

    @Override
    public Professeur ajouter(Professeur professeur) {
        professeurs.add(professeur);
        System.out.println("Professeur ajouté avec succès !");
        return professeur;
    }

    @Override
    public Professeur modifier(Professeur professeur) {
        for (int i = 0; i < professeurs.size(); i++) {
            if (professeurs.get(i).getId() == professeur.getId()) {
                professeurs.set(i, professeur);
                System.out.println("Professeur modifié avec succès !");
                return professeur;
            }
        }
        System.out.println("Aucun professeur trouvé avec cet ID.");
        return null;
    }

    @Override
    public void supprimer(int id) {
        boolean removed = professeurs.removeIf(professeur -> professeur.getId() == id);
        if (removed) {
            System.out.println("Professeur supprimé avec succès !");
        } else {
            System.out.println("Aucun professeur trouvé avec cet ID.");
        }
    }

    @Override
    public List<Professeur> obtenirProfesseurs() {
        return new ArrayList<>(professeurs); // Retourner une copie pour éviter les modifications externes
    }

    @Override
    public Professeur obtenir(int id) {
        for (Professeur professeur : professeurs) {
            if (professeur.getId() == id) {
                return professeur;
            }
        }
        System.out.println("Aucun professeur trouvé avec cet ID.");
        return null;
    }

    public void afficherMenuGestionProfesseurs() {
        int choix;

        do {
            System.out.println("     * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n" +
                    "     " +
                    "      GESTION DES PROFESSEURS \n" +
                    "      * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n" + "\n\n" +
                    "  MENU: \n\n" +
                    "    " +
                    "1: Ajouter un professeur \n" +
                    "    " +
                    "2: Supprimer un professeur \n" +
                    "    " +
                    "3: Modifier les informations du professeur\n" +
                    "    " +
                    "4: Lister les professeurs \n" +
                    "    " +
                    "5: Obtenir le dernier professeur ajouté \n" +
                    "    " +
                    "6: Retour \n" +
                    "    " +
                    "0: Quitter\n");

            choix = MenuNotFoundException.obtenirChoixUtilisateur(scanner, 6); // Assurez-vous que cette méthode existe

            switch (choix) {
                case 1:
                    ajouterProfesseur();
                    break;
                case 2:
                    supprimerProfesseur();
                    break;
                case 3:
                    modifierProfesseur();
                    break;
                case 4:
                    listerProfesseurs();
                    break;
                case 5:
                    obtenirDernierProfesseurAjoute();
                    break;
                case 6:
                    new MenuPrincipal().afficherMenu();
                    break;
                case 0:
                    System.out.println("Merci d'avoir utilisé l'application ETAB. Au revoir !");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 6);
    }

    private void ajouterProfesseur() {
        System.out.println("Veuillez entrer les informations du professeur");

        System.out.print("ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consomme le retour à la ligne

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        LocalDate dateNaissance = null;
        while (dateNaissance == null) {
            System.out.print("Date de naissance (ex: 23/12/1998) : ");
            String dateString = scanner.nextLine();
            try {
                dateNaissance = LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Format de date invalide. Veuillez entrer la date au format dd/MM/yyyy.");
            }
        }

        System.out.print("Ville : ");
        String ville = scanner.nextLine();

        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine();

        Professeur nouveauProfesseur = new Professeur(id,dateNaissance,ville,prenom,nom,telephone);
        ajouter(nouveauProfesseur);
    }

    private void supprimerProfesseur() {
        System.out.print("Entrez l'ID du professeur à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consomme le retour à la ligne

        supprimer(id);
    }

    private Professeur modifierProfesseur() {
        System.out.print("Entrez l'ID du professeur à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consomme le retour à la ligne

        Professeur professeur = obtenir(id);
        if (professeur != null) {
            System.out.println("Modification des informations du professeur (ID: " + professeur.getId() + ")");

            scanner.nextLine();

            System.out.print("Nom : ");
            professeur.setNom(scanner.nextLine());

            System.out.print("Prénom : ");
            professeur.setPrenom(scanner.nextLine());

            LocalDate dateNaissance = null;
            while (dateNaissance == null) {
                System.out.print("Entrez la date de naissance (ex: 23/12/1998): ");
                String dateStr = scanner.nextLine();
                try {
                    dateNaissance = LocalDate.parse(dateStr, formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Date non conforme. Veuillez entrer une date au format jj/MM/aaaa.");
                }
            }


            scanner.nextLine(); // Consommer la nouvelle ligne restante

            System.out.print("Ville : ");
            professeur.setVille(scanner.nextLine());

            System.out.print("Vacant (oui/non) : ");
            professeur.setVacant(scanner.nextLine().equalsIgnoreCase("oui"));

            System.out.println("Professeur modifié avec succès !");
        } else {
            System.out.println("Le professeur spécifié n'existe pas.");
        }
        return professeur;
    }

    private void listerProfesseurs() {
        List<Professeur> professeurs = obtenirProfesseurs();
        if (professeurs.isEmpty()) {
            System.out.println("Aucun professeur n'est enregistré.");
        } else {
            for (Professeur professeur : professeurs) {
                System.out.println("ID: " + professeur.getId() + "\n" +
                        "Nom: " + professeur.getNom() + "\n" +
                        "Prénom: " + professeur.getPrenom() + "\n" +
                        "Ville: " + professeur.getVille() + "\n" +
                        "Téléphone: " + professeur.getTelephone() + "\n");
            }
        }
    }

    private void obtenirDernierProfesseurAjoute() {
        List<Professeur> professeurs = obtenirProfesseurs();
        if (professeurs.isEmpty()) {
            System.out.println("Aucun professeur n'est enregistré.");
        } else {
            Professeur dernierProfesseur = professeurs.get(professeurs.size() - 1);
            System.out.println("Dernier professeur ajouté : \n" +
                    "        ID: " + dernierProfesseur.getId() + "\n" +
                    "        Nom: " + dernierProfesseur.getNom() + "\n" +
                    "        Prénom: " + dernierProfesseur.getPrenom() + "\n" +
                    "        Ville: " + dernierProfesseur.getVille() + "\n" +
                    "        Téléphone: " + dernierProfesseur.getTelephone() + "\n");
        }
    }

    public void finSession() {
        Instant finSession = Instant.now();
        Duration duree = Duration.between(debutSession, finSession);

        long heures = duree.toHours();
        long minutes = duree.toMinutes() % 60;
        long secondes = duree.getSeconds() % 60;

        System.out.println("Merci d'avoir utilisé l'application ETAB. Au revoir !");
        System.out.println("Durée de la session : " + heures + " heures, " + minutes + " minutes, " + secondes + " secondes.");
    }



}
