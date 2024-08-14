package org.example.services.impl;

import org.example.model.Professeur;
import org.example.services.IProfesseurService;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfesseurServiceImpl implements IProfesseurService {


    /**
     * La liste statique de tous les professeurs.
     */
    private static List<Professeur> professeurs = new ArrayList<>();
    /**
     * L'instant du début de la session.
     */
    private static Instant debutSession = Instant.now();
    /**
     * Scanner utilisé pour lire les entrées de l'utilisateur.
     */
    private static Scanner scanner = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public Professeur save(Professeur professeur) {
        return null;
    }

    @Override
    public Professeur update(Professeur professeur) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Professeur> getAll() {
        return null;
    }

    @Override
    public Professeur getOne(int id) {
        return null;
    }




}
