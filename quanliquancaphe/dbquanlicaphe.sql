-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: testbtlon
-- ------------------------------------------------------
-- Server version	8.0.17

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

--
-- Table structure for table `ban`
--

DROP TABLE IF EXISTS `ban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ban` (
  `maBan` varchar(45) NOT NULL,
  `sucChua` int(11) DEFAULT NULL,
  `tinhTrangTrong` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`maBan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ban`
--

LOCK TABLES `ban` WRITE;
/*!40000 ALTER TABLE `ban` DISABLE KEYS */;
INSERT INTO `ban` VALUES ('a638de62-a5ca-4707-a28b-f56164923e01',4,'Hết chỗ');
/*!40000 ALTER TABLE `ban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietdonhang`
--

DROP TABLE IF EXISTS `chitietdonhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietdonhang` (
  `hoadon_maHoaDon` int(11) NOT NULL,
  `donGia` double DEFAULT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `chitietdonhangcol` varchar(45) DEFAULT NULL,
  `maMon` varchar(45) NOT NULL,
  PRIMARY KEY (`hoadon_maHoaDon`,`maMon`),
  KEY `fk_chitietdonhang_hoadon1_idx` (`hoadon_maHoaDon`),
  KEY `fk_chitietdonhang_maMon_idx` (`maMon`),
  CONSTRAINT `fk_chitietdonhang_hoadon1` FOREIGN KEY (`hoadon_maHoaDon`) REFERENCES `hoadon` (`maHoaDon`),
  CONSTRAINT `fk_chitietdonhang_maMon` FOREIGN KEY (`maMon`) REFERENCES `mon` (`maMon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietdonhang`
--

LOCK TABLES `chitietdonhang` WRITE;
/*!40000 ALTER TABLE `chitietdonhang` DISABLE KEYS */;
/*!40000 ALTER TABLE `chitietdonhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `maHoaDon` int(11) NOT NULL,
  `Ban_maBan` varchar(45) NOT NULL,
  `nhanvien_maNV` varchar(45) NOT NULL,
  `ngayDat` date DEFAULT NULL,
  `tongTien` double DEFAULT NULL,
  PRIMARY KEY (`maHoaDon`),
  KEY `fk_DatBan_Ban1_idx` (`Ban_maBan`),
  KEY `fk_NhanVien_maNV_idx` (`nhanvien_maNV`),
  CONSTRAINT `fk_DatBan_Ban1` FOREIGN KEY (`Ban_maBan`) REFERENCES `ban` (`maBan`),
  CONSTRAINT `fk_NhanVien_maNV` FOREIGN KEY (`nhanvien_maNV`) REFERENCES `nhanvien` (`maNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mon`
--

DROP TABLE IF EXISTS `mon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mon` (
  `maMon` varchar(45) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `giaBan` double DEFAULT NULL,
  `tinhTrangCon` varchar(45) DEFAULT NULL,
  `thoiDiemBan` varchar(45) DEFAULT NULL,
  `loai` varchar(45) NOT NULL,
  PRIMARY KEY (`maMon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mon`
--

LOCK TABLES `mon` WRITE;
/*!40000 ALTER TABLE `mon` DISABLE KEYS */;
INSERT INTO `mon` VALUES ('49d8794d-09c7-4c93-9884-e4fee1738b11','coffee đắng',20000,'Hết','tết','Đồ uống'),('65321d0a-180d-421c-8170-58716da98a80','coffee sữa',25000,'Hết','ngày thường','Đồ uống'),('66f6128c-fefe-4d59-abcb-ba01bf1afb2c','trà đào cam xả',50000,'Còn hàng','tết','Đồ uống'),('adf074e5-fa6e-4335-94ca-d310dd646189','trà chanh',30000,'Còn hàng','noel','Thức ăn'),('e832fcf2-8bba-40ad-8160-3b712a7cb182','matcha machiato',60000,'Còn hàng','bình thường','Đồ uống'),('ee2ba9d2-658f-4520-b2d3-0633d9d7d86b','bánh đậu xanh',25500,'Còn hàng','thường','Thức ăn');
/*!40000 ALTER TABLE `mon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `maNV` varchar(45) NOT NULL,
  `ten` varchar(45) DEFAULT NULL,
  `gioiTinh` varchar(45) DEFAULT NULL,
  `queQuan` varchar(45) DEFAULT NULL,
  `ngaySinh` date DEFAULT NULL,
  `tenTaiKhoan` varchar(45) NOT NULL,
  `matKhau` varchar(45) NOT NULL,
  PRIMARY KEY (`maNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES ('123@','kim thanh','nu','ninh thuan','1999-09-10','kthanh09','123456');
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-15 14:15:48
