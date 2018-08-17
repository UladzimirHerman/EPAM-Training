-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: pizza
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS `pizza`;
USE `pizza`;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `rating` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_feedback_user` (`user_id`),
  CONSTRAINT `FK_feedback_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES
  (NULL,5,'Приятно оказаться первым клиентом. Никаких проблем, все четко. Успехов Вашей компании!',5,'2018-06-21 09:58:32'),
  (NULL,4,'Заказывал вчера три пиццы - привезли вовремя без проблем.',5,'2018-06-22 07:05:16'),
  (NULL,12,'Ребята, на дворе 21 век, а Вы предлагаете оплату только наличными... Пришлось спускаться и снимать деньги с карточки. Надеюсь, данное недоразумение будет устранено.',4,'2018-06-23 14:29:42'),
  (NULL,18,'Курьер позвонил и сообщил, что вот-вот подъедет. В итоге он заблудился и пришлось по телефону его направлять. Все остальное - на уровне.',4,'2018-06-25 10:47:03'),
  (NULL,19,'Заказывали на работу в офис - 5 из 5! Спасибо за обед!',5,'2018-06-28 11:42:09'),
  (NULL,12,'Заказывали вчера 5 пицц - привезли все оперативно. Радует, что теперь-то я могу оплатить картой.',5,'2018-06-30 09:34:07'),
  (NULL,11,'Пробовали 4 разные пиццы - вкуснотища! Будем пробовать еще!',5,'2018-07-01 11:09:52'),
  (NULL,9,'Хороший сервис.',5,'2018-07-03 19:24:30'),
  (NULL,8,'Привезли еле теплую пиццу :(',4,'2018-07-04 13:34:17'),
  (NULL,14,'Быстро и вкусно. 5 из 5',5,'2018-07-07 17:32:53'),
  (NULL,10,'Рад, что появился новый достойный сервис. Желаю успехов!',5,'2018-07-08 16:23:50');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `content` varchar(255) NOT NULL,
  `photo` varchar(100) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_news_user` (`user_id`),
  CONSTRAINT `FK_news_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES
  (NULL,1,'Мы открылись!','Друзья, мы рады сообщить вам, что с сегодняшеного дня наш сервис начинает свою работу! Урааа!','d6563414-24b7-4434-bac7-ec54270d0821.jpg','2018-06-21 06:00:00'),
  (NULL,3,'Первый пошел!','Наша команда обслужила первого клиента! Приятного аппетита!','04ee95bc-b09d-47ad-9f83-ea7fdbaba2e2.jpg','2018-06-21 08:42:53'),
  (NULL,1,'Оплата картой','Спешим сообщить, что с сегодняшнего дня оплату заказа можно осуществлять с использованием терминала.','7632d0e7-d1a2-42de-bebc-190be177aa34.jpg','2018-06-25 08:12:04'),
  (NULL,2,'Выходные с пиццей!','Наша пицца любит шумные компании! Проверим?','771815df-9a0b-41af-9a2f-b7d5339763fe.jpg','2018-06-29 10:08:15'),
  (NULL,1,'Доставка по всему городу','С сегодняшнего дня мы доставим Ваш заказ в любую точку Минска!','659ba36f-ec87-40af-8192-687196a14f6d.jpg','2018-07-01 06:02:42'),
  (NULL,1,'Кэшбэк до 7%','Рады сообщить, что теперь держатели карточек НикБанка получат кэшбэк до 7% от каждой суммы заказа. Чем больше сумма заказа, тем больше кэшбэк!','8d91dd75-3d77-49a1-b4c4-7a9dd3fcbe5a.jpg','2018-07-04 11:07:36'),
  (NULL,3,'Оле-оле-оле!','Наслаждайтесь полуфиналами Чемпионата Мира с изумительной пиццей!','48ec421b-8759-4be1-9219-789b374f4787.jpg','2018-07-10 09:51:44');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  `status` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_order_user` (`user_id`),
  CONSTRAINT `FK_order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES
  (NULL, '2018-06-21 10:31:32', 5, 'DELIVERED'),
  (NULL, '2018-06-21 12:04:51', 7, 'DELIVERED'),
  (NULL, '2018-06-21 18:42:16', 4, 'DELIVERED'),
  (NULL, '2018-06-22 11:20:29', 6, 'DELIVERED'),
  (NULL, '2018-06-22 19:20:17', 8, 'DELIVERED'),
  (NULL, '2018-06-22 19:48:50', 9, 'DELIVERED'),
  (NULL, '2018-06-22 20:14:28', 11, 'DELIVERED'),
  (NULL, '2018-06-23 12:33:33', 12, 'DELIVERED'),
  (NULL, '2018-06-23 14:43:15', 5, 'DELIVERED'),
  (NULL, '2018-06-23 16:09:37', 10, 'DELIVERED'),
  (NULL, '2018-06-23 17:52:03', 13, 'DELIVERED'),
  (NULL, '2018-06-23 19:39:22', 14, 'DELIVERED'),
  (NULL, '2018-06-24 15:20:53', 16, 'DELIVERED'),
  (NULL, '2018-06-24 16:44:00', 15, 'DELIVERED'),
  (NULL, '2018-06-24 19:14:35', 19, 'DELIVERED'),
  (NULL, '2018-06-24 20:50:24', 18, 'DELIVERED'),
  (NULL, '2018-06-25 11:22:47', 17, 'DELIVERED'),
  (NULL, '2018-06-25 14:23:10', 20, 'DELIVERED'),
  (NULL, '2018-06-26 12:06:47', 14, 'DELIVERED'),
  (NULL, '2018-06-26 18:20:36', 7, 'DELIVERED'),
  (NULL, '2018-06-26 19:57:41', 15, 'DELIVERED'),
  (NULL, '2018-06-27 15:29:03', 4, 'DELIVERED'),
  (NULL, '2018-06-27 17:21:18', 9, 'DELIVERED'),
  (NULL, '2018-06-28 09:59:51', 19, 'DELIVERED'),
  (NULL, '2018-06-28 15:09:52', 16, 'DELIVERED'),
  (NULL, '2018-06-28 18:43:29', 8, 'DELIVERED'),
  (NULL, '2018-06-29 11:18:47', 20, 'DELIVERED'),
  (NULL, '2018-06-29 16:29:59', 18, 'DELIVERED'),
  (NULL, '2018-06-29 19:40:32', 15, 'DELIVERED'),
  (NULL, '2018-06-29 19:57:19', 12, 'DELIVERED'),
  (NULL, '2018-06-29 20:16:47', 14, 'DELIVERED'),
  (NULL, '2018-06-30 15:59:40', 19, 'DELIVERED'),
  (NULL, '2018-06-30 18:48:13', 20, 'DELIVERED'),
  (NULL, '2018-06-30 19:23:18', 11, 'DELIVERED'),
  (NULL, '2018-06-30 20:34:07', 10, 'DELIVERED'),
  (NULL, '2018-07-01 16:20:14', 5, 'DELIVERED'),
  (NULL, '2018-07-01 18:24:59', 8, 'DELIVERED'),
  (NULL, '2018-07-01 20:48:51', 16, 'DELIVERED'),
  (NULL, '2018-07-02 14:22:56', 6, 'DELIVERED'),
  (NULL, '2018-07-02 16:00:51', 13, 'DELIVERED'),
  (NULL, '2018-07-02 18:23:02', 17, 'DELIVERED'),
  (NULL, '2018-07-02 19:30:50', 14, 'DELIVERED'),
  (NULL, '2018-07-03 15:44:25', 18, 'DELIVERED'),
  (NULL, '2018-07-03 17:50:38', 13, 'DELIVERED'),
  (NULL, '2018-07-03 18:11:13', 19, 'DELIVERED'),
  (NULL, '2018-07-03 18:27:32', 9, 'DELIVERED'),
  (NULL, '2018-07-04 14:52:15', 8, 'DELIVERED'),
  (NULL, '2018-07-05 10:14:22', 11, 'DELIVERED'),
  (NULL, '2018-07-05 12:54:36', 12, 'DELIVERED'),
  (NULL, '2018-07-06 15:25:26', 4, 'DELIVERED'),
  (NULL, '2018-07-06 18:40:51', 18, 'DELIVERED'),
  (NULL, '2018-07-07 11:49:02', 17, 'DELIVERED'),
  (NULL, '2018-07-07 12:59:25', 14, 'DELIVERED'),
  (NULL, '2018-07-07 16:23:10', 5, 'DELIVERED'),
  (NULL, '2018-07-08 16:04:57', 7, 'DELIVERED'),
  (NULL, '2018-07-08 17:42:09', 10, 'DELIVERED'),
  (NULL, '2018-07-08 18:22:41', 19, 'DELIVERED'),
  (NULL, '2018-07-09 14:50:28', 17, 'DELIVERED'),
  (NULL, '2018-07-09 17:13:20', 15, 'DELIVERED'),
  (NULL, '2018-07-10 17:27:36', 18, 'IN_PROGRESS'),
  (NULL, '2018-07-10 17:35:09', 11, 'NEW'),
  (NULL, '2018-07-10 17:42:48', 6, 'NEW'),
  (NULL, '2018-07-10 18:01:47', 9, 'BASKET');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_info`
--

DROP TABLE IF EXISTS `order_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_order_info_order` (`order_id`),
  KEY `FK_order_info_product` (`product_id`),
  CONSTRAINT `FK_order_info_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_order_info_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_info`
--

LOCK TABLES `order_info` WRITE;
/*!40000 ALTER TABLE `order_info` DISABLE KEYS */;
INSERT INTO `order_info` VALUES
  (NULL, 1, 3, 1),
  (NULL, 1, 15, 1),
  (NULL, 2, 12, 1),
  (NULL, 3, 2, 1),
  (NULL, 3, 7, 2),
  (NULL, 4, 6, 1),
  (NULL, 5, 8, 1),
  (NULL, 5, 1, 1),
  (NULL, 5, 13, 1),
  (NULL, 6, 10, 2),
  (NULL, 7, 16, 2),
  (NULL, 7, 14, 2),
  (NULL, 8, 14, 1),
  (NULL, 9, 1, 2),
  (NULL, 10, 3, 2),
  (NULL, 10, 7, 1),
  (NULL, 10, 8, 1),
  (NULL, 11, 13, 1),
  (NULL, 11, 16, 2),
  (NULL, 12, 5, 2),
  (NULL, 12, 11, 3),
  (NULL, 13, 13, 1),
  (NULL, 13, 15, 1),
  (NULL, 14, 6, 1),
  (NULL, 15, 4, 1),
  (NULL, 16, 9, 1),
  (NULL, 16, 11, 1),
  (NULL, 17, 12, 2),
  (NULL, 18, 2, 1),
  (NULL, 18, 8, 1),
  (NULL, 19, 1, 1),
  (NULL, 19, 4, 1),
  (NULL, 19, 15, 1),
  (NULL, 20, 5, 3),
  (NULL, 21, 10, 2),
  (NULL, 21, 8, 1),
  (NULL, 22, 3, 2),
  (NULL, 23, 13, 1),
  (NULL, 23, 11, 1),
  (NULL, 24, 6, 1),
  (NULL, 24, 10, 2),
  (NULL, 25, 9, 1),
  (NULL, 26, 7, 2),
  (NULL, 26, 12, 3),
  (NULL, 27, 16, 1),
  (NULL, 27, 13, 1),
  (NULL, 28, 3, 3),
  (NULL, 29, 2, 1),
  (NULL, 30, 10, 4),
  (NULL, 30, 11, 1),
  (NULL, 31, 1, 2),
  (NULL, 31, 7, 2),
  (NULL, 31, 14, 2),
  (NULL, 32, 6, 2),
  (NULL, 33, 11, 1),
  (NULL, 33, 14, 1),
  (NULL, 34, 16, 1),
  (NULL, 34, 6, 1),
  (NULL, 34, 3, 1),
  (NULL, 34, 7, 1),
  (NULL, 35, 3, 2),
  (NULL, 36, 5, 1),
  (NULL, 37, 3, 1),
  (NULL, 38, 8, 2),
  (NULL, 39, 11, 2),
  (NULL, 40, 16, 1),
  (NULL, 40, 15, 1),
  (NULL, 41, 2, 2),
  (NULL, 42, 6, 3),
  (NULL, 42, 12, 1),
  (NULL, 43, 5, 1),
  (NULL, 43, 7, 1),
  (NULL, 43, 10, 1),
  (NULL, 43, 14, 1),
  (NULL, 44, 6, 1),
  (NULL, 45, 8, 2),
  (NULL, 46, 12, 1),
  (NULL, 46, 16, 1),
  (NULL, 47, 1, 1),
  (NULL, 48, 13, 2),
  (NULL, 49, 2, 1),
  (NULL, 49, 14, 1),
  (NULL, 50, 10, 1),
  (NULL, 50, 13, 1),
  (NULL, 51, 3, 2),
  (NULL, 52, 15, 2),
  (NULL, 53, 9, 2),
  (NULL, 53, 7, 1),
  (NULL, 53, 12, 1),
  (NULL, 53, 4, 1),
  (NULL, 54, 8, 1),
  (NULL, 55, 3, 3),
  (NULL, 56, 7, 2),
  (NULL, 56, 14, 2),
  (NULL, 57, 13, 3),
  (NULL, 58, 9, 1),
  (NULL, 59, 6, 1),
  (NULL, 60, 7, 1),
  (NULL, 60, 1, 1),
  (NULL, 61, 12, 1),
  (NULL, 62, 9, 1),
  (NULL, 62, 2, 1),
  (NULL, 63, 15, 2);
/*!40000 ALTER TABLE `order_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(150) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `photo` varchar(100) NOT NULL,
  `sale` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES
  (NULL, 'Маргарита', 'Сыр «Моцарелла», сыр «Пармезан», орегано, томатный пицца-соус', 12.95, 'd31cfe5d-217f-4895-a944-4397212546bb.jpg', b'1'),
  (NULL, 'Чикен спайси', 'Сыр «Моцарелла», сыр «Пармезан», сладкий перец, пряный халапеньо, кусочки курицы гриль', 13.25, 'b5587140-34ca-4885-9e9a-8f80b37de0f7.jpg', b'1'),
  (NULL, 'Ветчина и грибы', 'Сыр «Моцарелла», ветчина, шампиньоны свежие, томатный пицца-соус', 13.60, '5d2f5760-da72-4390-bf18-0079268b2266.jpg', b'1'),
  (NULL, 'Чикен рэнч', 'Сыр «Моцарелла», кусочки курицы гриль, соус «Рэнч», лук, томаты', 16.75, '9880f1b0-244d-4ea4-9dfd-3d5685c41759.jpg', b'1'),
  (NULL, 'Пепперони', 'Сыр «Моцарелла», сыр «Пармезан», острые колбаски «Пепперони», орегано, томатный пицца-соус', 13.70, '2352af77-611e-4b47-80c1-a9bf96aadb4c.jpg', b'1'),
  (NULL, 'Гавайская', 'Сыр «Моцарелла», сыр «Пармезан», ветчина, ананасы, орегано, томатный пицца-соус', 13.50, '0bab6577-4dfc-457e-a462-f51d615595bb.jpg', b'1'),
  (NULL, '4 сыра', 'Сыр «Моцарелла», сыр «Фету», сыр «Чеддер», сыр «Дор Блю», орегано', 16.50, '5dd7d521-a7b0-483c-8dad-03c5790921f5.jpg', b'1'),
  (NULL, 'Грибная', 'Сыр «Моцарелла», сыр «Пармезан», шампиньоны свежие, чеснок, петрушка, орегано, томатный пицца-соус', 12.80, '1bcabf42-9e0e-4474-8a65-4a7f493293c4.jpg', b'1'),
  (NULL, 'Суприм', 'Сыр «Моцарелла», сыр «Пармезан», итальянские колбаски, перец, шампиньоны свежие, чеснок, перец красный, репчатый лук, томатный пицца-соус', 18.10, '8326b46e-4330-4006-beea-79c0875214c2.jpg', b'1'),
  (NULL, 'Чикен энд веджетебелс', 'Сыр «Моцарелла», кусочки курицы гриль, красный перец, петрушка, чеснок, томаты', 14.25, '03aadd82-8934-4968-bbee-0bd709a4ab3c.jpg', b'1'),
  (NULL, 'Вегетарианская', 'Сыр «Моцарелла», соус «Рэнч», орегано, сыр «Фета», томаты, маслины, микс перцев, микс из шпинатов', 16.90, '69801fcb-0ceb-4308-8c7e-89e78cf399d5.jpg', b'1'),
  (NULL, 'Диабло', 'Сыр «Моцарелла», бекон, говядина, сырный соус, перец чили, перец халапеньо, орегано', 16.25, '3eb04894-d3d2-46ad-a0bf-47cf6acc6be2.jpg', b'1'),
  (NULL, 'Чикен песто', 'Сыр «Моцарелла», куриное филе, соус «Рэнч», соус «Песто», микс из перцев, свежие томаты, орегано', 15.90, 'caf9ae15-3a36-40bf-8c06-a563eaebe948.jpg', b'1'),
  (NULL, 'Кантри', 'Сыр «Моцарелла», сыр «Пармезан», орегано, ветчина, шампиньоны свежие, огурцы маринованные, соус «Рэнч»', 16.25, '28333cd7-7b3c-45cf-9b04-d14e55dbb687.jpg', b'1'),
  (NULL, 'Барбекю', 'Сыр «Моцарелла», кусочки курицы гриль, кукуруза, лук, соус «Барбекю»', 15.75, '6a5483dc-0311-4df6-90e9-72f2d6c9df1e.jpg', b'1'),
  (NULL, 'Гриль', 'Сыр «Моцарелла», буженина, говядина, ветчина, пепперони, лук, огурцы маринованные, томатный микс и соус «Гриль»', 18.10, 'b0e53c42-373d-4fba-8f80-b4fbe1fde263.jpg', b'1');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(15) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES
  (NULL, 'statsura@company.by', '$2a$10$8641gFg.JFRyMgk4j.pAf.WyOXzTtEiD8.xqYXcmDhrNqxyeEF8lG', 'ADMIN'),
  (NULL, 'marazinski@company.by', '$2a$10$6h9e5RQ89fbUcTEUq1T2surOJ8cOLzg8V23.BBgnxeh/t8sLzHkZa', 'ADMIN'),
  (NULL, 'karniushka@company.by', '$2a$10$nOMfeIKhqInCj2WxboWVHO48BLmbmjqkhzBRb8FgL2BtJgCvAYzDO', 'ADMIN'),
  (NULL, 'shapira92@tut.by', '$2a$10$7BAorofN3bNJtfUz.hhzAuFUDNTPXoLVc/xdhMM8mxxwOMMVXkKRG', 'USER'),
  (NULL, 'hanna.zhyta@gmail.com', '$2a$10$fGxxHzwMMQ1gUIEARM9Gm.yHWOBNRXEgC7pWOJkH70YhhhI2.MaTu', 'USER'),
  (NULL, 'markpunk@tut.by', '$2a$10$IULCm843VyrIHgihMRWLWOsyZp4y1Nztohig65795VRWypsdTshqC', 'USER'),
  (NULL, 'mr.rudzinets@gmail.com', '$2a$10$AN16570t/EiM0yvRL66uL.oWw0hGUk1J4QlPtJTD7DonrGyp8i1Na', 'USER'),
  (NULL, 'chamomile@gmail.com', '$2a$10$flPY8/nZsxAfUzejcY7cZ.n1JdpM..ScgBRrbgR1G.m0dKdtvkskm', 'USER'),
  (NULL, 'freedom1918@gmail.com', '$2a$10$Xd1iHbeNpe0QuphPvgPxQeRbqvSn2lxlZTXhbNQnoT/9Z0ow3rTYG', 'USER'),
  (NULL, 'liutyby@tut.by', '$2a$10$kztdUuL2iebKBdgejxCaGu4sjWNjglvLOqs3PiuHAI6wJukk.oi1q', 'USER'),
  (NULL, 'lss-1990@tut.by', '$2a$10$wt5vpKKyr/M8NQSiXr0KxO/HwGHi2xeAxqly6Xhu2zcAI0/dmY8oS', 'USER'),
  (NULL, 'franz.king@gmail.com', '$2a$10$8zAwpWH2xq5.pVzZgtxOxe.Hc8OYjSKIbaBTwPOa4G2oTv3JLHEiq', 'USER'),
  (NULL, 'karas88@mail.ru', '$2a$10$mnlbcZjRal3BMZ8J29Ez2e7VB5GBIYVu57MuTBscqcFL5exsFdrl2', 'USER'),
  (NULL, 'msmartini@yandex.ru', '$2a$10$KXC1yeJ2KFRoL4uAmySFAequZs6ZzCksCRrPx47GHXl87fcxED1Gy', 'USER'),
  (NULL, 'romafoot25@tut.by', '$2a$10$1OwUP097HeQEwdRpJVupIeZicBWz7HuW/BteJWr5.qeJ9m9xEVBpu', 'USER'),
  (NULL, 'semahanz@mail.ru', '$2a$10$LWrIcR1HD2zobqPNadYNT.hPinc9oZCkgxzoBBN/bdruR1iwg24Ee', 'USER'),
  (NULL, 'blond91@yandex.ru', '$2a$10$ec4kMJkBT.aFtwXDWx4xRu0vnIxpKLTjMwd99eqIf4KZkfmE9QXY.', 'USER'),
  (NULL, 'p.madalinski@gmail.com', '$2a$10$LsG8dlc6QVHR7iaL/0v9D.li3SP9cwwvqWIsfvkfabFjmMMB0xHqG', 'USER'),
  (NULL, 'gorynych@tut.by', '$2a$10$0OtcrsQNTfBpibnQ8Rz1H.e07y6hP8SXDwIjE8nptFIo9lr6jRhRC', 'USER'),
  (NULL, 'summerkiss@tut.by', '$2a$10$3ZwT71OJNAgcxSw7Yyoqu.ygRMM4aH0r32u16Pb5ztjJt0O8xl2au', 'USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `phone` varchar(12) NOT NULL,
  `city` varchar(25) NOT NULL,
  `street` varchar(25) NOT NULL,
  `building` varchar(5) NOT NULL,
  `housing` varchar(5) DEFAULT '',
  `apartment` varchar(5) DEFAULT '',
  `note` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES
  (NULL, 'Константин', 'Стацура', '375291652038', 'Минск', 'Гурского', '44', '2', '89', 'Администратор с 21.06.2018'),
  (NULL, 'Кристоф', 'Маразинский', '375296853049', 'Минск', 'Воронянского', '7', '', '62', 'Администратор с 21.06.2018'),
  (NULL, 'Владислав', 'Карнюшка', '375293018952', 'Минск', 'Ванеева', '42', 'а', '5', 'Администратор с 21.06.2018'),
  (NULL, 'Денис', 'Шапира', '375259651017', 'Минск', 'Матусевича', '6', '', '120', ''),
  (NULL, 'Анна', 'Житневская', '375336012791', 'Минск', 'Гамарника', '12', '1', '73', ''),
  (NULL, 'Марк', 'Явинский', '375297102523', 'Минск', 'Кондрата Крапивы', '17', '', '', ''),
  (NULL, 'Матвей', 'Рудинец', '375447520278', 'Минск', 'Карла Либкнехта', '118', '', '47', ''),
  (NULL, 'Юлия', 'Гапанец', '375259662093', 'Минск', 'Казинца', '88', '', '60', ''),
  (NULL, 'Владимир', 'Вечур', '375295124982', 'Минск', 'Игуменский тракт', '14', '', '32', ''),
  (NULL, 'Дмитрий', 'Лютаревич', '375336014855', 'Минск', 'Каховская', '37', '', '30', ''),
  (NULL, 'Алексей', 'Станулевич', '375296104583', 'Минск', 'Лобанка', '81', '', '114', 'Не работает домофон, звоните на мобильный'),
  (NULL, 'Франтишек', 'Атрасевич', '375297665262', 'Минск', 'Шугаева', '21', '1', '186', ''),
  (NULL, 'Андрей', 'Карасюк', '375291450293', 'Минск', 'Малинина', '153', '', '247', ''),
  (NULL, 'Марта', 'Панишевская', '375259523640', 'Минск', 'Лещинского', '39', '', '30', ''),
  (NULL, 'Роман', 'Барута', '375297403634', 'Минск', 'Русиянова', '18', '', '364', ''),
  (NULL, 'Семен', 'Ханцевич', '375336045230', 'Минск', 'Ангарская', '167', '', '', ''),
  (NULL, 'Надежда', 'Черновская', '375293452094', 'Минск', 'Уборевича', '144', '', '8', ''),
  (NULL, 'Павел', 'Мадалинский', '375297403699', 'Минск', 'Нововиленская', '24', 'б', '', ''),
  (NULL, 'Игорь', 'Шишанович', '375447036520', 'Минск', 'Лидская', '16', '', '182', ''),
  (NULL, 'Елена', 'Вишкевич', '375293845471', 'Минск', 'Чечота', '23', '', '63', '');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-20 13:42:17