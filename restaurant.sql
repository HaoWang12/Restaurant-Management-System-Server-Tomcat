/*
Navicat MySQL Data Transfer

Source Server         : zhh
Source Server Version : 50733
Source Host           : localhost:3306
Source Database       : restaurant

Target Server Type    : MYSQL
Target Server Version : 50733
File Encoding         : 65001

Date: 2022-05-06 02:04:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `did` int(11) DEFAULT NULL,
  `cid` text,
  `name` text,
  `price` text,
  `types` text,
  `note` text,
  `username` varchar(255) DEFAULT NULL,
  `newprice` text,
  PRIMARY KEY (`id`),
  KEY `did` (`did`),
  KEY `username` (`username`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`did`) REFERENCES `desk` (`id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `commentId` int(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `commentId` (`commentId`),
  KEY `username` (`username`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`commentId`) REFERENCES `communication` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('100', '21', 'admin', 'ddddd');
INSERT INTO `comment` VALUES ('101', '20', 'admin', 'ffff');
INSERT INTO `comment` VALUES ('102', '21', '12345678900', 'fff');
INSERT INTO `comment` VALUES ('103', '22', '12345678900', 'eeeee');
INSERT INTO `comment` VALUES ('104', '22', '12345678900', 'wwww');
INSERT INTO `comment` VALUES ('105', '23', '12345678900', 'ggg');

-- ----------------------------
-- Table structure for communication
-- ----------------------------
DROP TABLE IF EXISTS `communication`;
CREATE TABLE `communication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `content` text,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  CONSTRAINT `communication_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of communication
-- ----------------------------
INSERT INTO `communication` VALUES ('20', 'admin', 'fdfsfsdfsf', '2022-05-06 01:39:44');
INSERT INTO `communication` VALUES ('21', 'admin', 'fdfsfsdfsfffff', '2022-05-06 01:39:47');
INSERT INTO `communication` VALUES ('22', 'admin', 'fdfsfsdfsfffffwwwww', '2022-05-06 01:39:49');
INSERT INTO `communication` VALUES ('23', '12345678900', 'fefefefef', '2022-05-06 01:44:25');

-- ----------------------------
-- Table structure for desk
-- ----------------------------
DROP TABLE IF EXISTS `desk`;
CREATE TABLE `desk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` text,
  `total` text,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  CONSTRAINT `desk_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of desk
-- ----------------------------
INSERT INTO `desk` VALUES ('10', '001', '8', '12345678900');

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` text,
  `types` text,
  `note` text,
  `newprice` text,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dish
-- ----------------------------
INSERT INTO `dish` VALUES ('22', 'c1', '50', 'Cold Dish', 'introduction c1', '49');
INSERT INTO `dish` VALUES ('23', 'c2', '52', 'Cold Dish', 'introduction c2', '50');
INSERT INTO `dish` VALUES ('24', 'c3', '53', 'Cold Dish', 'introduction c3', '53');
INSERT INTO `dish` VALUES ('25', 'c4', '54', 'Cold Dish', 'introduction c4', '54');
INSERT INTO `dish` VALUES ('26', 'c5', '55', 'Cold Dish', 'introduction c5', '54');
INSERT INTO `dish` VALUES ('27', 'h1', '31', 'Hot Dish', 'introduction h1', '30');
INSERT INTO `dish` VALUES ('28', 'h2', '32', 'Hot Dish', 'introduction h2', '30');
INSERT INTO `dish` VALUES ('29', 'h3', '33', 'Hot Dish', 'introduction h3', '31');
INSERT INTO `dish` VALUES ('30', 'h4', '34', 'Hot Dish', 'introduction h4', '34');
INSERT INTO `dish` VALUES ('31', 'h5', '35', 'Hot Dish', 'introduction h5', '35');
INSERT INTO `dish` VALUES ('32', 's1', '11', 'Soup', 'introduction s1', '10');
INSERT INTO `dish` VALUES ('33', 's2', '12', 'Soup', 'introduction s2', '10');
INSERT INTO `dish` VALUES ('34', 's3', '13', 'Soup', 'introduction s3', '13');
INSERT INTO `dish` VALUES ('35', 's4', '14', 'Soup', 'introduction s4', '14');
INSERT INTO `dish` VALUES ('36', 's5', '15', 'Soup', 'introduction s5', '15');
INSERT INTO `dish` VALUES ('37', 'staple1', '41', 'Staple', 'introduction staple1', '32');
INSERT INTO `dish` VALUES ('38', 'staple2', '42', 'Staple', 'introduction staple2', '35');
INSERT INTO `dish` VALUES ('39', 'staple3', '43', 'Staple', 'introduction staple3', '33');
INSERT INTO `dish` VALUES ('40', 'staple4', '44', 'Staple', 'introduction staple4', '34');
INSERT INTO `dish` VALUES ('41', 'staple5', '45', 'Staple', 'introduction staple5', '35');
INSERT INTO `dish` VALUES ('42', 'd1', '21', 'Drinks', 'introduction d1', '20');
INSERT INTO `dish` VALUES ('43', 'd2', '22', 'Drinks', 'introduction d2', '22');
INSERT INTO `dish` VALUES ('44', 'd3', '23', 'Drinks', 'introduction d3', '22');
INSERT INTO `dish` VALUES ('46', 'd5', '25', 'Drinks', 'introduction d5', '25');

-- ----------------------------
-- Table structure for d_admin
-- ----------------------------
DROP TABLE IF EXISTS `d_admin`;
CREATE TABLE `d_admin` (
  `a_username` varchar(255) NOT NULL,
  `a_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`a_username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of d_admin
-- ----------------------------
INSERT INTO `d_admin` VALUES ('admin', 'admin');

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text,
  `money` varchar(255) DEFAULT NULL,
  `time` text,
  `note` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES ('8', 'purchase1', '101', '2022-05-05', 'ddddd1');
INSERT INTO `purchase` VALUES ('9', 'purchase2', '102', '2022-05-05', 'ddddd2');
INSERT INTO `purchase` VALUES ('11', 'purchase4', '104', '2022-05-05', 'ddddd4');
INSERT INTO `purchase` VALUES ('12', 'ppp1', '500', '2022-05-03', 'fwfawfafwaf');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text,
  `price` text,
  `number` text,
  `time` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('39', 'd3', '23', '1', '2022-05-06 02:02:38');
INSERT INTO `record` VALUES ('40', 'd2', '22', '2', '2022-05-06 02:02:38');
INSERT INTO `record` VALUES ('41', 's3', '13', '1', '2022-05-06 02:02:38');
INSERT INTO `record` VALUES ('42', 'h3', '33', '1', '2022-05-06 02:02:38');
INSERT INTO `record` VALUES ('43', 'h4', '34', '2', '2022-05-06 02:02:38');
INSERT INTO `record` VALUES ('44', 'staple2', '42', '1', '2022-05-06 02:02:38');
INSERT INTO `record` VALUES ('45', 'c5', '55', '1', '2022-05-06 02:02:38');
INSERT INTO `record` VALUES ('46', 'c2', '52', '1', '2022-05-06 02:02:38');
INSERT INTO `record` VALUES ('47', 'c3', '53', '4', '2022-05-06 02:02:38');
INSERT INTO `record` VALUES ('48', 'c4', '54', '4', '2022-05-06 02:02:38');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `time` text,
  `note` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('7', '18652322523', '8', '2022-05-06 10:00:00', 'dasdasdasd');
INSERT INTO `room` VALUES ('8', '18652322524', '4', '2022-05-06 10:04:00', 'dasdasdasd4');
INSERT INTO `room` VALUES ('10', '18652322526', '6', '2022-05-06 10:06:00', 'dasdasdasd6');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `statue` varchar(255) DEFAULT NULL,
  `money` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('12345678900', '123456', 'Tom', 'customer', '242');
INSERT INTO `user` VALUES ('12345678901', '123456', 'Alice', 'customer', '0');
INSERT INTO `user` VALUES ('12345678903', '123456', 'bob', 'customer', '0');
INSERT INTO `user` VALUES ('21345678900', '123456', 'IBob', 'staff', null);
INSERT INTO `user` VALUES ('21345678902', '123456', 'IAlice', 'staff', null);
INSERT INTO `user` VALUES ('admin', '123456', 'bob', 'admin', null);
