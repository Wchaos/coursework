/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : demoshop

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-02-26 11:38:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contentId` int(11) NOT NULL,
  `buyerId` int(11) NOT NULL COMMENT '买家的用户ID',
  `count` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `c_cid_uid` (`contentId`,`buyerId`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('64', '22', '1', '8');

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(80) NOT NULL,
  `cAbstract` varchar(140) NOT NULL,
  `text` varchar(1000) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `imgPath` varchar(255) CHARACTER SET latin1 NOT NULL,
  `sellerId` int(11) NOT NULL,
  `beDeleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除字段，1表示被删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES ('17', '测试1 编辑1', '测试1 编辑1', '测试1 编辑1', '15.00', 'http://image.biaobaiju.com/uploads/20180803/22/1533306030-xbmaHzIVwL.jpg', '2', '0');
INSERT INTO `content` VALUES ('18', '测试2', '测试2', '测试2 第一次编辑', '32.00', 'http://www.pptbz.com/pptpic/UploadFiles_6909/201203/2012031220134655.jpg', '2', '0');
INSERT INTO `content` VALUES ('20', '测试4', '测试4', '测试4', '120.00', 'http://image.whhost.net/uploads/20181221/13/1545369346-nftFRkAKHD.jpg', '2', '0');
INSERT INTO `content` VALUES ('22', '测试5', '测试5摘要', '测试5正文', '150000.12', 'http://www.pptbz.com/pptpic/UploadFiles_6909/201306/2013062320262198.jpg', '2', '0');
INSERT INTO `content` VALUES ('25', '测试8', 'fsdasf', '范德萨发给大', '67.00', 'https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/image/h%3D300/sign=e8ce788890510fb367197197e933c893/b999a9014c086e061396097a0c087bf40ad1cbc0.jpg', '2', '0');
INSERT INTO `content` VALUES ('27', '本地上传测试+编辑1', '大噶山豆根十大', '反盾的', '170.00', 'img/ffa25053-8396-4ba0-a32e-91779ee0b2c8.png', '2', '0');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contentId` int(11) NOT NULL,
  `buyerId` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `orderDate` datetime NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('9', '17', '1', '3', '2018-03-13 18:13:17', '15.00');
INSERT INTO `orders` VALUES ('10', '18', '1', '1', '2018-03-13 18:14:56', '323.00');
INSERT INTO `orders` VALUES ('11', '17', '1', '3', '2018-06-17 18:13:17', '20.00');
INSERT INTO `orders` VALUES ('12', '17', '1', '10', '2019-02-21 11:15:31', '15.00');
INSERT INTO `orders` VALUES ('17', '17', '1', '10', '2019-02-21 12:48:52', '15.00');
INSERT INTO `orders` VALUES ('18', '20', '1', '1', '2019-02-22 21:01:17', '12.00');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `userType` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'buyer', '37254660e226ea65ce6f1efd54233424', '0');
INSERT INTO `user` VALUES ('2', 'seller', '981c57a5cfb0f868e064904b8745766f', '1');
