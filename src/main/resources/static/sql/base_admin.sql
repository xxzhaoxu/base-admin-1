/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : base_admin

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 05/11/2020 16:55:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins`  (
  `series` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登陆账号',
  `token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'cookie令牌',
  `last_used` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后更新时间',
  PRIMARY KEY (`series`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'persistent_logins表，用户实现记住我功能' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
INSERT INTO `persistent_logins` VALUES ('ORvclgmK8WYXYF1pBKZ3fQ==', 'admin', 'c56YHtouP5aoCoMxh0b8Dg==', '2020-11-04 18:08:51');

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority`  (
  `authority_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  `authority_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称，ROLE_开头，全大写',
  `authority_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限描述',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `authority_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限内容，可访问的url，多个时用,隔开',
  `up_file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_find_all` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`authority_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统权限表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES ('3fb1c570496d4c09ab99b8d31b06ccc', 'ROLE_USER', '普通用户', '2019-09-10 10:08:58', '2020-11-04 18:09:02', '/sys/**', 'on', 'on');
INSERT INTO `sys_authority` VALUES ('3fb1c570496d4c09ab99b8d31b06xxx', 'ROLE_SA', '超级管理员', '2019-09-10 10:08:58', '2019-09-10 10:08:58', '/sys/**,/logging', 'on', 'on');
INSERT INTO `sys_authority` VALUES ('3fb1c570496d4c09ab99b8d31b06zzz', 'ROLE_ADMIN', '管理员', '2019-09-10 10:08:58', '2020-11-04 18:19:23', '/sys/**', 'off', 'off');

-- ----------------------------
-- Table structure for sys_consumer
-- ----------------------------
DROP TABLE IF EXISTS `sys_consumer`;
CREATE TABLE `sys_consumer`  (
  `cid` bigint(11) NOT NULL AUTO_INCREMENT,
  `level` enum('A','B','C','D') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT 'A' COMMENT '级别',
  `is_new` enum('新','旧') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '新旧。 旧：0。新：1',
  `c_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '类别',
  `menu` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '目录',
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '公司',
  `c_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机',
  `product` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '产品',
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图片',
  `video_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '视频',
  `salesman` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '业务员',
  `wx_nick` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '微信昵称',
  `wx_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '微信电话',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '省',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '市',
  `area` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '区',
  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '乡',
  `village` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '村',
  `agency` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '社',
  `phone2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '更多电话',
  `sales_list` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '销售记录',
  `promise` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '信用评估',
  `phone_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电话内容',
  `ks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '快手',
  `ks_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '快手内容',
  `dy` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '抖音',
  `dy_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '抖音内容',
  `pdd` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '拼多多',
  `pdd_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '拼多多内容',
  `qq` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'qq',
  `qq_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'QQ内容',
  `tbww` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '淘宝旺旺',
  `tbww_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '淘宝旺旺内容',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `baidu` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '百度',
  `baidu_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '百度内容',
  `index_page` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '主页',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '备注',
  `connect_list` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '沟通记录',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '地址',
  `logistial_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物流地址',
  `c_group` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分组',
  `text_1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '1',
  `text_2` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '2',
  `text_3` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '3',
  `text_4` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '4',
  `text_5` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '5',
  `text_6` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '6',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `stats` enum('0','1') CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 353 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_consumer
-- ----------------------------
INSERT INTO `sys_consumer` VALUES (287, 'A', '新', '销售', '塑料颗粒', '大有塑业', '王林', '13864939852', '编织袋60*110次黄52克覆膜装地瓜', '有', '', '李元', '爱在深秋', '13562396963', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'sa', '凡人歌热热热2', '无钱无权额4', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (288, 'B', '旧', '进货', '面粉', '合成塑胶', '赵三', '13864939853', '编织袋40*60半透', '有', '', '李芳', '趟水过河', '13562396964', '', '', '', NULL, '', NULL, '0566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路34号', '', 'sa', '凡人歌热热热3', '无钱无权额5', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (289, 'C', '旧', '销售', '地瓜', '兰州天利', '李四', '13864939854', '编织袋50*90灰色47克', '有', '', '侯明昊', '执照', '13562396965', '', '', '', NULL, '', NULL, '0543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'sa', '凡人歌热热热4', '无钱无权额6', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (290, 'D', '新', '进货', '米糠', '东城铝业', '王六', '13864939855', '网袋60*95蓝色52克11', '有', '', '李丹1', '日落1', '13562396966', '', '', '', NULL, '', NULL, '0362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'sa', '凡人歌热热热5', '无钱无权额7', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (291, 'A', '新', '销售', '塑料颗粒', '东城铝业1', '李四1', '13864939856', '网袋60*95蓝色52克12', '有', '有', '李丹2', '日落2', '13562396967', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'sa', '凡人歌热热热6', '无钱无权额8', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (292, 'B', '旧', '进货', '面粉1', '东城铝业2', '李四2', '13864939857', '网袋60*95蓝色52克13', '有', '有', '李丹3', '日落3', '13562396968', '', '', '', NULL, '', NULL, '00566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路35号', '', 'sa', '凡人歌热热热7', '无钱无权额9', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (293, 'C', '旧', '销售', '地瓜1', '东城铝业3', '李四3', '13864939858', '网袋60*95蓝色52克14', '有', '有', '李丹4', '日落4', '13562396969', '', '', '', NULL, '', NULL, '00543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'sa', '凡人歌热热热8', '无钱无权额10', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (294, 'D', '新', '进货', '米糠1', '东城铝业4', '李四4', '13864939859', '网袋60*95蓝色52克15', '有', '有', '李丹5', '日落5', '13562396970', '', '', '', NULL, '', NULL, '00362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'sa', '凡人歌热热热9', '无钱无权额11', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (295, 'A', '新', '销售', '面粉2', '东城铝业5', '李四5', '13864939860', '网袋60*95蓝色52克16', '有', '有', '李丹6', '日落6', '13562396971', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'sa', '凡人歌热热热10', '无钱无权额12', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (296, 'B', '旧', '进货', '地瓜2', '东城铝业6', '李四6', '13864939861', '网袋60*95蓝色52克17', '有', '有', '李丹7', '日落7', '13562396972', '', '', '', NULL, '', NULL, '00566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路36号', '', 'sa', '凡人歌热热热11', '无钱无权额13', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (297, 'C', '旧', '销售', '米糠2', '东城铝业7', '李四7', '13864939862', '网袋60*95蓝色52克18', '', '有', '李丹8', '日落8', '13562396973', '', '', '', NULL, '', NULL, '00543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'sa', '凡人歌热热热12', '无钱无权额14', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (298, 'D', '新', '进货', '面粉3', '东城铝业8', '李四8', '13864939863', '网袋60*95蓝色52克19', '', '有', '李丹9', '日落9', '13562396974', '', '', '', NULL, '', NULL, '00362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'sa', '凡人歌热热热13', '无钱无权额15', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (299, 'A', '新', '销售', '地瓜3', '东城铝业9', '李四9', '13864939864', '网袋60*95蓝色52克20', '', '有', '李丹10', '日落10', '13562396975', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'sa', '凡人歌热热热14', '无钱无权额16', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (300, 'B', '旧', '进货', '米糠3', '东城铝业10', '李四10', '13864939865', '网袋60*95蓝色52克21', '', '有', '李丹11', '日落11', '13562396976', '', '', '', NULL, '', NULL, '00566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路37号', '', 'sa', '凡人歌热热热15', '无钱无权额17', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (301, 'C', '旧', '销售', '面粉4', '东城铝业11', '李四11', '13864939866', '网袋60*95蓝色52克22', '', '有', '李丹12', '日落12', '13562396977', '', '', '', NULL, '', NULL, '00543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'sa', '凡人歌热热热16', '无钱无权额18', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (302, 'D', '新', '进货', '地瓜4', '东城铝业12', '李四12', '13864939867', '网袋60*95蓝色52克23', '', '有', '李丹13', '日落13', '13562396978', '', '', '', NULL, '', NULL, '00362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'sa', '凡人歌热热热17', '无钱无权额19', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (303, 'A', '新', '销售', '米糠4', '东城铝业13', '李四13', '13864939868', '网袋60*95蓝色52克24', '', '有', '李丹14', '日落14', '13562396979', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'sa', '凡人歌热热热18', '无钱无权额20', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (304, 'B', '旧', '进货', '面粉5', '东城铝业14', '李四14', '13864939869', '网袋60*95蓝色52克25', '', '有', '李丹15', '日落15', '13562396980', '', '', '', NULL, '', NULL, '00566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路38号', '', 'sa', '凡人歌热热热19', '无钱无权额21', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (305, 'C', '旧', '销售', '地瓜5', '东城铝业15', '李四15', '13864939870', '网袋60*95蓝色52克26', '', '有', '李丹16', '日落16', '13562396981', '', '', '', NULL, '', NULL, '00543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'sa', '凡人歌热热热20', '无钱无权额22', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (306, 'D', '新', '进货', '米糠5', '东城铝业16', '李四16', '13864939871', '网袋60*95蓝色52克27', '有', '有', '李丹17', '日落17', '13562396982', '', '', '', NULL, '', NULL, '00362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'sa', '凡人歌热热热21', '无钱无权额23', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (307, 'A', '新', '销售', '面粉6', '东城铝业17', '李四17', '13864939872', '网袋60*95蓝色52克28', '有', '有', '李丹18', '日落18', '13562396983', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'sa', '凡人歌热热热22', '无钱无权额24', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (308, 'B', '旧', '进货', '地瓜6', '东城铝业18', '李四18', '13864939873', '网袋60*95蓝色52克29', '有', '有', '李丹19', '日落19', '13562396984', '', '', '', NULL, '', NULL, '00566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路39号', '', 'sa', '凡人歌热热热23', '无钱无权额25', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (309, 'C', '旧', '销售', '米糠6', '东城铝业19', '李四19', '13864939874', '网袋60*95蓝色52克30', '有', '有', '李丹20', '日落20', '13562396985', '', '', '', NULL, '', NULL, '00543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'sa', '凡人歌热热热24', '无钱无权额26', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (310, 'D', '新', '进货', '面粉7', '东城铝业20', '李四20', '13864939875', '网袋60*95蓝色52克31', '有', '', '李丹21', '日落21', '13562396986', '', '', '', NULL, '', NULL, '00362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'sa', '凡人歌热热热25', '无钱无权额27', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (311, 'A', '新', '销售', '地瓜7', '东城铝业21', '李四21', '13864939876', '网袋60*95蓝色52克32', '有', '', '李丹22', '日落22', '13562396987', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'sa', '凡人歌热热热26', '无钱无权额28', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (312, 'B', '旧', '进货', '米糠7', '东城铝业22', '李四22', '13864939877', '网袋60*95蓝色52克33', '有', '', '李丹23', '日落23', '13562396988', '', '', '', NULL, '', NULL, '00566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路40号', '', 'sa', '凡人歌热热热27', '无钱无权额29', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (313, 'C', '旧', '销售', '面粉8', '东城铝业23', '李四23', '13864939878', '网袋60*95蓝色52克34', '有', '', '李丹24', '日落24', '13562396989', '', '', '', NULL, '', NULL, '00543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'sa', '凡人歌热热热28', '无钱无权额30', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (314, 'D', '新', '进货', '地瓜8', '东城铝业24', '李四24', '13864939879', '网袋60*95蓝色52克35', '有', '', '李丹25', '日落25', '13562396990', '', '', '', NULL, '', NULL, '00362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'sa', '凡人歌热热热29', '无钱无权额31', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (315, 'A', '新', '销售', '米糠8', '东城铝业25', '李四25', '13864939880', '网袋60*95蓝色52克36', '有', '', '李丹26', '日落26', '13562396991', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'sa', '凡人歌热热热30', '无钱无权额32', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (316, 'B', '旧', '进货', '面粉9', '东城铝业26', '李四26', '13864939881', '网袋60*95蓝色52克37', '有', '', '李丹27', '日落27', '13562396992', '', '', '', NULL, '', NULL, '00566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路41号', '', 'sa', '凡人歌热热热31', '无钱无权额33', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (317, 'C', '旧', '销售', '地瓜9', '东城铝业27', '李四27', '13864939882', '网袋60*95蓝色52克38', '有', '', '李丹28', '日落28', '13562396993', '', '', '', NULL, '', NULL, '00543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'sa', '凡人歌热热热32', '无钱无权额34', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (318, 'D', '新', '进货', '米糠9', '东城铝业28', '李四28', '13864939883', '网袋60*95蓝色52克39', '有', '', '李丹29', '日落29', '13562396994', '', '', '', NULL, '', NULL, '00362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'sa', '凡人歌热热热33', '无钱无权额35', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (319, 'A', '新', NULL, '面粉10', '东城铝业29', NULL, '13864939884', '网袋60*95蓝色52克40', NULL, NULL, '李丹30', '测试123456', '13562396995', '', '', '', NULL, '', NULL, NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'sa', '凡人歌热热热34', '无钱无权额36', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_consumer` VALUES (320, 'A', '新', '销售', '塑料颗粒', '大有塑业', '王林', '13864939852', '编织袋60*110次黄52克覆膜装地瓜', '有', '', '李元', '爱在深秋', '13562396963', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'admin', '凡人歌热热热2', '无钱无权额4', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (321, 'B', '旧', '进货', '面粉', '合成塑胶', '赵三', '13864939853', '编织袋40*60半透', '有', '', '李芳', '趟水过河', '13562396964', '', '', '', NULL, '', NULL, '0566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路34号', '', 'admin', '凡人歌热热热3', '无钱无权额5', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (322, 'C', '旧', '销售', '地瓜', '兰州天利', '李四', '13864939854', '编织袋50*90灰色47克', '有', '', '侯明昊', '执照', '13562396965', '', '', '', NULL, '', NULL, '0543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'admin', '凡人歌热热热4', '无钱无权额6', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (323, 'D', '新', '进货', '米糠', '东城铝业', '王六', '13864939855', '网袋60*95蓝色52克11', '有', '', '李丹1', '日落1', '13562396966', '', '', '', NULL, '', NULL, '0362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'admin', '凡人歌热热热5', '无钱无权额7', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (324, 'A', '新', '销售', '塑料颗粒', '东城铝业1', '李四1', '13864939856', '网袋60*95蓝色52克12', '有', '有', '李丹2', '日落2', '13562396967', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'admin', '凡人歌热热热6', '无钱无权额8', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (325, 'B', '旧', '进货', '面粉1', '东城铝业2', '李四2', '13864939857', '网袋60*95蓝色52克13', '有', '有', '李丹3', '日落3', '13562396968', '', '', '', NULL, '', NULL, '00566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路35号', '', 'admin', '凡人歌热热热7', '无钱无权额9', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (326, 'C', '旧', '销售', '地瓜1', '东城铝业3', '李四3', '13864939858', '网袋60*95蓝色52克14', '有', '有', '李丹4', '日落4', '13562396969', '', '', '', NULL, '', NULL, '00543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'admin', '凡人歌热热热8', '无钱无权额10', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (327, 'D', '新', '进货', '米糠1', '东城铝业4', '李四4', '13864939859', '网袋60*95蓝色52克15', '有', '有', '李丹5', '日落5', '13562396970', '', '', '', NULL, '', NULL, '00362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'admin', '凡人歌热热热9', '无钱无权额11', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (328, 'A', '新', '销售', '面粉2', '东城铝业5', '李四5', '13864939860', '网袋60*95蓝色52克16', '有', '有', '李丹6', '日落6', '13562396971', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'admin', '凡人歌热热热10', '无钱无权额12', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (329, 'B', '旧', '进货', '地瓜2', '东城铝业6', '李四6', '13864939861', '网袋60*95蓝色52克17', '有', '有', '李丹7', '日落7', '13562396972', '', '', '', NULL, '', NULL, '00566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路36号', '', 'admin', '凡人歌热热热11', '无钱无权额13', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (330, 'C', '旧', '销售', '米糠2', '东城铝业7', '李四7', '13864939862', '网袋60*95蓝色52克18', '', '有', '李丹8', '日落8', '13562396973', '', '', '', NULL, '', NULL, '00543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'admin', '凡人歌热热热12', '无钱无权额14', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (331, 'D', '新', '进货', '面粉3', '东城铝业8', '李四8', '13864939863', '网袋60*95蓝色52克19', '', '有', '李丹9', '日落9', '13562396974', '', '', '', NULL, '', NULL, '00362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'admin', '凡人歌热热热13', '无钱无权额15', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (332, 'A', '新', '销售', '地瓜3', '东城铝业9', '李四9', '13864939864', '网袋60*95蓝色52克20', '', '有', '李丹10', '日落10', '13562396975', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'admin', '凡人歌热热热14', '无钱无权额16', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (333, 'B', '旧', '进货', '米糠3', '东城铝业10', '李四10', '13864939865', '网袋60*95蓝色52克21', '', '有', '李丹11', '日落11', '13562396976', '', '', '', NULL, '', NULL, '00566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路37号', '', 'admin', '凡人歌热热热15', '无钱无权额17', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (334, 'C', '旧', '销售', '面粉4', '东城铝业11', '李四11', '13864939866', '网袋60*95蓝色52克22', '', '有', '李丹12', '日落12', '13562396977', '', '', '', NULL, '', NULL, '00543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'admin', '凡人歌热热热16', '无钱无权额18', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (335, 'D', '新', '进货', '地瓜4', '东城铝业12', '李四12', '13864939867', '网袋60*95蓝色52克23', '', '有', '李丹13', '日落13', '13562396978', '', '', '', NULL, '', NULL, '00362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'admin', '凡人歌热热热17', '无钱无权额19', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (336, 'A', '新', '销售', '米糠4', '东城铝业13', '李四13', '13864939868', '网袋60*95蓝色52克24', '', '有', '李丹14', '日落14', '13562396979', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'admin', '凡人歌热热热18', '无钱无权额20', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (337, 'B', '旧', '进货', '面粉5', '东城铝业14', '李四14', '13864939869', '网袋60*95蓝色52克25', '', '有', '李丹15', '日落15', '13562396980', '', '', '', NULL, '', NULL, '00566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路38号', '', 'admin', '凡人歌热热热19', '无钱无权额21', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (338, 'C', '旧', '销售', '地瓜5', '东城铝业15', '李四15', '13864939870', '网袋60*95蓝色52克26', '', '有', '李丹16', '日落16', '13562396981', '', '', '', NULL, '', NULL, '00543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'admin', '凡人歌热热热20', '无钱无权额22', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (339, 'D', '新', '进货', '米糠5', '东城铝业16', '李四16', '13864939871', '网袋60*95蓝色52克27', '有', '有', '李丹17', '日落17', '13562396982', '', '', '', NULL, '', NULL, '00362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'admin', '凡人歌热热热21', '无钱无权额23', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (340, 'A', '新', '销售', '面粉6', '东城铝业17', '李四17', '13864939872', '网袋60*95蓝色52克28', '有', '有', '李丹18', '日落18', '13562396983', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'admin', '凡人歌热热热22', '无钱无权额24', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (341, 'B', '旧', '进货', '地瓜6', '东城铝业18', '李四18', '13864939873', '网袋60*95蓝色52克29', '有', '有', '李丹19', '日落19', '13562396984', '', '', '', NULL, '', NULL, '00566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路39号', '', 'admin', '凡人歌热热热23', '无钱无权额25', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (342, 'C', '旧', '销售', '米糠6', '东城铝业19', '李四19', '13864939874', '网袋60*95蓝色52克30', '有', '有', '李丹20', '日落20', '13562396985', '', '', '', NULL, '', NULL, '00543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'admin', '凡人歌热热热24', '无钱无权额26', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (343, 'D', '新', '进货', '面粉7', '东城铝业20', '李四20', '13864939875', '网袋60*95蓝色52克31', '有', '', '李丹21', '日落21', '13562396986', '', '', '', NULL, '', NULL, '00362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'admin', '凡人歌热热热25', '无钱无权额27', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (344, 'A', '新', '销售', '地瓜7', '东城铝业21', '李四21', '13864939876', '网袋60*95蓝色52克32', '有', '', '李丹22', '日落22', '13562396987', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'admin', '凡人歌热热热26', '无钱无权额28', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (345, 'B', '旧', '进货', '米糠7', '东城铝业22', '李四22', '13864939877', '网袋60*95蓝色52克33', '有', '', '李丹23', '日落23', '13562396988', '', '', '', NULL, '', NULL, '00566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路40号', '', 'admin', '凡人歌热热热27', '无钱无权额29', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (346, 'C', '旧', '销售', '面粉8', '东城铝业23', '李四23', '13864939878', '网袋60*95蓝色52克34', '有', '', '李丹24', '日落24', '13562396989', '', '', '', NULL, '', NULL, '00543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'admin', '凡人歌热热热28', '无钱无权额30', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (347, 'D', '新', '进货', '地瓜8', '东城铝业24', '李四24', '13864939879', '网袋60*95蓝色52克35', '有', '', '李丹25', '日落25', '13562396990', '', '', '', NULL, '', NULL, '00362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'admin', '凡人歌热热热29', '无钱无权额31', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (348, 'A', '新', '销售', '米糠8', '东城铝业25', '李四25', '13864939880', '网袋60*95蓝色52克36', '有', '', '李丹26', '日落26', '13562396991', '', '', '', NULL, '', NULL, '05394056369', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '山东省临沂市沂南县大庄镇李家村', '', 'admin', '凡人歌热热热30', '无钱无权额32', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (349, 'B', '旧', '进货', '面粉9', '东城铝业26', '李四26', '13864939881', '网袋60*95蓝色52克37', '有', '', '李丹27', '日落27', '13562396992', '', '', '', NULL, '', NULL, '00566969693', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '安徽合肥同安路41号', '', 'admin', '凡人歌热热热31', '无钱无权额33', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (350, 'C', '旧', '销售', '地瓜9', '东城铝业27', '李四27', '13864939882', '网袋60*95蓝色52克38', '有', '', '李丹28', '日落28', '13562396993', '', '', '', NULL, '', NULL, '00543696963', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '黑龙江齐齐哈尔', '', 'admin', '凡人歌热热热32', '无钱无权额34', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (351, 'D', '新', '进货', '米糠9', '东城铝业28', '李四28', '13864939883', '网袋60*95蓝色52克39', '有', '', '李丹29', '日落29', '13562396994', '', '', '', NULL, '', NULL, '00362563253', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '辽宁沈阳', '', 'admin', '凡人歌热热热33', '无钱无权额35', NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `sys_consumer` VALUES (352, 'A', '新', NULL, '面粉10', '东城铝业29', NULL, '13864939884', '网袋60*95蓝色52克40', NULL, NULL, '李丹30', '日落30', '13562396995', '', '', '', NULL, '', NULL, NULL, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '111111', '', '山东省临沂市沂南县大庄镇李家村', '', 'admin', '凡人歌热热热34', '无钱无权额36', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `menu_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单路径',
  `menu_parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统菜单表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('35cb950cebb04bb18bb1d8b742a02005', '客户查询', '/costomer/find', '35cb950cebb04bb18bb1d8b742a02005', '2019-09-11 18:05:21', '2020-10-20 12:37:37');
INSERT INTO `sys_menu` VALUES ('35cb950cebb04bb18bb1d8b742a02xaa', '权限管理', '/sys/sysAuthority/authority', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-10 10:08:58', '2019-09-10 10:08:58');
INSERT INTO `sys_menu` VALUES ('35cb950cebb04bb18bb1d8b742a02xcc', '菜单管理', '/sys/sysMenu/menu', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-10 10:08:58', '2019-09-10 10:08:58');
INSERT INTO `sys_menu` VALUES ('35cb950cebb04bb18bb1d8b742a02xxx', '系统管理', '/sys', '', '2019-09-10 10:08:58', '2019-09-10 10:08:58');
INSERT INTO `sys_menu` VALUES ('35cb950cebb04bb18bb1d8b742a02xzz', '用户管理', '/sys/sysUser/user', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-10 10:08:58', '2019-09-10 10:08:58');
INSERT INTO `sys_menu` VALUES ('4e8bd8f2bb9d4b4fa054bdac224ebcf1', '查询客户', '/consumer/find', 'e665bf3364934ee58699723e29dc136f', '2020-10-20 12:39:10', '2020-10-20 12:39:10');
INSERT INTO `sys_menu` VALUES ('74315e162f524a4d88aa931f02416f26', '实时监控', '/monitor', '35cb950cebb04bb18bb1d8b742a02xxx', '2020-06-10 15:07:07', '2020-06-10 15:07:07');
INSERT INTO `sys_menu` VALUES ('914aa22c78af4327822061f3eada4067', '实时日志', '/logging', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-11 11:19:52', '2019-09-11 11:19:52');
INSERT INTO `sys_menu` VALUES ('bcf17dc0ce304f0ba02d64ce21ddb4f9', '系统设置', '/sys/sysSetting/setting', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-17 10:46:11', '2019-09-17 10:46:11');
INSERT INTO `sys_menu` VALUES ('e665bf3364934ee58699723e29dc136f', '用户信息', '', '', '2020-10-20 12:38:48', '2020-10-27 23:26:38');

-- ----------------------------
-- Table structure for sys_setting
-- ----------------------------
DROP TABLE IF EXISTS `sys_setting`;
CREATE TABLE `sys_setting`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表id',
  `sys_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统名称',
  `sys_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统logo图标',
  `sys_bottom_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统底部信息',
  `sys_notice_text` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '系统公告',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `user_init_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户管理：初始、重置密码',
  `sys_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统颜色',
  `sys_api_encrypt` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'API加密 Y/N',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统设置表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_setting
-- ----------------------------
INSERT INTO `sys_setting` VALUES ('1', '客户信息管理系统', 'https://avatar.gitee.com/uploads/0/5137900_huanzi-qch.png!avatar100?1562729811', '© 2019 - 2020  客户信息管理系统', '<h1 style=\"white-space: normal; text-align: center;\"><span style=\"color: rgb(255, 0, 0);\">通知</span></h1><p style=\"white-space: normal;\"><span style=\"color: rgb(255, 0, 0);\">1、不得在公共场合吸烟；</span></p><p style=\"white-space: normal;\"><span style=\"color: rgb(255, 0, 0);\">2、xxxxxxx；</span></p><p><br/></p>', '2019-09-17 10:15:38', '2020-10-27 23:36:41', '123456', 'rgba(37, 132, 215, 0.73)', 'Y');

-- ----------------------------
-- Table structure for sys_shortcut_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_shortcut_menu`;
CREATE TABLE `sys_shortcut_menu`  (
  `shortcut_menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户快捷菜单id',
  `shortcut_menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户快捷菜单名称',
  `shortcut_menu_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户快捷菜单路径',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `shortcut_menu_parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上级id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`shortcut_menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户快捷菜单表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_shortcut_menu
-- ----------------------------
INSERT INTO `sys_shortcut_menu` VALUES ('72d94b41b9994038bd2f2135a1de28d8', '快捷菜单', '', 'b5ac62e154964151a19c565346bb354a', '', '2019-09-17 14:36:28', '2019-09-17 14:36:28');
INSERT INTO `sys_shortcut_menu` VALUES ('88353f04ad5d47b182c984bfbb1693cc', 'ggg', '/xxx', 'b5ac62e154964151a19c565346bb354a', '72d94b41b9994038bd2f2135a1de28d8', '2019-09-17 14:36:50', '2019-09-17 14:36:50');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `valid` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '软删除标识，Y/N',
  `limited_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '限制允许登录的IP集合',
  `expired_time` datetime(0) NULL DEFAULT NULL COMMENT '账号失效时间，超过时间将不能登录系统',
  `last_change_pwd_time` datetime(0) NOT NULL COMMENT '最近修改密码时间，超出时间间隔，提示用户修改密码',
  `limit_multi_login` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否允许账号同一个时刻多人在线，Y/N',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'sa', '超级管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 'Y', '', NULL, '2019-09-17 12:00:36', 'Y', '2019-07-19 16:36:03', '2020-10-20 23:12:07');
INSERT INTO `sys_user` VALUES ('2', 'admin', '管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 'Y', '', NULL, '2019-09-17 12:00:36', 'N', '2019-07-19 16:36:03', '2020-11-04 15:01:28');
INSERT INTO `sys_user` VALUES ('3fb1c570496d4c09ab99b8d31b0671cf', 'daji', '妲己', 'E10ADC3949BA59ABBE56E057F20F883E', 'Y', '', NULL, '2019-09-17 12:00:36', 'Y', '2019-09-11 18:11:41', '2019-09-17 12:09:47');
INSERT INTO `sys_user` VALUES ('b5ac62e154964151a19c565346bb354a', 'xiaofang', '小芳', 'E10ADC3949BA59ABBE56E057F20F883E', 'Y', '', NULL, '2019-09-17 12:00:36', 'N', '2019-09-17 14:12:41', '2020-10-28 00:46:05');

-- ----------------------------
-- Table structure for sys_user_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_authority`;
CREATE TABLE `sys_user_authority`  (
  `user_authority_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户权限表id',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `authority_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_authority_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户权限表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_user_authority
-- ----------------------------
INSERT INTO `sys_user_authority` VALUES ('4f98ec0c26584dcba59c3c1d67a3f80b', 'b5ac62e154964151a19c565346bb354a', '3fb1c570496d4c09ab99b8d31b06ccc', '2020-10-28 00:44:55', '2020-10-28 00:44:55');
INSERT INTO `sys_user_authority` VALUES ('57dca083c9e74b2596e0baab7d833a46', '1', '3fb1c570496d4c09ab99b8d31b06xxx', '2020-10-20 23:12:07', '2020-10-20 23:12:07');
INSERT INTO `sys_user_authority` VALUES ('90c18739f3ad41ae8010f6c2b7eeaac5', '3fb1c570496d4c09ab99b8d31b0671cf', '3fb1c570496d4c09ab99b8d31b06ccc', '2019-09-17 12:09:47', '2019-09-17 12:09:47');
INSERT INTO `sys_user_authority` VALUES ('e8b819d9ca3d422898434e17f34d847e', '1', '3fb1c570496d4c09ab99b8d31b06ccc', '2020-10-20 23:12:07', '2020-10-20 23:12:07');
INSERT INTO `sys_user_authority` VALUES ('f2f3f86fc4d9414b8de2529bdfb63ea4', '1', '3fb1c570496d4c09ab99b8d31b06zzz', '2020-10-20 23:12:07', '2020-10-20 23:12:07');
INSERT INTO `sys_user_authority` VALUES ('fc7c1eefd0a44f639264e0311af4adce', '2', '3fb1c570496d4c09ab99b8d31b06zzz', '2020-11-04 15:01:29', '2020-11-04 15:01:29');

-- ----------------------------
-- Table structure for sys_user_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_menu`;
CREATE TABLE `sys_user_menu`  (
  `user_menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户菜单表id',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户菜单表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_user_menu
-- ----------------------------
INSERT INTO `sys_user_menu` VALUES ('05d6f2d8d32747f0a1583535e6cfa4e5', 'b5ac62e154964151a19c565346bb354a', 'e665bf3364934ee58699723e29dc136f', '2020-10-28 00:44:55', '2020-10-28 00:44:55');
INSERT INTO `sys_user_menu` VALUES ('0d47a922c1c44951bfd45c66ef504de9', '1', '35cb950cebb04bb18bb1d8b742a02xaa', '2020-10-20 23:12:07', '2020-10-20 23:12:07');
INSERT INTO `sys_user_menu` VALUES ('26ed1d8b088d41549d962559010333ca', '1', '35cb950cebb04bb18bb1d8b742a02xxx', '2020-10-20 23:12:07', '2020-10-20 23:12:07');
INSERT INTO `sys_user_menu` VALUES ('41d3dc6b9cf14056bd0f569b89641603', '2', '35cb950cebb04bb18bb1d8b742a02xxx', '2020-11-04 15:01:29', '2020-11-04 15:01:29');
INSERT INTO `sys_user_menu` VALUES ('453214513fbe466b842d90ab60e269e8', '1', 'e665bf3364934ee58699723e29dc136f', '2020-10-20 23:12:07', '2020-10-20 23:12:07');
INSERT INTO `sys_user_menu` VALUES ('6e8fe2b9307a4855ba7d006dc17c97ae', '3fb1c570496d4c09ab99b8d31b0671cf', '35cb950cebb04bb18bb1d8b742a02005', '2019-09-17 12:09:47', '2019-09-17 12:09:47');
INSERT INTO `sys_user_menu` VALUES ('7600d1904f5d4020ad8c0d1f1004a58d', '2', '4e8bd8f2bb9d4b4fa054bdac224ebcf1', '2020-11-04 15:01:29', '2020-11-04 15:01:29');
INSERT INTO `sys_user_menu` VALUES ('784110277bfe4e9c80f0a82f2f52993f', '1', 'bcf17dc0ce304f0ba02d64ce21ddb4f9', '2020-10-20 23:12:07', '2020-10-20 23:12:07');
INSERT INTO `sys_user_menu` VALUES ('7a97c7087829433e8c1a912450bc53ac', '2', 'e665bf3364934ee58699723e29dc136f', '2020-11-04 15:01:29', '2020-11-04 15:01:29');
INSERT INTO `sys_user_menu` VALUES ('80dc80ea7596484c8e5b6a7ddc97ed56', '1', '35cb950cebb04bb18bb1d8b742a02xzz', '2020-10-20 23:12:07', '2020-10-20 23:12:07');
INSERT INTO `sys_user_menu` VALUES ('a6d64eedfaf341f3ae6ab812d25ecb32', '1', '4e8bd8f2bb9d4b4fa054bdac224ebcf1', '2020-10-20 23:12:07', '2020-10-20 23:12:07');
INSERT INTO `sys_user_menu` VALUES ('ae1a02644a244279ac43a4167888dab4', '1', '74315e162f524a4d88aa931f02416f26', '2020-10-20 23:12:07', '2020-10-20 23:12:07');
INSERT INTO `sys_user_menu` VALUES ('b1853b2bf45a4018b8e30386825dab69', '2', '35cb950cebb04bb18bb1d8b742a02xzz', '2020-11-04 15:01:29', '2020-11-04 15:01:29');
INSERT INTO `sys_user_menu` VALUES ('b746e025ada6452c96cf44e3f3a52c3b', '2', '35cb950cebb04bb18bb1d8b742a02xcc', '2020-11-04 15:01:29', '2020-11-04 15:01:29');
INSERT INTO `sys_user_menu` VALUES ('d96186ca71c9464aa8cc82b2dd534f18', 'b5ac62e154964151a19c565346bb354a', '4e8bd8f2bb9d4b4fa054bdac224ebcf1', '2020-10-28 00:44:55', '2020-10-28 00:44:55');
INSERT INTO `sys_user_menu` VALUES ('deeb83ecd92344ac8a0e38d167c78d75', '2', '35cb950cebb04bb18bb1d8b742a02xaa', '2020-11-04 15:01:29', '2020-11-04 15:01:29');
INSERT INTO `sys_user_menu` VALUES ('f94772f5a4384a89a651f2c064379a86', '1', '35cb950cebb04bb18bb1d8b742a02xcc', '2020-10-20 23:12:07', '2020-10-20 23:12:07');
INSERT INTO `sys_user_menu` VALUES ('ff8acce5b09e4d3e90092f2e082b5ef1', '1', '914aa22c78af4327822061f3eada4067', '2020-10-20 23:12:07', '2020-10-20 23:12:07');

SET FOREIGN_KEY_CHECKS = 1;
