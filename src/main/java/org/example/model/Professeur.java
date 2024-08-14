package org.example.model;

import org.example.dao.IProfesseurDAO;
import org.example.IEducation;
import org.example.MenuPrincipal;
import org.example.exceptions.MenuNotFoundException;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * La classe Professeur représente un professeur dans l'application.
 * Elle hérite de la classe Personne et implémente les interfaces IEducation et ICRUDProfesseur.
 */
public class Professeur extends Personne  {
    /**
     * Indique si le professeur est vacant.
     */
    private boolean vacant;
    /**
     * La matière enseignée par le professeur.
     */
    private String matiereEnseigne;
    /**
     * Le prochain cours que le professeur doit préparer.
     */
    private String prochainCours;
    /**
     * Le sujet de la prochaine réunion que le professeur doit assister.
     */
    private String sujetProchaineReunion;


    /**
     * Constructeur de la classe Professeur.
     *
     * @param id            L'ID du professeur.
     * @param dateNaissance La date de naissance du professeur.
     * @param ville         La ville du professeur.
     * @param prenom        Le prénom du professeur.
     * @param nom           Le nom du professeur.
     * @param telephone     Le numéro de téléphone du professeur.
     */

    public Professeur(int id, LocalDate dateNaissance, String ville, String prenom, String nom, String telephone) {
        super(id, dateNaissance, ville, prenom, nom, telephone);
    }


   public static void afficherMenu(){

   }

    public boolean isVacant() {
        return vacant;
    }

    public void setVacant(boolean vacant) {
        this.vacant = vacant;
    }

    public String getMatiereEnseigne() {
        return matiereEnseigne;
    }

    public void setMatiereEnseigne(String matiereEnseigne) {
        this.matiereEnseigne = matiereEnseigne;
    }

    public String getProchainCours() {
        return prochainCours;
    }

    public void setProchainCours(String prochainCours) {
        this.prochainCours = prochainCours;
    }

    public String getSujetProchaineReunion() {
        return sujetProchaineReunion;
    }

    public void setSujetProchaineReunion(String sujetProchaineReunion) {
        this.sujetProchaineReunion = sujetProchaineReunion;
    }
}
