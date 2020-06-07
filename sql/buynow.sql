/*
 Navicat Premium Data Transfer

 Source Server         : 测
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : buynow

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 07/06/2020 21:28:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `price` double(10, 2) NOT NULL DEFAULT 0.00,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `sales` int(11) NOT NULL DEFAULT 0,
  `imgUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES (25, 'iPhone10', 8000.00, '最好用的手机之一', 6, 'https://dss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/b2de9c82d158ccbf256dd55512d8bc3eb035418f.jpg');
INSERT INTO `item` VALUES (26, '喜剧之王', 10.00, '书籍', 1, 'https://dss1.baidu.com/70cFfyinKgQFm2e88IuM_a/forum/pic/item/b2de9c82d158ccbf256dd55512d8bc3eb035418f.jpg');

-- ----------------------------
-- Table structure for item_stock
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) NOT NULL DEFAULT 0,
  `item_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of item_stock
-- ----------------------------
INSERT INTO `item_stock` VALUES (25, 93, 25);
INSERT INTO `item_stock` VALUES (26, 88, 26);

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT 0,
  `item_id` int(11) NOT NULL DEFAULT 0,
  `item_price` double NOT NULL DEFAULT 0,
  `amount` int(11) NOT NULL DEFAULT 0,
  `order_amount` double NOT NULL DEFAULT 0,
  `promo_id` int(11) NOT NULL DEFAULT 0 COMMENT '非0则表示秒杀价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('2020060700000000', 21, 25, 8000, 1, 8000, 0);
INSERT INTO `order_info` VALUES ('2020060700000100', 21, 25, 8000, 1, 8000, 0);
INSERT INTO `order_info` VALUES ('2020060700000200', 21, 25, 8000, 1, 8000, 0);
INSERT INTO `order_info` VALUES ('2020060700000300', 21, 25, 8000, 1, 8000, 0);
INSERT INTO `order_info` VALUES ('2020060700000400', 21, 25, 400, 1, 400, 1);
INSERT INTO `order_info` VALUES ('2020060700000500', 21, 25, 400, 1, 400, 1);
INSERT INTO `order_info` VALUES ('2020060700000600', 22, 26, 10, 1, 10, 0);
INSERT INTO `order_info` VALUES ('2020060700000700', 22, 25, 400, 1, 400, 1);

-- ----------------------------
-- Table structure for promo
-- ----------------------------
DROP TABLE IF EXISTS `promo`;
CREATE TABLE `promo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `promo_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `start_date` datetime(0) NOT NULL,
  `end_date` datetime(0) NOT NULL,
  `item_id` int(11) NOT NULL DEFAULT 0,
  `promo_item_price` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of promo
-- ----------------------------
INSERT INTO `promo` VALUES (1, 'iPhone10抢购', '2020-06-07 21:21:45', '2020-06-27 13:32:52', 25, 400);

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `current_value` int(11) NOT NULL DEFAULT 0,
  `step` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sequence_info
-- ----------------------------
INSERT INTO `sequence_info` VALUES ('order_info', 8, 1);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `gender` tinyint(4) NOT NULL COMMENT '0女生，1男生',
  `age` int(11) NOT NULL DEFAULT -1,
  `register_mode` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '//byphone, bywechat, byalipay',
  `telphone` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '-1',
  `third_party_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '//第三方系统id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `telphone_unique_index`(`telphone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (16, '中文', 1, 12, 'byphone', '15512345674', '');
INSERT INTO `user_info` VALUES (19, 'admin', 1, 12, 'byphone', '18512345678', '');
INSERT INTO `user_info` VALUES (21, 'tooyi', 1, 12, 'byphone', '15312345678', '');
INSERT INTO `user_info` VALUES (22, '商帆', 1, 14, 'byphone', '14712345678', '');

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encrpt_password` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES (5, 'v6nNRtDW5EPNMrwqJ/07gg==', 16);
INSERT INTO `user_password` VALUES (6, 'gdyb21LQTcIANtvYMT7QVQ==', 19);
INSERT INTO `user_password` VALUES (8, 'ICy5YqxZB1uWSwcVLSNLcA==', 21);
INSERT INTO `user_password` VALUES (9, 'ICy5YqxZB1uWSwcVLSNLcA==', 22);

SET FOREIGN_KEY_CHECKS = 1;
