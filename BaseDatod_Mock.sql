CREATE DATABASE  IF NOT EXISTS `softfriends_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `softfriends_db`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: softfriends_db
-- ------------------------------------------------------
-- Server version	9.6.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'b90f3af4-2590-11f1-a779-60cf84aa63c0:1-315';

--
-- Table structure for table `carritos`
--

DROP TABLE IF EXISTS `carritos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carritos` (
  `idCarrito` bigint NOT NULL AUTO_INCREMENT,
  `fechaCreacion` date DEFAULT NULL,
  `idUsuario` bigint NOT NULL,
  PRIMARY KEY (`idCarrito`),
  UNIQUE KEY `UKg21u1svwr72rtrusij56ng6li` (`idUsuario`),
  CONSTRAINT `FKscfq7iip3vwnflvoib1a8t3xe` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carritos`
--

LOCK TABLES `carritos` WRITE;
/*!40000 ALTER TABLE `carritos` DISABLE KEYS */;
INSERT INTO `carritos` VALUES (1,NULL,2),(2,NULL,1);
/*!40000 ALTER TABLE `carritos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `idCategoria` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idCategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallespedido`
--

DROP TABLE IF EXISTS `detallespedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallespedido` (
  `idDetallePedido` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `precioVenta` double NOT NULL,
  `idPedido` bigint NOT NULL,
  `idProducto` bigint NOT NULL,
  PRIMARY KEY (`idDetallePedido`),
  KEY `FK5luvvg1jfybbupvpc34o0947i` (`idPedido`),
  KEY `FKsu3nyy6toard4o4exwtt39kn3` (`idProducto`),
  CONSTRAINT `FK5luvvg1jfybbupvpc34o0947i` FOREIGN KEY (`idPedido`) REFERENCES `pedidos` (`idPedido`),
  CONSTRAINT `FKsu3nyy6toard4o4exwtt39kn3` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallespedido`
--

LOCK TABLES `detallespedido` WRITE;
/*!40000 ALTER TABLE `detallespedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallespedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direcciones`
--

DROP TABLE IF EXISTS `direcciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direcciones` (
  `idDireccion` bigint NOT NULL AUTO_INCREMENT,
  `calle` varchar(150) NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `codigoPostal` varchar(10) NOT NULL,
  `numeroExterior` varchar(10) NOT NULL,
  `idUsuario` bigint DEFAULT NULL,
  PRIMARY KEY (`idDireccion`),
  KEY `FKs5vbnlk4v5bp75q2ge2q9f8pw` (`idUsuario`),
  CONSTRAINT `FKs5vbnlk4v5bp75q2ge2q9f8pw` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direcciones`
--

LOCK TABLES `direcciones` WRITE;
/*!40000 ALTER TABLE `direcciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `direcciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemscarrito`
--

DROP TABLE IF EXISTS `itemscarrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itemscarrito` (
  `idItemCarrito` bigint NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `idCarrito` bigint NOT NULL,
  `idProducto` bigint NOT NULL,
  PRIMARY KEY (`idItemCarrito`),
  KEY `FKrm6ct0yrd6ydb8mqw07loj0ul` (`idCarrito`),
  KEY `FKkyug8596as3y43jbp7siagj9u` (`idProducto`),
  CONSTRAINT `FKkyug8596as3y43jbp7siagj9u` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`),
  CONSTRAINT `FKrm6ct0yrd6ydb8mqw07loj0ul` FOREIGN KEY (`idCarrito`) REFERENCES `carritos` (`idCarrito`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemscarrito`
--

LOCK TABLES `itemscarrito` WRITE;
/*!40000 ALTER TABLE `itemscarrito` DISABLE KEYS */;
/*!40000 ALTER TABLE `itemscarrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `idPedido` bigint NOT NULL AUTO_INCREMENT,
  `estado` enum('ENTREGADO','ENVIADO','PENDIENTE') NOT NULL,
  `fechaCompra` datetime(6) NOT NULL,
  `folio` varchar(20) NOT NULL,
  `metodoPago` varchar(255) NOT NULL,
  `total` double NOT NULL,
  `idUsuario` bigint NOT NULL,
  PRIMARY KEY (`idPedido`),
  UNIQUE KEY `UK38126kbvhcrgutddajdbty40x` (`folio`),
  KEY `FKc6khdladvnwt6n78t5dwxmpe6` (`idUsuario`),
  CONSTRAINT `FKc6khdladvnwt6n78t5dwxmpe6` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `idProducto` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(500) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `precio` double NOT NULL,
  `rutaImagen` varchar(260) NOT NULL,
  `stock` int NOT NULL,
  `activo` bit(1) DEFAULT NULL,
  `categoria` varchar(150) NOT NULL,
  `tamano` tinyint NOT NULL,
  PRIMARY KEY (`idProducto`),
  CONSTRAINT `productos_chk_1` CHECK ((`tamano` between 0 and 3))
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (6,'Perro Golden Retriver tamaño Grande','Golden Retriver',499.99,'https://res.cloudinary.com/dslwxrhsi/image/upload/v1779071085/qwoe26e527cwjwo1zviy.png',8,_binary '','Perros',2),(7,'Conejo Rosa de tamaño Pequeño','Conejito Rosa',299.99,'https://res.cloudinary.com/dslwxrhsi/image/upload/v1779071133/erbyyumwdrxqb6alngr7.png',18,_binary '','Bosque',0),(8,'Oso Panda de tamaño Extra Grande','Panda Gigante',799.99,'https://res.cloudinary.com/dslwxrhsi/image/upload/v1779071172/mln15lmqr2dknkry2sxw.png',5,_binary '','Bosque',3);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productoscategorias`
--

DROP TABLE IF EXISTS `productoscategorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productoscategorias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `idCategoria` bigint NOT NULL,
  `idProducto` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9u3djkv6r6mhcisdhadr6b5rx` (`idCategoria`),
  KEY `FKm0nk593mnjurvcr0rbd05svm5` (`idProducto`),
  CONSTRAINT `FK9u3djkv6r6mhcisdhadr6b5rx` FOREIGN KEY (`idCategoria`) REFERENCES `categorias` (`idCategoria`),
  CONSTRAINT `FKm0nk593mnjurvcr0rbd05svm5` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productoscategorias`
--

LOCK TABLES `productoscategorias` WRITE;
/*!40000 ALTER TABLE `productoscategorias` DISABLE KEYS */;
/*!40000 ALTER TABLE `productoscategorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resenias`
--

DROP TABLE IF EXISTS `resenias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resenias` (
  `idResenia` bigint NOT NULL AUTO_INCREMENT,
  `calificacion` int NOT NULL,
  `comentario` varchar(500) NOT NULL,
  `fechaPublicacion` date NOT NULL,
  `idProducto` bigint NOT NULL,
  `idUsuario` bigint NOT NULL,
  PRIMARY KEY (`idResenia`),
  KEY `FKikg8crojfglrj21pn6wo6ofvq` (`idProducto`),
  KEY `FK55mwwx4j6tr7gghcqt8h9sa3h` (`idUsuario`),
  CONSTRAINT `FK55mwwx4j6tr7gghcqt8h9sa3h` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`),
  CONSTRAINT `FKikg8crojfglrj21pn6wo6ofvq` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resenias`
--

LOCK TABLES `resenias` WRITE;
/*!40000 ALTER TABLE `resenias` DISABLE KEYS */;
INSERT INTO `resenias` VALUES (1,4,'Muy buen producto','2026-05-17',6,2);
/*!40000 ALTER TABLE `resenias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuario` bigint NOT NULL AUTO_INCREMENT,
  `contrasenia` varchar(60) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `nombres` varchar(150) NOT NULL,
  `primerApellido` varchar(100) NOT NULL,
  `segundoApellido` varchar(100) DEFAULT NULL,
  `telefono` varchar(10) NOT NULL,
  `tipoUsuario` enum('ADMINISTRADOR','CLIENTE') NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `UKcdmw5hxlfj78uf4997i3qyyw5` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Admin1234','admin@gmail.com','Camila','Zubía','Higuera','1234567890','ADMINISTRADOR'),(2,'Cliente1234','Cliente@gmail.com','María','Lopéz','Gonzalez','1234567890','CLIENTE');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-19  0:18:22
