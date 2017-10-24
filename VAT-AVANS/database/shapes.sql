-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Gegenereerd op: 24 okt 2017 om 22:06
-- Serverversie: 10.1.19-MariaDB
-- PHP-versie: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shapes`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `shapes`
--

CREATE TABLE `shapes` (
  `Shapes` enum('Rhombus','Cube','Cylinder','Rectangle','Sphere') NOT NULL,
  `Volume` double DEFAULT NULL,
  `Radius - Base - width` double DEFAULT NULL,
  `Heigth` double DEFAULT NULL,
  `Length` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `shapes`
--

INSERT INTO `shapes` (`Shapes`, `Volume`, `Radius - Base - width`, `Heigth`, `Length`) VALUES
('Rectangle', 1, 1, 1, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
