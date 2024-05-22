-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         11.3.2-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para dentalclinc
CREATE DATABASE IF NOT EXISTS `dentalclinc` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `dentalclinc`;

-- Volcando estructura para tabla dentalclinc.citas
CREATE TABLE IF NOT EXISTS `citas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nif_paciente` varchar(20) NOT NULL,
  `medico` varchar(100) NOT NULL,
  `dientes` varchar(50) NOT NULL,
  `hora` varchar(10) NOT NULL,
  `fecha` date NOT NULL,
  `procedimiento` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla dentalclinc.citas: ~2 rows (aproximadamente)
INSERT INTO `citas` (`id`, `nif_paciente`, `medico`, `dientes`, `hora`, `fecha`, `procedimiento`) VALUES
	(3, '12345678E', 'Juan Pérez Martínez', '47,48', '08:00', '2024-05-29', 'Extraccion'),
	(4, '555555555F', 'Juan Pérez Martínez', '48,27', '09:00', '2024-05-30', 'Extraccion');

-- Volcando estructura para tabla dentalclinc.medicos
CREATE TABLE IF NOT EXISTS `medicos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `nif` varchar(20) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `genero` varchar(10) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla dentalclinc.medicos: ~3 rows (aproximadamente)
INSERT INTO `medicos` (`id`, `nombre`, `apellidos`, `nif`, `direccion`, `telefono`, `genero`, `fecha_nacimiento`) VALUES
	(1, 'Juan', 'Pérez Martínez', '12345678A', 'Calle Principal 123', '123456789', 'Masculino', '1985-03-15'),
	(2, 'Alvarez', 'Ferrari', '11111111A', 'Calle no lo se 2', '6215523368', 'Hombre', '2018-05-18'),
	(3, 'Muñoz', 'Andrade', '11111112E', 'Calle no lo se 2', '6215523368', 'Hombre', '2018-05-18');

-- Volcando estructura para tabla dentalclinc.pacientes
CREATE TABLE IF NOT EXISTS `pacientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `nif` varchar(20) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `genero` varchar(10) DEFAULT NULL,
  `alergia` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Volcando datos para la tabla dentalclinc.pacientes: ~6 rows (aproximadamente)
INSERT INTO `pacientes` (`id`, `nombre`, `apellidos`, `nif`, `direccion`, `telefono`, `genero`, `alergia`, `fecha_nacimiento`) VALUES
	(2, 'Guilherme', 'Pereira', '12345678E', 'Calle Secundaria 123', '123456710', 'Hombre', 'Ninguna', '1980-01-16'),
	(4, 'Gerson', 'Ferrera', '222222222A', 'Calle la 2 24', '859654715', 'Hombre', 'Ninguna', '2000-05-12'),
	(10, 'Leandro', 'Viver', '555555555F', 'Calle Enfin 2', '621547853', 'Hombre', 'Ninguna', '1979-05-09');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
