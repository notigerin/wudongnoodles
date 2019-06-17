/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : ddc

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 16/06/2019 09:24:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dcc_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `dcc_role_auth`;
CREATE TABLE `dcc_role_auth`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `auth_id` bigint(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1139943693940281346 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dcc_role_auth
-- ----------------------------
INSERT INTO `dcc_role_auth` VALUES (1139943690635169794, 1, 1139936537677512705);
INSERT INTO `dcc_role_auth` VALUES (1139943691188817922, 1, 1139936538944192514);
INSERT INTO `dcc_role_auth` VALUES (1139943691331424257, 1, 1139936539090993153);
INSERT INTO `dcc_role_auth` VALUES (1139943691616636929, 1, 1139936539376205826);
INSERT INTO `dcc_role_auth` VALUES (1139943691897655298, 1, 1139936539653029890);
INSERT INTO `dcc_role_auth` VALUES (1139943692023484418, 1, 1139936539791441922);
INSERT INTO `dcc_role_auth` VALUES (1139943692254171137, 1, 1139941078108061698);
INSERT INTO `dcc_role_auth` VALUES (1139943692405166082, 1, 1139941079345381377);
INSERT INTO `dcc_role_auth` VALUES (1139943692535189506, 1, 1139941079458627585);
INSERT INTO `dcc_role_auth` VALUES (1139943692686184449, 1, 1139941079752228866);
INSERT INTO `dcc_role_auth` VALUES (1139943692812013569, 1, 1139941080033247234);
INSERT INTO `dcc_role_auth` VALUES (1139943692958814210, 1, 1139941080171659266);
INSERT INTO `dcc_role_auth` VALUES (1139943693088837634, 1, 1139941080310071298);
INSERT INTO `dcc_role_auth` VALUES (1139943693239832577, 1, 1139941080435900417);
INSERT INTO `dcc_role_auth` VALUES (1139943693365661698, 1, 1139941080591089666);
INSERT INTO `dcc_role_auth` VALUES (1139943693525045249, 1, 1139941080721113089);
INSERT INTO `dcc_role_auth` VALUES (1139943693650874369, 1, 1139941080872108033);
INSERT INTO `dcc_role_auth` VALUES (1139943693793480706, 1, 1139941080997937154);
INSERT INTO `dcc_role_auth` VALUES (1139943693940281345, 1, 1139941081274761217);

-- ----------------------------
-- Table structure for ddc_admin
-- ----------------------------
DROP TABLE IF EXISTS `ddc_admin`;
CREATE TABLE `ddc_admin`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码 密文',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '加密盐值',
  `sex` tinyint(2) DEFAULT 0 COMMENT '性别 0 男 1 女 默认 0',
  `mobile` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码 ',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(2) DEFAULT NULL COMMENT '启用、禁用  1 启用 2 禁用',
  `del_flag` tinyint(2) NOT NULL DEFAULT 0 COMMENT '删除标志 0 未删除 1 已删除 默认 0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ddc_admin
-- ----------------------------
INSERT INTO `ddc_admin` VALUES (1139946923277545474, 'root', 'f2f337ace5db36a0f83d726f31eb66ff', 'root_0ba80d42-8f92-4374-85be-fef484195bcc', 0, '13812341234', 'hello@qq.com', 1, NULL, 0, 1560619499537, 0, 1560619499537, 0, 0);

-- ----------------------------
-- Table structure for ddc_authority
-- ----------------------------
DROP TABLE IF EXISTS `ddc_authority`;
CREATE TABLE `ddc_authority`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限名称',
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '权限标识',
  `p_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '上级权限 顶级权限的上级权限是 0',
  `level` tinyint(2) NOT NULL DEFAULT 2 COMMENT '权限级别 0 模块权限 1 菜单权限 2 操作权限 默认是2',
  `create_by` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `del_flag` tinyint(2) NOT NULL DEFAULT 0 COMMENT '删除标志 0 未删除 1 已删除 默认 0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1139941081274761218 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ddc_authority
-- ----------------------------
INSERT INTO `ddc_authority` VALUES (1139936537677512705, '资讯管理', 'ZXGL', 0, 1, 0, 1560617023429, 0, 1560617023429, 0);
INSERT INTO `ddc_authority` VALUES (1139936538944192514, '评论管理', 'PLGL', 0, 1, 0, 1560617024539, 0, 1560617024539, 0);
INSERT INTO `ddc_authority` VALUES (1139936539090993153, '会员管理', 'HYGL', 0, 1, 0, 1560617024573, 0, 1560617024573, 0);
INSERT INTO `ddc_authority` VALUES (1139936539376205826, '管理员管理', 'GLYGL', 0, 1, 0, 1560617024640, 0, 1560617024640, 0);
INSERT INTO `ddc_authority` VALUES (1139936539653029890, '数据统计', 'SJTJ', 0, 1, 0, 1560617024707, 0, 1560617024707, 0);
INSERT INTO `ddc_authority` VALUES (1139936539791441922, '系统管理', 'XTGL', 0, 1, 0, 1560617024738, 0, 1560617024738, 0);
INSERT INTO `ddc_authority` VALUES (1139941078108061698, '资讯列表', 'ZXGL-ZXLB', 1139936537677512705, 2, 0, 1560618105944, 0, 1560618105944, 0);
INSERT INTO `ddc_authority` VALUES (1139941079345381377, '图片管理', 'ZXGL-TPGL', 1139936537677512705, 2, 0, 1560618107053, 0, 1560618107053, 0);
INSERT INTO `ddc_authority` VALUES (1139941079458627585, '栏目管理', 'ZXGL-LMGL', 1139936537677512705, 2, 0, 1560618107082, 0, 1560618107082, 0);
INSERT INTO `ddc_authority` VALUES (1139941079752228866, '分类管理', 'ZXGL-FLGL', 1139936537677512705, 2, 0, 1560618107152, 0, 1560618107152, 0);
INSERT INTO `ddc_authority` VALUES (1139941080033247234, '意见反馈', 'PLGL-YJFK', 1139936538944192514, 2, 0, 1560618107219, 0, 1560618107219, 0);
INSERT INTO `ddc_authority` VALUES (1139941080171659266, '会员列表', 'PLGL-HYLB', 1139936539090993153, 2, 0, 1560618107249, 0, 1560618107249, 0);
INSERT INTO `ddc_authority` VALUES (1139941080310071298, '浏览记录', 'PLGL-LLJL', 1139936539090993153, 2, 0, 1560618107285, 0, 1560618107285, 0);
INSERT INTO `ddc_authority` VALUES (1139941080435900417, '管理员列表', 'GLYGL-GLYLB', 1139936539376205826, 2, 0, 1560618107314, 0, 1560618107314, 0);
INSERT INTO `ddc_authority` VALUES (1139941080591089666, '角色列表', 'GLYGL-JSLB', 1139936539376205826, 2, 0, 1560618107350, 0, 1560618107350, 0);
INSERT INTO `ddc_authority` VALUES (1139941080721113089, '权限列表', 'GLYGL-QXLB', 1139936539376205826, 2, 0, 1560618107381, 0, 1560618107381, 0);
INSERT INTO `ddc_authority` VALUES (1139941080872108033, '活跃用户统计', 'SJTJ-HYYH', 1139936539653029890, 2, 0, 1560618107417, 0, 1560618107417, 0);
INSERT INTO `ddc_authority` VALUES (1139941080997937154, '浏览记录统计', 'SJTJ-LJL', 1139936539653029890, 2, 0, 1560618107447, 0, 1560618107447, 0);
INSERT INTO `ddc_authority` VALUES (1139941081274761217, '系统日志', 'XTGL-XTRZ', 1139936539791441922, 2, 0, 1560618107515, 0, 1560618107515, 0);

-- ----------------------------
-- Table structure for ddc_role
-- ----------------------------
DROP TABLE IF EXISTS `ddc_role`;
CREATE TABLE `ddc_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `create_by` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(2) NOT NULL DEFAULT 0 COMMENT '删除标志 0 未删除 1 已删除 默认 0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ddc_role
-- ----------------------------
INSERT INTO `ddc_role` VALUES (1, '超级管理员', '所有权限', 0, 1560617023429, 0, 1560617023429, 0);

SET FOREIGN_KEY_CHECKS = 1;
