/*
SQLyog Community v12.09 (64 bit)
MySQL - 10.4.14-MariaDB : Database - e_shop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`e_shop` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `e_shop`;

/*Table structure for table `acquisto` */

DROP TABLE IF EXISTS `acquisto`;

CREATE TABLE `acquisto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_utente` varchar(30) NOT NULL,
  `id_prodotto` varchar(30) NOT NULL,
  `id_carrello` int(11) NOT NULL,
  `data_acquisto` date NOT NULL DEFAULT '2000-01-01',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=288 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `categoria` */

DROP TABLE IF EXISTS `categoria`;

CREATE TABLE `categoria` (
  `id` char(2) NOT NULL,
  `categoria` varchar(20) NOT NULL,
  `n_prodotti` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `prodotto` */

DROP TABLE IF EXISTS `prodotto`;

CREATE TABLE `prodotto` (
  `id` char(5) NOT NULL,
  `id_categoria` char(2) NOT NULL,
  `descrizione` varchar(100) DEFAULT NULL,
  `prezzo` int(11) NOT NULL CHECK (`prezzo` > 0),
  `nome` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `utente` */

DROP TABLE IF EXISTS `utente`;

CREATE TABLE `utente` (
  `username` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
