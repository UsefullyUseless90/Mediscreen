-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Mar 06, 2023 at 12:08 PM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mediscreen`
--

-- --------------------------------------------------------

--
-- Table structure for table `patients_informations`
--

DROP TABLE IF EXISTS `patients_informations`;
CREATE TABLE IF NOT EXISTS `patients_informations` (
  `id_patient` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `birth_date` varchar(10) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `postal_address` varchar(100) NOT NULL,
  `phone_number` int(50) NOT NULL,
  PRIMARY KEY (`id_patient`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patients_informations`
--

INSERT INTO `patients_informations` (`id_patient`, `first_name`, `name`, `birth_date`, `gender`, `postal_address`, `phone_number`) VALUES
(1, 'Matthew ', 'McWorthIt', '1989-08-18', 'male', '1548 BloodyBurgers street', 555689852),
(3, 'Norman', 'VonDecker', '1975-04-23', 'male', '2365 TooMuch Sugary Road', 555231458),
(4, 'Crystal', 'Lorringham', '1985-02-16', 'female', '961 WaflesWalls Street', 555666323),
(7, 'Rondha', 'Klear', '1964-10-03', 'female', '215 Alcohols Bad kay Avenue', 555894201),
(10, 'Kyle ', 'Browslovsky', '1992-02-13', 'male', '8966 SouthPark Avenue', 123456789),
(14, 'Michael', 'Scott', '1970-05-13', 'male', '2137 St William Avenue', 841874795);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
