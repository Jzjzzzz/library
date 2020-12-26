/*
 Navicat Premium Data Transfer

 Source Server         : Tset
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 25/12/2020 23:37:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for books
-- ----------------------------


DROP DATABASE IF EXISTS book;


CREATE DATABASE book CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';


use book;


DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` decimal(10, 2) NOT NULL,
  `amount` int(11) NOT NULL,
  `surplusAmount` int(11) NOT NULL,
  `createDate` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES (1, 'Java核心编程', '一本书', '刘德华', 13.00, 3, 3, '2020-12-23 18:16:23');
INSERT INTO `books` VALUES (2, '面向对象设计', '一本书', '周杰伦', 44.00, 2, 2, '2020-12-23 18:16:54');
INSERT INTO `books` VALUES (3, '算法与设计', '一本书', '喜洋洋', 55.00, 5, 5, '2020-12-23 18:17:28');
INSERT INTO `books` VALUES (4, '计算思维', '一本书', '灰太狼', 34.00, 4, 4, '2020-12-23 18:17:48');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bookName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createDate` datetime(0) NOT NULL,
  `isRepay` int(11) NOT NULL,
  `repayDate` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (1, 1, 1, '张三', 'Java核心编程', '2020-12-23 18:21:48', 1, '2020-12-23 21:07:27');
INSERT INTO `borrow` VALUES (2, 1, 1, '张三', 'Java核心编程', '2020-12-23 21:05:09', 1, '2020-12-23 21:22:21');
INSERT INTO `borrow` VALUES (3, 4, 1, '张三', '计算思维', '2020-12-23 21:22:52', 1, '2020-12-23 21:23:39');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bookNumber` int(11) NOT NULL DEFAULT 0,
  `newBorrowBooksCreate` date DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '123', 0, NULL);
INSERT INTO `user` VALUES (2, '李四', '123', 0, NULL);
INSERT INTO `user` VALUES (3, '王五', '123', 0, NULL);
INSERT INTO `user` VALUES (4, '赵六', '123', 0, NULL);
INSERT INTO `user` VALUES (5, '钱七', '123', 0, NULL);
INSERT INTO `user` VALUES (6, 'admin', '123', 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
