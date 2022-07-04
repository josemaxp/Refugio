-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 13-12-2021 a las 09:07:20
-- Versión del servidor: 5.7.31
-- Versión de PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `refugio`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `animal`
--

DROP TABLE IF EXISTS `animal`;
CREATE TABLE IF NOT EXISTS `animal` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `sexo` varchar(1) NOT NULL,
  `fecha_nac` date NOT NULL,
  `color_predominante` varchar(30) NOT NULL,
  `id_raza_predominante` int(9) NOT NULL,
  `peso` decimal(3,1) NOT NULL,
  `fecha_arribo` date NOT NULL,
  `fecha_adopcion` date DEFAULT NULL,
  `características` varchar(250) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_raza` (`id_raza_predominante`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `animal`
--

INSERT INTO `animal` (`id`, `nombre`, `sexo`, `fecha_nac`, `color_predominante`, `id_raza_predominante`, `peso`, `fecha_arribo`, `fecha_adopcion`, `características`) VALUES
(1, 'Pluto', 'M', '2015-01-01', 'amarillo', 8, '40.0', '2021-10-01', NULL, 'Perezoso y sociable'),
(2, 'Kitty', 'H', '2020-01-01', 'marrón', 15, '12.0', '2021-10-30', NULL, 'Inteligente'),
(4, 'Kuki', 'H', '2018-11-09', 'blanco', 14, '23.0', '2021-12-04', NULL, 'Tranquila y sosegada'),
(5, 'Estrellita', 'H', '2021-06-15', 'negro', 10, '20.0', '2021-12-04', NULL, 'Juguetona y traviesa'),
(8, 'Pablito', 'H', '2021-12-02', 'naranja', 11, '3.0', '2021-12-06', '2021-12-09', 'Callate, por favor'),
(9, 'Manolito', 'M', '2018-12-16', 'naranja', 15, '10.0', '2021-12-06', '2021-12-09', 'Cariñoso, juguetón y risueño'),
(10, 'Lara', 'H', '2014-12-06', 'marrón', 12, '27.0', '2021-12-06', NULL, 'Vaga'),
(11, 'Lazy', 'H', '2017-12-09', 'negro', 15, '15.0', '2021-12-09', NULL, 'Atrevida');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dosis`
--

DROP TABLE IF EXISTS `dosis`;
CREATE TABLE IF NOT EXISTS `dosis` (
  `id_animal` int(9) NOT NULL,
  `id_vacuna` int(9) NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id_animal`,`id_vacuna`,`fecha`),
  KEY `fk_dvacuna` (`id_vacuna`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `dosis`
--

INSERT INTO `dosis` (`id_animal`, `id_vacuna`, `fecha`) VALUES
(1, 1, '2021-10-01'),
(10, 1, '2021-12-06'),
(1, 2, '2021-10-01'),
(5, 2, '2021-12-06'),
(10, 2, '2021-12-06'),
(1, 3, '2021-10-01'),
(10, 3, '2021-12-06'),
(1, 4, '2021-10-01'),
(5, 4, '2021-12-08'),
(10, 4, '2021-12-06'),
(8, 5, '2021-12-06'),
(4, 6, '2021-12-09'),
(2, 7, '2021-10-30'),
(8, 7, '2021-12-06'),
(9, 7, '2021-12-06'),
(11, 7, '2021-12-09'),
(2, 8, '2021-10-30'),
(8, 8, '2021-12-06'),
(9, 8, '2021-12-06'),
(11, 8, '2021-12-09'),
(2, 9, '2021-10-30'),
(8, 9, '2021-12-06'),
(9, 9, '2021-12-06'),
(11, 9, '2021-12-09'),
(8, 11, '2021-12-01'),
(8, 11, '2021-12-06'),
(9, 11, '2021-12-09');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especie`
--

DROP TABLE IF EXISTS `especie`;
CREATE TABLE IF NOT EXISTS `especie` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `especie`
--

INSERT INTO `especie` (`id`, `tipo`) VALUES
(1, 'Perro'),
(2, 'Gato'),
(5, 'Otra');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `raza`
--

DROP TABLE IF EXISTS `raza`;
CREATE TABLE IF NOT EXISTS `raza` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `idespecie` int(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_razaespecie` (`idespecie`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `raza`
--

INSERT INTO `raza` (`id`, `nombre`, `idespecie`) VALUES
(1, 'Siamés', 2),
(2, 'Persa', 2),
(3, 'Siberiano', 2),
(4, 'Bengalí', 2),
(5, 'Angora turco', 2),
(6, 'Siberiano', 2),
(7, 'Bulldog', 1),
(8, 'Labrador', 1),
(9, 'Caniche', 1),
(10, 'Pastor alemán', 1),
(11, 'Chihuahua', 1),
(12, 'Terrier', 1),
(14, 'Perro genérico', 1),
(15, 'Gato genérico', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_user` varchar(50) NOT NULL,
  `password` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_user`, `password`) VALUES
('prueba', '0439434dae91c10c3bc073af1e76addf8f57a30ce0a7de0438b3aaad34b85200d41d01078f2ee786b3130b4ed4e39e3e26090da5d9f87420454dfdd182761cce'),
('usuario', 'd9e94fd2b4c5522e5bb7996aa4df48a3f6b8f1b0c3e7fadb5fcc724e3ab6d85dc401b0a2789fe56c209b80e86102b218ff74ff8614f315599a180691846138b6');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacuna`
--

DROP TABLE IF EXISTS `vacuna`;
CREATE TABLE IF NOT EXISTS `vacuna` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(15) NOT NULL,
  `descripción` varchar(50) NOT NULL,
  `escencial` tinyint(1) NOT NULL,
  `revacunación` int(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `vacuna`
--

INSERT INTO `vacuna` (`id`, `nombre`, `descripción`, `escencial`, `revacunación`) VALUES
(1, 'Moquillo', 'Moquillo canino', 1, 36),
(2, 'Parvovirus', 'Parvovirus canino', 1, 36),
(3, 'Rabia', 'Rabia', 1, 12),
(4, 'Adenovirus', 'Adenovirus canino tipo I y II', 1, 36),
(5, 'Coronavirus', 'Coronavirus', 0, 0),
(6, 'Leptospira', 'Leptospirosis', 0, 12),
(7, 'Herpes', 'Herpes felino tipo I', 1, 36),
(8, 'Calcivirus', 'Calcivirus felino', 1, 36),
(9, 'Panleucopenia', 'Panleucopenia felina', 1, 36),
(11, 'Peritonitis', 'Peritonitis infecciosa felina', 0, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vacuna_especie`
--

DROP TABLE IF EXISTS `vacuna_especie`;
CREATE TABLE IF NOT EXISTS `vacuna_especie` (
  `id_vacuna` int(9) NOT NULL,
  `id_especie` int(9) NOT NULL,
  PRIMARY KEY (`id_vacuna`,`id_especie`),
  KEY `fk_especie` (`id_especie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `vacuna_especie`
--

INSERT INTO `vacuna_especie` (`id_vacuna`, `id_especie`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(5, 2),
(7, 2),
(8, 2),
(9, 2),
(11, 2);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `animal`
--
ALTER TABLE `animal`
  ADD CONSTRAINT `fk_raza` FOREIGN KEY (`id_raza_predominante`) REFERENCES `raza` (`id`);

--
-- Filtros para la tabla `dosis`
--
ALTER TABLE `dosis`
  ADD CONSTRAINT `fk_danimal` FOREIGN KEY (`id_animal`) REFERENCES `animal` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_dvacuna` FOREIGN KEY (`id_vacuna`) REFERENCES `vacuna` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `raza`
--
ALTER TABLE `raza`
  ADD CONSTRAINT `fk_razaespecie` FOREIGN KEY (`idespecie`) REFERENCES `especie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `vacuna_especie`
--
ALTER TABLE `vacuna_especie`
  ADD CONSTRAINT `fk_especie` FOREIGN KEY (`id_especie`) REFERENCES `especie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_vacuna` FOREIGN KEY (`id_vacuna`) REFERENCES `vacuna` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
