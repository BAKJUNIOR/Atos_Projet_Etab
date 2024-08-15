-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 14 août 2024 à 02:22
-- Version du serveur : 8.3.0
-- Version de PHP : 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `etab_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `eleve`
--

DROP TABLE IF EXISTS `eleve`;
CREATE TABLE IF NOT EXISTS `eleve` (
  `id` int NOT NULL,
  `classe` varchar(255) DEFAULT NULL,
  `matricule` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dateNaissance` date DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

DROP TABLE IF EXISTS `professeur`;
CREATE TABLE IF NOT EXISTS `professeur` (
  `id` int NOT NULL,
  `vacant` tinyint(1) DEFAULT NULL,
  `matiereEnseigne` varchar(255) DEFAULT NULL,
  `prochainCours` varchar(255) DEFAULT NULL,
  `sujetProchaineReunion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(255) DEFAULT NULL,
  `motDePasse` varchar(255) DEFAULT NULL,
  `dateCreation` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
