/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50712
 Source Host           : localhost:3306
 Source Schema         : ecarpool

 Target Server Type    : MySQL
 Target Server Version : 50712
 File Encoding         : 65001

 Date: 16/01/2022 21:27:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for carpool_ad
-- ----------------------------
DROP TABLE IF EXISTS `carpool_ad`;
CREATE TABLE `carpool_ad` (
  `id` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `ad_position_id` smallint(5) unsigned NOT NULL DEFAULT '0',
  `media_type` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `name` varchar(60) DEFAULT '',
  `link` varchar(255) DEFAULT '',
  `image_url` text,
  `content` varchar(255) DEFAULT '',
  `end_time` datetime DEFAULT NULL,
  `enabled` tinyint(3) unsigned DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `position_id` (`ad_position_id`),
  KEY `enabled` (`enabled`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of carpool_ad
-- ----------------------------
BEGIN;
INSERT INTO `carpool_ad` VALUES (2, 2, 1, '拼车轮播图', '', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/banner/b2.jpg', '轮播图', '2022-01-13 14:16:18', 1);
INSERT INTO `carpool_ad` VALUES (1, 2, 1, '拼车轮播图', '', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/banner/b1.png', '轮播图', '2018-02-13 18:15:29', 1);
INSERT INTO `carpool_ad` VALUES (3, 2, 1, '拼车轮播图', '', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/banner/b3.jpg', '轮播图', '2022-01-13 14:18:10', 1);
COMMIT;

-- ----------------------------
-- Table structure for carpool_car
-- ----------------------------
DROP TABLE IF EXISTS `carpool_car`;
CREATE TABLE `carpool_car` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(11) DEFAULT NULL COMMENT '发布人id',
  `car_type` varchar(50) DEFAULT NULL COMMENT '车辆类型  1 轿车 2 SUV  3 出租车',
  `color` varchar(30) DEFAULT NULL COMMENT '车辆颜色 1 白色 2 黑色 3 红色  4 银色  5 灰色  6蓝色 7 其他',
  `plate_number_prefix` varchar(30) DEFAULT NULL COMMENT '车牌号前缀',
  `plate_number` varchar(30) DEFAULT NULL COMMENT '车牌号',
  `car_brand` varchar(50) DEFAULT NULL COMMENT '品牌',
  `driver_license` varchar(255) DEFAULT NULL COMMENT '驾驶证',
  `driving_license` varchar(255) DEFAULT NULL COMMENT '行驶证',
  `seat_nums` int(2) DEFAULT NULL COMMENT '车座位数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='车辆信息\n';

-- ----------------------------
-- Records of carpool_car
-- ----------------------------
BEGIN;
INSERT INTO `carpool_car` VALUES (1, 1, '轿车', '黑', '京A', '12345', '宝马6X', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/banner/b1.png', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/banner/b1.png', 5);
COMMIT;

-- ----------------------------
-- Table structure for carpool_chat
-- ----------------------------
DROP TABLE IF EXISTS `carpool_chat`;
CREATE TABLE `carpool_chat` (
  `id` int(11) NOT NULL COMMENT '自增id',
  `publish_id` int(11) DEFAULT NULL COMMENT '行程id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `message_type` smallint(2) DEFAULT NULL COMMENT '消息类型  0 普通消息   1 系统提示  2 公告',
  `message` text COMMENT '消息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `data_status` smallint(2) DEFAULT '0' COMMENT '数据状态 0 正常 1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拼车聊天\n';

-- ----------------------------
-- Records of carpool_chat
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for carpool_cms_article
-- ----------------------------
DROP TABLE IF EXISTS `carpool_cms_article`;
CREATE TABLE `carpool_cms_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `resume` varchar(300) DEFAULT NULL COMMENT '简述',
  `content` text COMMENT '内容',
  `url` varchar(255) DEFAULT NULL COMMENT '配图',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `view_num` int(22) DEFAULT '0' COMMENT '查看数量',
  `data_status` smallint(2) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='文章咨询\n';

-- ----------------------------
-- Records of carpool_cms_article
-- ----------------------------
BEGIN;
INSERT INTO `carpool_cms_article` VALUES (1, '高端中大型5座电动SUV', '新车定位于高端中大型5座电动SUV，以用户实际出行场景为出发点，将智能、易用和美感融入在设计细节中，打造出一款满足日常通勤又兼具探索气质的人性化全场景智能电动SUV。', '自游家NV的官图正式发布，新车定位于高端中大型5座电动SUV，在设计理念上结合了Urban Exploring城市探索融合风格，以用户实际出行场景为出发点，将智能、易用和美感融入在设计细节中，打造出一款满足日常通勤又兼具探索气质的人性化全场景智能电动SUV。', 'https://nimg.ws.126.net/?url=http%3A%2F%2Fcms-bucket.ws.126.net%2F2022%2F0112%2Fde7e15eej00r5ks4c0026c000sg00lcc.jpg&thumbnail=660x2147483647&quality=80&type=jpg', '易拼车', '2022-01-13 14:41:51', '2022-01-13 14:41:54', 13, 0);
INSERT INTO `carpool_cms_article` VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for carpool_feedback
-- ----------------------------
DROP TABLE IF EXISTS `carpool_feedback`;
CREATE TABLE `carpool_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `platform_id` int(11) DEFAULT NULL,
  `type` smallint(2) DEFAULT NULL COMMENT '类型   1 反馈  2 回复',
  `title` varchar(500) DEFAULT NULL COMMENT '反馈标题',
  `content` text COMMENT '反馈内容',
  `img_urls` text,
  `feedback_time` datetime DEFAULT NULL COMMENT '反馈时间',
  `wx_user_id` int(11) DEFAULT NULL COMMENT '微信用户id',
  `wx_nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `open_id` varchar(100) DEFAULT NULL COMMENT '用户标示',
  `form_id` varchar(100) DEFAULT NULL COMMENT '用户提交反馈formId',
  `replyer_id` int(11) DEFAULT NULL COMMENT '回复人id',
  `replyer_name` varchar(50) DEFAULT NULL COMMENT '回复人姓名',
  `reply_content` text COMMENT '回复内容',
  `reply_to` int(11) DEFAULT NULL COMMENT '回复反馈的id',
  `reply_time` datetime DEFAULT NULL COMMENT '回复时间',
  `data_status` smallint(2) DEFAULT '0' COMMENT '数据状态  0  正常   1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='微信用户反馈\n';

-- ----------------------------
-- Records of carpool_feedback
-- ----------------------------
BEGIN;
INSERT INTO `carpool_feedback` VALUES (1, NULL, NULL, NULL, NULL, NULL, '2022-01-16 19:39:48', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for carpool_nav
-- ----------------------------
DROP TABLE IF EXISTS `carpool_nav`;
CREATE TABLE `carpool_nav` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(30) DEFAULT NULL,
  `icon` varchar(1250) DEFAULT NULL,
  `disabled_icon` varchar(1250) DEFAULT NULL,
  `tip` varchar(200) DEFAULT NULL,
  `open` smallint(2) DEFAULT NULL,
  `need_login` smallint(2) DEFAULT NULL,
  `skip_to` varchar(255) DEFAULT NULL,
  `data_status` smallint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='拼车导航\n';

-- ----------------------------
-- Records of carpool_nav
-- ----------------------------
BEGIN;
INSERT INTO `carpool_nav` VALUES (1, '车找人', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/icon/i1.png', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/icon/i1.png', '车找人', 0, 0, '/pages/carpool/carpoolList/carpoolList?type=0', 0);
INSERT INTO `carpool_nav` VALUES (2, '人找车', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/icon/i2.png', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/icon/i2.png', '人找车', 0, 0, '/pages/carpool/carpoolList/carpoolList?type=1', 0);
INSERT INTO `carpool_nav` VALUES (3, '车友圈', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/icon/i3.png', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/icon/i3.png', '车友圈', 0, 0, '/pages/community/index', 0);
INSERT INTO `carpool_nav` VALUES (4, '车周边', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/icon/i4.png', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/icon/i4.png', '车周边', 0, 0, '/pages/arounds/index', 0);
COMMIT;

-- ----------------------------
-- Table structure for carpool_notice
-- ----------------------------
DROP TABLE IF EXISTS `carpool_notice`;
CREATE TABLE `carpool_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` varchar(3000) DEFAULT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `data_status` smallint(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='公告\n';

-- ----------------------------
-- Records of carpool_notice
-- ----------------------------
BEGIN;
INSERT INTO `carpool_notice` VALUES (1, '文明公告', '文明乘车，合法拼车', '2022-01-13 14:27:40', 0);
COMMIT;

-- ----------------------------
-- Table structure for carpool_order
-- ----------------------------
DROP TABLE IF EXISTS `carpool_order`;
CREATE TABLE `carpool_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `publish_id` int(11) DEFAULT NULL COMMENT '行程id',
  `user_type` smallint(2) DEFAULT NULL COMMENT '用户类型  0 乘客 1司机',
  `order_user_id` int(11) DEFAULT NULL COMMENT '预约人id',
  `order_user_name` varchar(50) DEFAULT NULL COMMENT '预约/抢单人姓名',
  `mobile` varchar(22) DEFAULT NULL COMMENT '联系电话',
  `passenger_num` int(3) DEFAULT NULL COMMENT '乘客人数',
  `startPoint` varchar(2000) DEFAULT NULL COMMENT '具体的出发地点',
  `start_point_longitude` decimal(10,2) DEFAULT NULL COMMENT '起点经度',
  `start_point_latitude` decimal(10,2) DEFAULT NULL COMMENT '起点维度',
  `start_point_geo` varchar(100) DEFAULT NULL COMMENT '起点GEO编码',
  `destination` varchar(2000) DEFAULT NULL COMMENT '终点',
  `destination_longitude` decimal(10,2) DEFAULT NULL COMMENT '终点经度',
  `destination_latitude` decimal(10,2) DEFAULT NULL COMMENT '终点维度',
  `destination_geo` varchar(100) DEFAULT NULL COMMENT '终点GEO编码',
  `departure_time` datetime DEFAULT NULL COMMENT '出发时间',
  `status` smallint(2) DEFAULT NULL COMMENT '状态  0 预约中  1  预约成功  2 拒绝   3 取消  4 失效',
  `refuse_reason` varchar(2000) DEFAULT NULL COMMENT '拒绝原因',
  `cancel_reason` varchar(2000) DEFAULT NULL COMMENT '取消原因',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  `operator_name` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `bake` varchar(3000) DEFAULT NULL COMMENT '备注信息',
  `data_status` smallint(2) DEFAULT '0' COMMENT '数据状态  0 正常  1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单';

-- ----------------------------
-- Records of carpool_order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for carpool_order_log
-- ----------------------------
DROP TABLE IF EXISTS `carpool_order_log`;
CREATE TABLE `carpool_order_log` (
  `id` int(11) NOT NULL COMMENT '自增id',
  `order_id` int(11) DEFAULT NULL COMMENT '预约单id',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  `operator_name` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `content` text COMMENT '操作内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拼车操作日志\n';

-- ----------------------------
-- Records of carpool_order_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for carpool_publish
-- ----------------------------
DROP TABLE IF EXISTS `carpool_publish`;
CREATE TABLE `carpool_publish` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `publish_user_id` int(11) DEFAULT NULL COMMENT '发布人id',
  `user_type` smallint(2) DEFAULT NULL COMMENT '用户类型  0 乘客 1司机',
  `type` smallint(2) DEFAULT NULL COMMENT '拼车类型',
  `mobile` varchar(22) DEFAULT NULL COMMENT '联系电话',
  `price` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `start_point` varchar(2000) DEFAULT NULL COMMENT '具体的出发地点',
  `start_point_longitude` decimal(10,2) DEFAULT NULL COMMENT '起点经度',
  `start_point_latitude` decimal(10,2) DEFAULT NULL COMMENT '起点维度',
  `start_point_geo` varchar(2000) DEFAULT NULL COMMENT '起点GEO编码',
  `destination` varchar(2000) DEFAULT NULL COMMENT '终点',
  `destination_longitude` decimal(10,2) DEFAULT NULL COMMENT '终点经度',
  `destination_latitude` decimal(10,2) DEFAULT NULL COMMENT '终点维度',
  `destination_geo` varchar(2000) DEFAULT NULL COMMENT '终点GEO编码',
  `by_ways` text COMMENT '途经地方',
  `schedule` smallint(2) DEFAULT NULL COMMENT '车程安排  0 单程 1 往返',
  `departure_time` datetime DEFAULT NULL COMMENT '出发时间',
  `back_date` datetime DEFAULT NULL COMMENT '返程日期',
  `back_time` datetime DEFAULT NULL COMMENT '返程时分（精确到分钟）',
  `passenger_num` int(3) DEFAULT NULL COMMENT '乘客人数',
  `car_type` varchar(20) DEFAULT NULL COMMENT '车辆类型  1 轿车 2 SUV  3 出租车',
  `plate_number_prefix` varchar(10) DEFAULT NULL COMMENT '车牌号前缀',
  `plate_number` varchar(30) DEFAULT NULL COMMENT '车牌号',
  `car_color` varchar(20) DEFAULT NULL COMMENT '车辆颜色 1 白色 2 黑色 3 红色  4 银色  5 灰色  6蓝色 7 其他',
  `car_brand` varchar(50) DEFAULT NULL COMMENT '品牌',
  `status` smallint(2) DEFAULT '0' COMMENT '拼车信息状态 0 发布中  1 完成  2 取消  3 过期',
  `cancel_reason` varchar(2000) DEFAULT NULL COMMENT '取消原因',
  `bake` text COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `data_status` smallint(2) DEFAULT '0' COMMENT '数据状态 0  正常  1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='拼车发布信息';

-- ----------------------------
-- Records of carpool_publish
-- ----------------------------
BEGIN;
INSERT INTO `carpool_publish` VALUES (1, 1, 0, 0, '18107111111', NULL, '北京航空航天大学', 116.35, 39.98, 'wx4e', '上地[地铁站]', 116.32, 40.03, 'wx4e', '', NULL, '2022-01-15 00:00:00', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, 1, NULL, '不带东西', '2022-01-14 20:19:37', '2022-01-14 20:19:37', 0);
INSERT INTO `carpool_publish` VALUES (2, 1, 1, 0, '18107111111', 100.00, '海淀区人民政府', 116.30, 39.96, 'wx4e', '上地[地铁站]', 116.32, 40.03, 'wx4e', '中关村', NULL, '2022-01-20 20:59:00', NULL, NULL, 6, 'SUV', '京', '12345', '黑色', '奔驰', 0, NULL, '', '2022-01-14 20:44:56', '2022-01-14 20:44:56', 0);
INSERT INTO `carpool_publish` VALUES (3, 1, 1, 0, '18107111111', 50.00, '昌平区北京市昌平区城北中心六街小学', 116.23, 40.22, 'wx4s', '上地[地铁站]', 116.32, 40.03, 'wx4e', '平西府', NULL, '2022-01-16 21:38:00', NULL, NULL, 1, '轿车', '京A', '12345', '黑', '宝马6X', 0, NULL, '', '2022-01-16 21:24:05', '2022-01-16 21:24:05', 0);
COMMIT;

-- ----------------------------
-- Table structure for carpool_user
-- ----------------------------
DROP TABLE IF EXISTS `carpool_user`;
CREATE TABLE `carpool_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `wx_openid` varchar(200) DEFAULT NULL,
  `user_name` varchar(200) DEFAULT NULL COMMENT '用户姓名',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(22) DEFAULT NULL COMMENT '手机号码',
  `sex` smallint(2) DEFAULT NULL COMMENT '性别 0 男 1 女',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `city` varchar(50) DEFAULT NULL COMMENT '地市',
  `county` varchar(50) DEFAULT NULL COMMENT '区',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `data_status` smallint(2) DEFAULT '0' COMMENT '数据状态 0  正常  1 删除',
  `is_real_name` smallint(2) DEFAULT '0' COMMENT '是否实名 0 否 1 是',
  `is_auth` smallint(2) DEFAULT '0' COMMENT '是否认证 0 否 1 是',
  `id_card_face` varchar(255) DEFAULT NULL COMMENT '身份证正面',
  `id_card_back` varchar(255) DEFAULT NULL COMMENT '身份证背面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='拼车用户\n';

-- ----------------------------
-- Records of carpool_user
-- ----------------------------
BEGIN;
INSERT INTO `carpool_user` VALUES (1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', '宋先生', '@微语人生', '18107111111', 0, 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKzxCYCic7qRic3I2j4Tia6nuBEMpBaLk9UNZtz4yDWtWfBXRxYPHUWkGt7zkTSAO0nGJyZSwMxibtiaGw/132', '北京', '北京', '昌平区', '2022-01-12 20:51:18', '2022-01-16 19:39:12', 0, 1, 1, 'https://e-carpool.oss-cn-beijing.aliyuncs.com/banner/b2.jpg', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/banner/b2.jpg');
COMMIT;

-- ----------------------------
-- Table structure for carpool_user_formid
-- ----------------------------
DROP TABLE IF EXISTS `carpool_user_formid`;
CREATE TABLE `carpool_user_formid` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `wx_openid` varchar(200) DEFAULT NULL,
  `form_id` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COMMENT='拼车用户，模板消息标识\n';

-- ----------------------------
-- Records of carpool_user_formid
-- ----------------------------
BEGIN;
INSERT INTO `carpool_user_formid` VALUES (1, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is a mock one', '2022-01-13 14:36:59');
INSERT INTO `carpool_user_formid` VALUES (2, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is a mock one', '2022-01-13 14:37:05');
INSERT INTO `carpool_user_formid` VALUES (3, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is a mock one', '2022-01-13 14:46:39');
INSERT INTO `carpool_user_formid` VALUES (4, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is a mock one', '2022-01-13 14:47:02');
INSERT INTO `carpool_user_formid` VALUES (5, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is a mock one', '2022-01-13 16:46:52');
INSERT INTO `carpool_user_formid` VALUES (6, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is a mock one', '2022-01-14 19:51:33');
INSERT INTO `carpool_user_formid` VALUES (7, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:15:17');
INSERT INTO `carpool_user_formid` VALUES (8, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:19:34');
INSERT INTO `carpool_user_formid` VALUES (9, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:20:12');
INSERT INTO `carpool_user_formid` VALUES (10, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:20:14');
INSERT INTO `carpool_user_formid` VALUES (11, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:20:42');
INSERT INTO `carpool_user_formid` VALUES (12, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:21:40');
INSERT INTO `carpool_user_formid` VALUES (13, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:21:46');
INSERT INTO `carpool_user_formid` VALUES (14, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:24:11');
INSERT INTO `carpool_user_formid` VALUES (15, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:31:51');
INSERT INTO `carpool_user_formid` VALUES (16, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:37:36');
INSERT INTO `carpool_user_formid` VALUES (17, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:38:28');
INSERT INTO `carpool_user_formid` VALUES (18, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:39:02');
INSERT INTO `carpool_user_formid` VALUES (19, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:39:08');
INSERT INTO `carpool_user_formid` VALUES (20, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:39:46');
INSERT INTO `carpool_user_formid` VALUES (21, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:40:18');
INSERT INTO `carpool_user_formid` VALUES (22, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:42:42');
INSERT INTO `carpool_user_formid` VALUES (23, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:42:55');
INSERT INTO `carpool_user_formid` VALUES (24, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:44:16');
INSERT INTO `carpool_user_formid` VALUES (25, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:44:45');
INSERT INTO `carpool_user_formid` VALUES (26, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:44:45');
INSERT INTO `carpool_user_formid` VALUES (27, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:44:45');
INSERT INTO `carpool_user_formid` VALUES (28, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:44:45');
INSERT INTO `carpool_user_formid` VALUES (29, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:44:46');
INSERT INTO `carpool_user_formid` VALUES (30, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:44:55');
INSERT INTO `carpool_user_formid` VALUES (31, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-14 20:45:01');
INSERT INTO `carpool_user_formid` VALUES (32, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 17:39:42');
INSERT INTO `carpool_user_formid` VALUES (33, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 17:52:12');
INSERT INTO `carpool_user_formid` VALUES (34, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 17:52:20');
INSERT INTO `carpool_user_formid` VALUES (35, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:07:15');
INSERT INTO `carpool_user_formid` VALUES (36, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:07:53');
INSERT INTO `carpool_user_formid` VALUES (37, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:09:41');
INSERT INTO `carpool_user_formid` VALUES (38, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:15:34');
INSERT INTO `carpool_user_formid` VALUES (39, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:15:49');
INSERT INTO `carpool_user_formid` VALUES (40, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:16:27');
INSERT INTO `carpool_user_formid` VALUES (41, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:19:08');
INSERT INTO `carpool_user_formid` VALUES (42, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:20:48');
INSERT INTO `carpool_user_formid` VALUES (43, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:21:05');
INSERT INTO `carpool_user_formid` VALUES (44, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:22:20');
INSERT INTO `carpool_user_formid` VALUES (45, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:22:22');
INSERT INTO `carpool_user_formid` VALUES (46, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:22:32');
INSERT INTO `carpool_user_formid` VALUES (47, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:22:44');
INSERT INTO `carpool_user_formid` VALUES (48, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:23:01');
INSERT INTO `carpool_user_formid` VALUES (49, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:24:04');
INSERT INTO `carpool_user_formid` VALUES (50, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:24:57');
INSERT INTO `carpool_user_formid` VALUES (51, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:28:08');
INSERT INTO `carpool_user_formid` VALUES (52, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:28:49');
INSERT INTO `carpool_user_formid` VALUES (53, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:29:36');
INSERT INTO `carpool_user_formid` VALUES (54, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:34:11');
INSERT INTO `carpool_user_formid` VALUES (55, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:42:02');
INSERT INTO `carpool_user_formid` VALUES (56, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:42:23');
INSERT INTO `carpool_user_formid` VALUES (57, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:43:59');
INSERT INTO `carpool_user_formid` VALUES (58, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:44:14');
INSERT INTO `carpool_user_formid` VALUES (59, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:44:16');
INSERT INTO `carpool_user_formid` VALUES (60, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:47:55');
INSERT INTO `carpool_user_formid` VALUES (61, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:47:57');
INSERT INTO `carpool_user_formid` VALUES (62, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:50:52');
INSERT INTO `carpool_user_formid` VALUES (63, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:51:18');
INSERT INTO `carpool_user_formid` VALUES (64, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:56:40');
INSERT INTO `carpool_user_formid` VALUES (65, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:56:47');
INSERT INTO `carpool_user_formid` VALUES (66, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:56:59');
INSERT INTO `carpool_user_formid` VALUES (67, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:57:57');
INSERT INTO `carpool_user_formid` VALUES (68, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 18:58:41');
INSERT INTO `carpool_user_formid` VALUES (69, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:15:47');
INSERT INTO `carpool_user_formid` VALUES (70, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:15:50');
INSERT INTO `carpool_user_formid` VALUES (71, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:16:12');
INSERT INTO `carpool_user_formid` VALUES (72, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:16:15');
INSERT INTO `carpool_user_formid` VALUES (73, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:16:34');
INSERT INTO `carpool_user_formid` VALUES (74, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:16:37');
INSERT INTO `carpool_user_formid` VALUES (75, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:17:30');
INSERT INTO `carpool_user_formid` VALUES (76, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:17:46');
INSERT INTO `carpool_user_formid` VALUES (77, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:20:42');
INSERT INTO `carpool_user_formid` VALUES (78, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:20:44');
INSERT INTO `carpool_user_formid` VALUES (79, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:20:49');
INSERT INTO `carpool_user_formid` VALUES (80, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:20:54');
INSERT INTO `carpool_user_formid` VALUES (81, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:21:06');
INSERT INTO `carpool_user_formid` VALUES (82, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:21:50');
INSERT INTO `carpool_user_formid` VALUES (83, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:27:50');
INSERT INTO `carpool_user_formid` VALUES (84, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:30:38');
INSERT INTO `carpool_user_formid` VALUES (85, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:31:23');
INSERT INTO `carpool_user_formid` VALUES (86, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:31:32');
INSERT INTO `carpool_user_formid` VALUES (87, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:33:43');
INSERT INTO `carpool_user_formid` VALUES (88, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:34:37');
INSERT INTO `carpool_user_formid` VALUES (89, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:34:47');
INSERT INTO `carpool_user_formid` VALUES (90, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:34:56');
INSERT INTO `carpool_user_formid` VALUES (91, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:35:00');
INSERT INTO `carpool_user_formid` VALUES (92, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:35:09');
INSERT INTO `carpool_user_formid` VALUES (93, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:39:24');
INSERT INTO `carpool_user_formid` VALUES (94, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:39:55');
INSERT INTO `carpool_user_formid` VALUES (95, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:40:01');
INSERT INTO `carpool_user_formid` VALUES (96, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:40:03');
INSERT INTO `carpool_user_formid` VALUES (97, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:40:04');
INSERT INTO `carpool_user_formid` VALUES (98, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:40:07');
INSERT INTO `carpool_user_formid` VALUES (99, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:40:09');
INSERT INTO `carpool_user_formid` VALUES (100, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:40:10');
INSERT INTO `carpool_user_formid` VALUES (101, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:40:14');
INSERT INTO `carpool_user_formid` VALUES (102, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 19:40:15');
INSERT INTO `carpool_user_formid` VALUES (103, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 21:24:04');
INSERT INTO `carpool_user_formid` VALUES (104, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 21:24:14');
INSERT INTO `carpool_user_formid` VALUES (105, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 21:24:23');
INSERT INTO `carpool_user_formid` VALUES (106, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 21:24:45');
INSERT INTO `carpool_user_formid` VALUES (107, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 21:26:06');
INSERT INTO `carpool_user_formid` VALUES (108, 1, 'o3WTN4lyhTdwUn41jdMTJ5ELyRLU', 'the formId is no longer available in develop or trial version of this mini program', '2022-01-16 21:26:23');
COMMIT;

-- ----------------------------
-- Table structure for sc_comment
-- ----------------------------
DROP TABLE IF EXISTS `sc_comment`;
CREATE TABLE `sc_comment` (
  `id` int(22) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `info_id` int(22) DEFAULT NULL COMMENT '信息表ID',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `name` varchar(100) DEFAULT NULL COMMENT '微信昵称',
  `content` text COMMENT '文本内容',
  `agree_num` int(22) DEFAULT '0' COMMENT '点赞数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `data_status` smallint(2) DEFAULT '0' COMMENT '数据状态 0 正常 1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='评论\n';

-- ----------------------------
-- Records of sc_comment
-- ----------------------------
BEGIN;
INSERT INTO `sc_comment` VALUES (1, 1, 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKzxCYCic7qRic3I2j4Tia6nuBEMpBaLk9UNZtz4yDWtWfBXRxYPHUWkGt7zkTSAO0nGJyZSwMxibtiaGw/132', '@微语人生', '过去吧', 1, '2022-01-16 19:15:37', 0);
COMMIT;

-- ----------------------------
-- Table structure for sc_info
-- ----------------------------
DROP TABLE IF EXISTS `sc_info`;
CREATE TABLE `sc_info` (
  `id` int(22) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `name` varchar(200) DEFAULT NULL COMMENT '微信昵称',
  `type` smallint(2) DEFAULT NULL COMMENT '信息类型 1->全部;41->视频;10->图片;29->文本;31->声音',
  `content` text COMMENT '文本内容',
  `img_url` text COMMENT '图片路径，最多支持九张',
  `video_url` varchar(2000) DEFAULT NULL COMMENT '视频路径',
  `width` double(10,2) DEFAULT NULL COMMENT '资源宽度',
  `height` double(10,2) DEFAULT NULL COMMENT '资源高度',
  `voice_url` varchar(2000) DEFAULT NULL COMMENT '音频路径',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `bimg_url` varchar(255) DEFAULT NULL COMMENT '音频背景图',
  `agree_num` int(22) DEFAULT '0' COMMENT '点赞数',
  `diss_num` int(22) DEFAULT '0' COMMENT '踩数',
  `repost_num` int(22) DEFAULT '0' COMMENT '转发数',
  `comment_num` int(22) DEFAULT '0' COMMENT '评论数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `data_status` smallint(2) DEFAULT '0' COMMENT '数据状态 0 正常 1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='信息表\n';

-- ----------------------------
-- Records of sc_info
-- ----------------------------
BEGIN;
INSERT INTO `sc_info` VALUES (1, 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKzxCYCic7qRic3I2j4Tia6nuBEMpBaLk9UNZtz4yDWtWfBXRxYPHUWkGt7zkTSAO0nGJyZSwMxibtiaGw/132', '@微语人生', 29, '回家过年，疫情赶紧过去吧', '', '', 340.00, 340.00, '', '易拼车', '', 7, 4, 1, 1, '2022-01-16 19:15:23', 0);
INSERT INTO `sc_info` VALUES (2, 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKzxCYCic7qRic3I2j4Tia6nuBEMpBaLk9UNZtz4yDWtWfBXRxYPHUWkGt7zkTSAO0nGJyZSwMxibtiaGw/132', '@微语人生', 29, '回家过年，疫情赶紧过去吧', '', '', 340.00, 340.00, '', '易拼车', '', 0, 0, 0, 0, '2022-01-16 19:16:06', 0);
INSERT INTO `sc_info` VALUES (3, 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKzxCYCic7qRic3I2j4Tia6nuBEMpBaLk9UNZtz4yDWtWfBXRxYPHUWkGt7zkTSAO0nGJyZSwMxibtiaGw/132', '@微语人生', 10, '拼车回家，一路有你', 'https://e-carpool.oss-cn-beijing.aliyuncs.com/banner/b1.png,https://e-carpool.oss-cn-beijing.aliyuncs.com/banner/b1.png,https://e-carpool.oss-cn-beijing.aliyuncs.com/banner/b1.png', '', 340.00, 340.00, '', '易拼车', '', 0, 0, 0, 0, '2022-01-16 19:32:39', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统配置信息表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES (2, 'SMS_CONFIG_KEY', '{\"domain\":\"http://web.cr6868.com/asmx/smsservice.aspx?\",\"name\":\"\",\"pwd\":\"\",\"sign\":\"\",\"type\":1}', 0, '短信配置');
INSERT INTO `sys_config` VALUES (3, 'CLOUD_STORAGE_CONFIG_KEY', '{\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', 0, '云存储配置信息');
COMMIT;

-- ----------------------------
-- Table structure for sys_macro
-- ----------------------------
DROP TABLE IF EXISTS `sys_macro`;
CREATE TABLE `sys_macro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(255) DEFAULT NULL COMMENT '父级id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `value` varchar(2000) DEFAULT NULL COMMENT '值',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，0：隐藏   1：显示',
  `type` tinyint(20) DEFAULT NULL COMMENT '类型,0:目录，1:参数配置',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `gmt_create` date DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` date DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=gbk ROW_FORMAT=DYNAMIC COMMENT='通用字典表';

-- ----------------------------
-- Records of sys_macro
-- ----------------------------
BEGIN;
INSERT INTO `sys_macro` VALUES (5, NULL, '单位', 'goodsUnit', 1, 0, NULL, NULL, '2017-08-30', NULL);
INSERT INTO `sys_macro` VALUES (6, 5, '个', '个', 1, 1, NULL, NULL, '2017-08-30', NULL);
INSERT INTO `sys_macro` VALUES (7, 5, '只', '只', 1, 1, 2, NULL, '2017-10-06', '2017-10-06');
COMMIT;

-- ----------------------------
-- Table structure for tb_token
-- ----------------------------
DROP TABLE IF EXISTS `tb_token`;
CREATE TABLE `tb_token` (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户Token';

-- ----------------------------
-- Records of tb_token
-- ----------------------------
BEGIN;
INSERT INTO `tb_token` VALUES (1, '9ksmdeed221jmarbndkxqwzxcjidrfow', '2018-02-20 23:52:39', '2018-02-20 11:52:39');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
