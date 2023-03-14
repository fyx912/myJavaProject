/*
 Navicat Premium Data Transfer

 Source Server         : ding-master
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : 127.0.0.1:3306
 Source Schema         : boss

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 14/03/2023 01:46:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user`  (
  `uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  `account` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '账号',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '昵称',
  `status` tinyint(1) DEFAULT 1 COMMENT '状态1启用2停止3冻结',
  `total_money` decimal(9, 2) DEFAULT NULL COMMENT '总金额',
  `total_integral` int(0) DEFAULT NULL COMMENT '总积分',
  `passwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `last_time` datetime(0) DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES ('1', '19900000000', 'appuser', 'toney', 1, 0.00, 0, NULL, NULL, '2023-03-12 08:44:51', '2023-03-12 08:45:10');
INSERT INTO `u_user` VALUES ('2', '19900000001', 'appadmin', 'jake', 1, 0.00, 0, NULL, NULL, '2023-03-12 08:44:54', '2023-03-12 08:45:13');
INSERT INTO `u_user` VALUES ('3', '19900000001', 'jackson', 'jackson', 1, 0.00, 0, NULL, NULL, '2023-03-12 08:44:57', '2023-03-12 08:45:16');
INSERT INTO `u_user` VALUES ('4', '1360000005', 'jack', '杰克', 1, 1.00, 0, '1223456', '2023-03-14 01:39:18', '2023-03-14 01:37:55', '2023-03-14 01:39:18');

SET FOREIGN_KEY_CHECKS = 1;
