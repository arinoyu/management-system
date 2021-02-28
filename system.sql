-- MySQL dump 10.13  Distrib 8.0.22, for osx10.16 (x86_64)
--
-- Host: 127.0.0.1    Database: shop
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` int NOT NULL,
  `pid` int DEFAULT '0',
  `authName` varchar(50) NOT NULL,
  `path` varchar(50) NOT NULL,
  `order` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (101,0,'商品管理','goods',3),(102,0,'订单管理','orders',4),(103,0,'权限管理','rights',2),(104,101,'商品列表','goods',1),(107,102,'订单列表','orders',1),(110,125,'用户列表','users',1),(111,103,'角色列表','roles',1),(112,103,'权限列表','rights',2),(115,101,'分类参数','params',2),(121,101,'商品分类','categories',3),(125,0,'用户管理','users',1),(145,0,'数据统计','reports',5),(146,145,'数据报表','reports',1);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `ps_id` smallint unsigned NOT NULL AUTO_INCREMENT,
  `ps_name` varchar(20) NOT NULL COMMENT '权限名称',
  `ps_pid` smallint unsigned NOT NULL COMMENT '父id',
  `ps_c` varchar(32) NOT NULL DEFAULT '' COMMENT '控制器',
  `ps_a` varchar(32) NOT NULL DEFAULT '' COMMENT '操作方法',
  `ps_level` enum('0','2','1') NOT NULL DEFAULT '0' COMMENT '权限等级',
  PRIMARY KEY (`ps_id`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8 COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (101,'商品管理',0,'','','0'),(102,'订单管理',0,'','order','0'),(103,'权限管理',0,'','','0'),(104,'商品列表',101,'Goods','index','1'),(105,'添加商品',104,'Goods','tianjia','2'),(107,'订单列表',102,'Order','index','1'),(109,'添加订单',107,'Order','tianjia','2'),(110,'用户列表',125,'Manager','index','1'),(111,'角色列表',103,'Role','index','1'),(112,'权限列表',103,'Permission','index','1'),(115,'分类参数',101,'Type','index','1'),(116,'商品修改',104,'Goods','upd','2'),(117,'商品删除',104,'Goods','del','2'),(121,'商品分类',101,'','','1'),(122,'添加分类',121,'','','2'),(123,'删除分类',121,'','','2'),(125,'用户管理',0,'','','0'),(129,'添加角色',111,'','','2'),(130,'删除角色',111,'','','2'),(131,'添加用户',110,'','','2'),(132,'删除用户',110,'','','2'),(133,'更新用户',110,'','','2'),(134,'角色授权',111,'','','2'),(135,'取消角色授权',111,'','','2'),(136,'获取用户详情',110,'','','2'),(137,'分配用户角色',110,'','','2'),(138,'获取角色列表',111,'','','2'),(139,'获取角色详情',111,'','','2'),(140,'更新角色信息',111,'','','2'),(141,'更新角色权限',111,'','','2'),(142,'获取参数列表',115,'','','2'),(143,'创建商品参数',115,'','','2'),(144,'删除商品参数',115,'','','2'),(145,'数据统计',0,'','','0'),(146,'数据报表',145,'','','1'),(147,'查看权限',112,'','','2'),(148,'查看数据',146,'','','2'),(149,'获取分类详情',121,'','','2'),(150,'更新商品图片',104,'','','2'),(151,'更新商品属性',104,'','','2'),(152,'更新商品状态',104,'','','2'),(153,'获取商品详情',104,'','','2'),(154,'订单更新',107,'','','2'),(155,'获取订单详情',107,'','','2'),(156,'分类参数添加',101,'','','2'),(157,'分类参数删除',101,'','','2'),(158,'分类参数详情',101,'','','2'),(159,'设置管理状态',110,'','','2');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission_api`
--

DROP TABLE IF EXISTS `permission_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission_api` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ps_id` int NOT NULL,
  `ps_api_service` varchar(255) DEFAULT NULL,
  `ps_api_action` varchar(255) DEFAULT NULL,
  `ps_api_path` varchar(255) DEFAULT NULL,
  `ps_api_order` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ps_id` (`ps_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission_api`
--

LOCK TABLES `permission_api` WRITE;
/*!40000 ALTER TABLE `permission_api` DISABLE KEYS */;
INSERT INTO `permission_api` VALUES (1,101,NULL,NULL,'goods',3),(2,102,NULL,NULL,'orders',4),(3,103,NULL,NULL,'rights',2),(4,104,'GoodService','getAllGoods','goods',1),(5,105,'GoodService','createGood','goods',NULL),(6,107,'OrderService','getAllOrders','orders',NULL),(9,109,'OrderService','createOrder','orders',NULL),(10,110,'ManagerService','getAllManagers','users',NULL),(11,111,'RoleService','getAllRoles','roles',NULL),(12,112,'RightService','getAllRights','rights',NULL),(15,115,'CategoryService','getAttributes','params',2),(16,116,'GoodService','updateGood','goods',NULL),(17,117,'GoodService','deleteGood','goods',NULL),(21,121,'CategoryService','getAllCategories','categories',3),(22,122,'CategoryService','addCategory','categories',NULL),(23,123,'CategoryService','deleteCategory','categories',NULL),(25,125,NULL,NULL,'users',1),(29,129,'RoleService','createRole','roles',NULL),(30,130,'RoleService','deleteRole','roles',NULL),(31,131,'ManagerService','createManager','users',NULL),(32,132,'ManagerService','deleteManager','users',NULL),(33,133,'ManagerService','updateManager','users',NULL),(34,134,'RoleService','updateRoleRight','roles',NULL),(35,135,'RoleService','deleteRoleRight','roles',NULL),(36,136,'ManagerService','getManager','users',NULL),(37,137,'ManagerService','setRole','users',NULL),(38,138,'RoleService','getAllRoles','roles',NULL),(39,139,'RoleService','getRoleById','roles',NULL),(40,140,'RoleService','updateRole','roles',NULL),(41,141,'RoleService','updateRoleRight','roles',NULL),(42,142,'AttributeService','getAttributes','categories',NULL),(43,143,'AttributeService','createAttribute','categories',NULL),(44,144,'AttributeService','deleteAttribute','categories',NULL),(45,145,NULL,NULL,'reports',5),(46,146,NULL,NULL,'reports',NULL),(47,147,'RightService','getAllRights','rights',NULL),(48,148,NULL,NULL,'reports',NULL),(49,149,'CategoryService','getCategoryById','categories',NULL),(50,150,'GoodService','updateGoodPics','goods',NULL),(51,151,'GoodService','updateGoodAttributes','goods',NULL),(52,152,'GoodService','updateGoodsState','goods',NULL),(53,153,'GoodService','getGoodById','goods',NULL),(54,154,'OrderService','updateOrder','orders',NULL),(55,155,'OrderService','getOrder','orders',NULL),(56,156,'CategoryService','createAttribute','categories',NULL),(57,157,'CategoryService','deleteAttribute','categories',NULL),(58,158,'CategoryService','attributeById','categories',NULL),(59,159,'ManagerService','updateMgrState','users',NULL);
/*!40000 ALTER TABLE `permission_api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `role_desc` text,
  `ps_ids` varchar(512) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'超级管理员','创建新用户时的默认角色，不能被删除','145,146,148,101,156,157,158,104,150,151,152,153,105,116,117,115,142,143,144,121,149,122,123,102,107,154,155,109,103,111,129,130,134,135,138,139,140,141,112,147,125,110,131,132,133,136,137,159'),(30,'主管','技术负责人','145,146,148,156,157,158,116,142,143,121,149,122,123,109,129,130,134,135,139,140,141,112,147,131,132,133,136,137,101,104,115,102,107,103,111,125,110'),(31,'测试角色','用于测试功能','115,142,143,144,102,107,154,155,109,103,111,129,130,134,135,138,139,140,141,112,147,101'),(49,'普通管理者','拥有少数权限','101,156,157,158,104,150,151,152,153,105,116,117,115,142,143,144,121,149,122,123,125,110,131,132,133,136,137,159');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rid` int DEFAULT '1',
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `mobile` varchar(60) DEFAULT '',
  `email` varchar(50) DEFAULT '',
  `mg_state` tinyint(1) DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_username_uindex` (`username`),
  KEY `rid` (`rid`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'admin','$2a$10$GCfME8r.9s7jiq007/rAgO1fdOzAF8ehtqPDSgt9AryaaZa1llG6S','17712341234','23131221@qq.com',1,'2021-02-24 11:23:26'),(2,31,'linken','$2a$10$GCfME8r.9s7jiq007/rAgO1fdOzAF8ehtqPDSgt9AryaaZa1llG6S','16618331833','23dewd32@qq.com',1,'2021-02-24 12:53:31'),(25,30,'tom','$2a$10$ylyhpQjkaoJu6RE1whv/NeFb84WYNhjjxa854Vu2pIf6D/E5fYWAq','16411115678','12354353@qq.com',0,'2021-02-28 04:55:01'),(26,49,'Bob','$2a$10$7OeJd/pFZYcBUW3uaDQ8S.z6bs57GXI38rOsIGvU57Ut37EvjjhWa','17731233253','12321321@qq.com',0,'2021-02-28 04:55:45');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-28 20:52:10
