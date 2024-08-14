package org.example.dao.impl;

import org.example.MenuPrincipal;
import org.example.dao.IEleveDAO;
import org.example.exceptions.MenuNotFoundException;
import org.example.model.Eleve;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EleveDaoImpl implements IEleveDAO {

    Instant debutSession = Instant.now();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private List<Eleve> eleves = new ArrayList<>();

    @Override
    public Eleve ajouter(Eleve eleve) {
        eleves.add(eleve);
        System.out.println("Élève ajouté avec succès !");
        return eleve;
    }

    @Override
    public Eleve modifier(Eleve eleve) {
        for (int i = 0; i < eleves.size(); i++) {
            if (eleves.get(i).getId() == eleve.getId()) {
                eleves.set(i, eleve);
                System.out.println("Élève modifié avec succès !");
                return eleve;
            }
        }
        System.out.println("Aucun élève trouvé avec cet ID.");
        return null;
    }

    @Override
    public void supprimer(int id) {
        boolean removed = eleves.removeIf(eleve -> eleve.getId() == id);
        if (removed) {
            System.out.println("Élève supprimé avec succès !");
        } else {
            System.out.println("Aucun élève trouvé avec cet ID.");
        }
    }

    @Override
    public List<Eleve> obtenirEleves() {
        return new ArrayList<>(eleves); // Retourner une copie pour éviter les modifications externes
    }

    @Override
    public Eleve obtenir(int id) {
        for (Eleve eleve : eleves) {
            if (eleve.getId() == id) {
                return eleve;
            }
        }
        System.out.println("Aucun élève trouvé avec cet ID.");
        return null;
    }

    public void afficherMenuGestionEleves() {
        int choix;

        do {
            System.out.println("     * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n" +
                    "     " +
                    "      GESTION DES ELEVES \n" +
                    "      * * * * * * * * * * * * * * * * * * * * * * * * * * * * \n" + "\n\n" +
                    "  MENU: \n\n" +
                    "    " +
                    "1: Ajouter un élève \n" +
                    "    " +
                    "2: Supprimer un élève \n" +
                    "    " +
                    "3: Modifier les informations de l'élève\n" +
                    "    " +
                    "4: Lister les élèves \n" +
                    "    " +
                    "5: Obtenir le dernier élève ajouté \n" +
                    "    " +
                    "6: Retour \n" +
                    "    " +
                    "0: Quitter\n");

            choix = MenuNotFoundException.obtenirChoixUtilisateur(scanner, 6); // Assurez-vous que cette méthode existe

            switch (choix) {
                case 1:
                    ajouterEleve();
                    break;
                case 2:
                    supprimerEleve();
                    break;
                case 3:
                    modifierEleve();
                    break;
                case 4:
                    listerEleves();
                    break;
                case 5:
                    obtenirDernierEleveAjoute();
                    break;
                case 6:
                    new MenuPrincipal().afficherMenu();
                    break;
                case 0:
                    finSession();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 6);
    }

    private void ajouterEleve() {
        System.out.println("Veuillez entrer les informations de l'élève");

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

        System.out.print("Classe : ");
        String classe = scanner.nextLine();

        System.out.print("Téléphone : ");
        String telephone = scanner.nextLine();

        System.out.print("Matricule : ");
        String matricule = scanner.nextLine();

        Eleve nouvelEleve = new Eleve(id, dateNaissance, ville, prenom, nom, telephone, classe, matricule);
        ajouter(nouvelEleve);
    }

    private void supprimerEleve() {
        System.out.print("Entrez l'ID de l'élève à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consomme le retour à la ligne

        supprimer(id);
    }

    private void modifierEleve() {
        System.out.print("Entrez l'ID de l'élève à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consomme le retour à la ligne

        Eleve eleve = obtenir(id);
        if (eleve != null) {
            // Implémentez la logique pour modifier les informations de l'élève
            System.out.println("Modifiez les informations de l'élève (ID: " + id + ")");
            // Exemples d'édition :
            System.out.print("Nouveau nom : ");
            String nom = scanner.nextLine();
            eleve.setNom(nom);
            // Ajoutez ici d'autres champs à modifier...
            modifier(eleve);
        }
    }

    private void listerEleves() {
        List<Eleve> eleves = obtenirEleves();
        if (eleves.isEmpty()) {
            System.out.println("Aucun élève n'est enregistré.");
        } else {
            for (Eleve eleve : eleves) {
                System.out.println("ID: " + eleve.getId() + "\n" +
                        "Nom: " + eleve.getNom() + "\n" +
                        "Prénom: " + eleve.getPrenom() + "\n" +
                        "Classe: " + eleve.getClasse() + "\n" +
                        "Ville: " + eleve.getVille() + "\n" +
                        "Date de Naissance: " + eleve.getDateNaissance() + "\n");
            }
        }
    }

    private void obtenirDernierEleveAjoute() {
        List<Eleve> eleves = obtenirEleves();
        if (eleves.isEmpty()) {
            System.out.println("Aucun élève n'est enregistré.");
        } else {
            Eleve dernierEleve = eleves.get(eleves.size() - 1);
            System.out.println("Dernier élève ajouté : \n" +
                    "        ID: " + dernierEleve.getId() + "\n" +
                    "        Nom: " + dernierEleve.getNom() + "\n" +
                    "        Prénom: " + dernierEleve.getPrenom() + "\n" +
                    "        Classe: " + dernierEleve.getClasse() + "\n" +
                    "        Ville: " + dernierEleve.getVille() + "\n" +
                    "        Date de Naissance: " + dernierEleve.getDateNaissance() + "\n");
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
