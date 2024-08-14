package org.example.services.impl;

import org.example.dao.IEleveDAO;
import org.example.model.Eleve;
import org.example.services.IEleveService;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EleveServiceImpl implements IEleveService {

    private final IEleveDAO eleveDAO;

    public EleveServiceImpl(IEleveDAO eleveDAO) {
        this.eleveDAO = eleveDAO;
    }


    @Override
    public Eleve save(Eleve eleve) {
        return null;
    }

    @Override
    public Eleve update(Eleve eleve) {
        return eleveDAO.modifier(eleve);
    }

    @Override
    public void delete(int id) {
        eleveDAO.supprimer(id);
    }

    @Override
    public List<Eleve> getAll() {
        return eleveDAO.obtenirEleves();
    }

    @Override
    public Eleve getOne(int id) {
        return eleveDAO.obtenir(id);
    }



}
