/*
 Navicat Premium Data Transfer

 Source Server         : 123
 Source Server Type    : MySQL
 Source Server Version : 50018
 Source Host           : localhost:3306
 Source Schema         : bankinfo

 Target Server Type    : MySQL
 Target Server Version : 50018
 File Encoding         : 65001

 Date: 06/08/2020 09:15:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `accountId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `balance` decimal(11, 2) NOT NULL DEFAULT '',
  `idCard` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `telphone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `accountNumber` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT 1,
  PRIMARY KEY USING BTREE (`accountId`),
  UNIQUE INDEX `accountNumber` USING BTREE(`accountNumber`)
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'zx', 30728.43, '123456789012345678', '12345678901', '123456', '6812351898319', 1);
INSERT INTO `account` VALUES (2, 'chb', 43471.61, '123456789012345678', '1234', '123456', '6812350429017', 1);
INSERT INTO `account` VALUES (3, 'admin', 9000.00, '123456789012345678', '17803892165', '4f4979e2b7b3740d87e246c414532c67', '6812334470111', 1);
INSERT INTO `account` VALUES (4, 'swl', 11000.00, '123456789012345678', '12345678901', 'f9133d5ec87677d8d52fee6175ea811b', '6812302672516', 1);
INSERT INTO `account` VALUES (5, 'zsw', 10000.00, '123456432178907654', '12345678900', '3840b7e94860f6ee60dabd0164816011', NULL, 1);
INSERT INTO `account` VALUES (6, 'zsw', 123456.00, '123456789018907654', '12345678909', '3840b7e94860f6ee60dabd0164816011', NULL, 1);
INSERT INTO `account` VALUES (7, 'hsk', 10000.00, '123455321897097654', '17803896543', '8cf63435f939bc5088ed6e0a30727f17', '6812305833610', 1);
INSERT INTO `account` VALUES (8, 'lws', 10000.00, '123456789012343215', '12345678900', 'a3b0d4da28ea7602896e673ff8c6f4ea', '6812379372412', 1);
INSERT INTO `account` VALUES (9, 'zhangsan', 10000.00, '123456789012345678', '12345678900', 'f9133d5ec87677d8d52fee6175ea811b', '6812388456511', 1);
INSERT INTO `account` VALUES (10, 'hsk', 10000.00, '109987465711111111', '12345675432', 'cdb5bf1250e369ecdafdbf0728abedde', '6812397544018', 1);
INSERT INTO `account` VALUES (11, 'fh', 1000.00, '123456789012345678', '12345678901', 'f9133d5ec87677d8d52fee6175ea811b', '6812304030416', 1);

-- ----------------------------
-- Table structure for account_role
-- ----------------------------
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL DEFAULT '',
  `roleId` int(11) NOT NULL DEFAULT '',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of account_role
-- ----------------------------
INSERT INTO `account_role` VALUES (1, 1, 1);
INSERT INTO `account_role` VALUES (2, 2, 2);
INSERT INTO `account_role` VALUES (3, 3, 3);
INSERT INTO `account_role` VALUES (4, 4, 3);
INSERT INTO `account_role` VALUES (5, 6, 2);
INSERT INTO `account_role` VALUES (6, 7, 1);
INSERT INTO `account_role` VALUES (7, 8, 1);
INSERT INTO `account_role` VALUES (8, 9, 1);
INSERT INTO `account_role` VALUES (9, 10, 2);
INSERT INTO `account_role` VALUES (10, 11, 3);
INSERT INTO `account_role` VALUES (11, 12, 1);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '开户');
INSERT INTO `menu` VALUES (2, '转账');
INSERT INTO `menu` VALUES (3, '存款');
INSERT INTO `menu` VALUES (4, '取款');
INSERT INTO `menu` VALUES (5, '修改密码');
INSERT INTO `menu` VALUES (6, '注销');
INSERT INTO `menu` VALUES (7, '查询余额');
INSERT INTO `menu` VALUES (8, '查看流水');
INSERT INTO `menu` VALUES (9, '更换手机号');
INSERT INTO `menu` VALUES (10, '退出');

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL DEFAULT '',
  `menuId` int(11) NOT NULL DEFAULT '',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES (1, 1, 2);
INSERT INTO `menu_role` VALUES (2, 1, 3);
INSERT INTO `menu_role` VALUES (3, 1, 4);
INSERT INTO `menu_role` VALUES (4, 1, 7);
INSERT INTO `menu_role` VALUES (5, 2, 2);
INSERT INTO `menu_role` VALUES (6, 2, 3);
INSERT INTO `menu_role` VALUES (7, 2, 4);
INSERT INTO `menu_role` VALUES (8, 2, 5);
INSERT INTO `menu_role` VALUES (9, 2, 6);
INSERT INTO `menu_role` VALUES (10, 2, 7);
INSERT INTO `menu_role` VALUES (11, 2, 8);
INSERT INTO `menu_role` VALUES (12, 2, 9);
INSERT INTO `menu_role` VALUES (13, 3, 1);
INSERT INTO `menu_role` VALUES (14, 1, 10);
INSERT INTO `menu_role` VALUES (15, 2, 10);
INSERT INTO `menu_role` VALUES (16, 3, 10);

-- ----------------------------
-- Table structure for recode
-- ----------------------------
DROP TABLE IF EXISTS `recode`;
CREATE TABLE `recode`  (
  `recordId` int(11) NOT NULL AUTO_INCREMENT,
  `moduleName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `transferMoney` decimal(11, 2) NULL DEFAULT 0.00,
  `withdrawalMoney` decimal(11, 2) NULL DEFAULT 0.00,
  `depositMoney` decimal(11, 2) NULL DEFAULT 0.00,
  `accountNumber` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `recordTime` date NULL DEFAULT NULL,
  PRIMARY KEY USING BTREE (`recordId`)
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of recode
-- ----------------------------
INSERT INTO `recode` VALUES (1, '存款', NULL, NULL, 2000.00, '6812350429017', '2019-09-02');
INSERT INTO `recode` VALUES (2, '存款', NULL, NULL, 2000.00, '6812350429017', '2019-09-02');
INSERT INTO `recode` VALUES (3, '取款', NULL, NULL, NULL, '6812350429017', '2019-09-02');
INSERT INTO `recode` VALUES (4, '转账', NULL, NULL, NULL, '6812350429017', '2019-09-02');
INSERT INTO `recode` VALUES (5, '存款', NULL, NULL, 20000.00, '6812351898319', '2019-09-02');
INSERT INTO `recode` VALUES (6, '转账', 5000.00, NULL, NULL, '6812350429017', '2019-09-02');
INSERT INTO `recode` VALUES (7, '存款', NULL, NULL, 5000.00, '6812351898319', '2019-09-02');
INSERT INTO `recode` VALUES (8, '存款', NULL, NULL, 10000.00, '6812351898319', '2019-09-02');
INSERT INTO `recode` VALUES (9, '取款', NULL, 20000.00, NULL, '6812351898319', '2019-09-02');
INSERT INTO `recode` VALUES (10, '存款', NULL, NULL, 1000.00, '6812350429017', '2019-09-02');
INSERT INTO `recode` VALUES (11, '转账', 100.65, NULL, NULL, '6812351898319', '2019-09-02');
INSERT INTO `recode` VALUES (12, '存款', NULL, NULL, 100.65, '6812350429017', '2019-09-02');
INSERT INTO `recode` VALUES (13, '取款', NULL, 0.35, NULL, '6812350429017', '2019-09-02');
INSERT INTO `recode` VALUES (14, '存款', NULL, NULL, 100.39, '6812350429017', '2019-09-02');
INSERT INTO `recode` VALUES (15, '转账', 1000.00, 0.00, 0.00, '6812351898319', '2019-09-02');
INSERT INTO `recode` VALUES (16, '存款', 0.00, 0.00, 1000.00, '6812302672516', '2019-09-02');
INSERT INTO `recode` VALUES (17, '转账', 100.00, 0.00, 0.00, '6812350429017', '2019-09-03');
INSERT INTO `recode` VALUES (18, '存款', 0.00, 0.00, 100.00, '6812350429017', '2019-09-03');
INSERT INTO `recode` VALUES (19, '存款', 0.00, 0.00, 1000.00, '6812351898319', '2019-09-03');
INSERT INTO `recode` VALUES (20, '转账', 1314.56, 0.00, 0.00, '6812350429017', '2019-09-03');
INSERT INTO `recode` VALUES (21, '存款', 0.00, 0.00, 1314.56, '6812351898319', '2019-09-03');
INSERT INTO `recode` VALUES (22, '转账', 1000.00, 0.00, 0.00, '6812351898319', '2019-09-03');
INSERT INTO `recode` VALUES (23, '存款', 0.00, 0.00, 1000.00, '6812350429017', '2019-09-03');
INSERT INTO `recode` VALUES (24, '取款', 0.00, 1000.00, 0.00, '6812350429017', '2019-09-03');
INSERT INTO `recode` VALUES (25, '存款', 0.00, 0.00, 1000.00, '6812351898319', '2019-09-03');
INSERT INTO `recode` VALUES (26, '存款', 0.00, 0.00, 1000.00, '6812350429017', '2019-09-03');
INSERT INTO `recode` VALUES (27, '取款', 0.00, 3000.00, 0.00, '6812350429017', '2019-09-03');
INSERT INTO `recode` VALUES (28, '转账', 1413.52, 0.00, 0.00, '6812350429017', '2019-09-03');
INSERT INTO `recode` VALUES (29, '存款', 0.00, 0.00, 1413.52, '6812351898319', '2019-09-03');
INSERT INTO `recode` VALUES (30, '存款', 0.00, 0.00, 100.00, '6812351898319', '2019-09-03');
INSERT INTO `recode` VALUES (31, '转账', 1999.00, 0.00, 0.00, '6812351898319', '2019-09-03');
INSERT INTO `recode` VALUES (32, '存款', 0.00, 0.00, 1999.00, '6812350429017', '2019-09-03');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '储户');
INSERT INTO `role` VALUES (2, '银行职员');
INSERT INTO `role` VALUES (3, '大厅经理');

SET FOREIGN_KEY_CHECKS = 1;
