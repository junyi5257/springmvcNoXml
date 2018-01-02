/*
Navicat MySQL Data Transfer

Source Server         : myAliyun
Source Server Version : 50635
Source Host           : 59.110.217.87:3306
Source Database       : springbootdb

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2018-01-02 22:54:11
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id`       INT(10) unsigned NOT NULL AUTO_INCREMENT,
  `rolename` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE =InnoDB AUTO_INCREMENT = 3 DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_USER');
INSERT INTO `role` VALUES ('2', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`       INT(10) unsigned NOT NULL AUTO_INCREMENT,
  `name`     VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE =InnoDB AUTO_INCREMENT = 3 DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'user', 'password');
INSERT INTO `user` VALUES ('2', 'admin', 'admin');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id`      INT(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `user_id`, `role_id`)
) ENGINE =InnoDB AUTO_INCREMENT = 4 DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '1');
INSERT INTO `user_role` VALUES ('3', '2', '2');
