package org.example.services.impl;

import org.example.dao.IUtilisateurDao;
import org.example.dao.impl.UtilisateurDaoImpl;
import org.example.model.Utilisateur;
import org.example.services.IUtilisateurService;

import java.sql.SQLException;
import java.util.List;

public class UtilisateurServiceImpl implements IUtilisateurService {
    private IUtilisateurDao utilisateurDao;

    public UtilisateurServiceImpl() {
        this.utilisateurDao = new UtilisateurDaoImpl();

    }

    @Override
    public boolean authentification(String identifiant, String motDePasse) throws SQLException {
        Utilisateur user = utilisateurDao.getUser(identifiant,motDePasse);
        return user!=null;
}


    @Override
    public boolean ajouterCompte(String identifiant, String motDePasse) {
        return false;
    }

    @Override
    public boolean modifierMotDepass(String identifiant, String motDePasse) {
        return false;
    }

    @Override
    public boolean supprimerCompte(String identifiant, String motDePasse) {
        return false;
    }

    @Override
    public List<Utilisateur> listeUtilisateur() {
        return null;
    }
}
