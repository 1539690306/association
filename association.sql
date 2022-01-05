/*
 Navicat Premium Data Transfer

 Source Server         : conn
 Source Server Type    : MySQL
 Source Server Version : 50511
 Source Host           : localhost:3306
 Source Schema         : association

 Target Server Type    : MySQL
 Target Server Version : 50511
 File Encoding         : 65001

 Date: 19/06/2021 20:35:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_act
-- ----------------------------
DROP TABLE IF EXISTS `t_act`;
CREATE TABLE `t_act`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `actname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL,
  `deadline` timestamp NULL DEFAULT NULL,
  `countuser` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_act
-- ----------------------------
INSERT INTO `t_act` VALUES (8, '小乔', '游园活动', '六一”是少年儿童自己的节日,通过活动,使小学生人人自主的参与在游园活动之中,在游园中陶冶情操,在游园中获取知识,在游园中培养学生的能力,从而推进学生的全面', '2021-05-22 14:52:36', '2021-05-31 14:52:00', 0);
INSERT INTO `t_act` VALUES (9, '卢松', '荧光夜跑', '“荧光跑”在国内越来越流行，人们对于活动的参与度也越来越高，据悉，每场活动都会有上万的人参加，不分性别、不分年龄、不分国界，是真正意义上的全民活动。\r\n　　荧光跑活动主旨推崇全民健身运动，结合社会潮流，以绿色·健康·运动的宗旨号召高校学生及社会全体通过慢跑这一具有青春、活力、健康的运动方式强健身心，以提倡全面运动将健康生活传递给周围每一个人！', '2021-05-22 16:10:46', '2021-05-25 16:10:00', 0);
INSERT INTO `t_act` VALUES (11, '卢松', '英语演讲比赛', '大赛旨在以丰富的内容与严谨的形式，通过各地高校、大学外语教学研究会以及主办单位有组织、分层次的选拔，为全国大学生创造一个激励外语学习、展示综合能力、培养人才的平台，同时推动高校英语教学，创新教学方法，展现教学水平，促进教师发展。', '2021-05-22 16:37:06', '2021-05-25 16:37:00', 0);
INSERT INTO `t_act` VALUES (12, '卢松', 'PPT设计大赛', '从校园到职场，每个人的青春都必然有过PPT的陪伴，它可能是一份学业功课，一份个人展示，一份工作报告，一份项目策划……无论你是职场人，还是大学生，一份好的PPT，它可以彰显你的才华，你的能力，你独有的范儿！', '2021-05-22 16:39:02', '2021-05-22 16:38:00', 0);
INSERT INTO `t_act` VALUES (14, '卢松', '歌唱比赛', '唱歌是表达情感的极佳方式,让我们的歌声唱响青春, 唱响每一颗心。我们商协人满腔热情,决心用最质朴最真诚的方式去歌唱. 比赛也为同学们提供了一个表演的舞台,让他们尽情欢畅,唱出我们青年人的热情与朝气,唱出我们商协人的风采。', '2021-05-23 15:40:05', '2021-05-27 15:40:00', 0);
INSERT INTO `t_act` VALUES (15, '卢松', '演讲比赛', '大赛', '2021-06-17 20:11:07', '2021-06-26 20:11:00', 0);

-- ----------------------------
-- Table structure for t_actbmjl
-- ----------------------------
DROP TABLE IF EXISTS `t_actbmjl`;
CREATE TABLE `t_actbmjl`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actId` int(11) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_actbmjl
-- ----------------------------
INSERT INTO `t_actbmjl` VALUES (1, 5, '卢松', '男', '17777331934');
INSERT INTO `t_actbmjl` VALUES (3, 4, '胡一菲', '女', '56461321');
INSERT INTO `t_actbmjl` VALUES (4, 1, '胡一菲', '女', '56461321');
INSERT INTO `t_actbmjl` VALUES (5, 2, '关羽', '男', '17777331934');
INSERT INTO `t_actbmjl` VALUES (6, 5, '小乔', '女', '17777331934');
INSERT INTO `t_actbmjl` VALUES (7, 3, '小乔', '女', '17777331934');
INSERT INTO `t_actbmjl` VALUES (9, 2, '黄忠', '男', '17777331934');
INSERT INTO `t_actbmjl` VALUES (11, 7, '卢松', '男', '17777331934');
INSERT INTO `t_actbmjl` VALUES (14, 8, '小乔', '女', '17777331934');
INSERT INTO `t_actbmjl` VALUES (15, 8, '卢松', '男', '17777331934');
INSERT INTO `t_actbmjl` VALUES (19, 10, '卢松', '男', '17777331934');
INSERT INTO `t_actbmjl` VALUES (21, 11, '卢松', '男', '17777331934');
INSERT INTO `t_actbmjl` VALUES (22, 9, '卢松', '男', '17777331934');
INSERT INTO `t_actbmjl` VALUES (23, 11, '典韦', NULL, NULL);
INSERT INTO `t_actbmjl` VALUES (24, 13, '卢松', '男', '17777331934');
INSERT INTO `t_actbmjl` VALUES (25, 11, '张天志', NULL, NULL);
INSERT INTO `t_actbmjl` VALUES (26, 14, '卢松', '男', '17777331934');
INSERT INTO `t_actbmjl` VALUES (27, 8, '小乔', '女', '17777331934');
INSERT INTO `t_actbmjl` VALUES (28, 15, '卢松', '男', '17777331934');
INSERT INTO `t_actbmjl` VALUES (29, 15, '卢松', '男', '17777331934');
INSERT INTO `t_actbmjl` VALUES (30, 15, '张天志', '男', '17788145692');
INSERT INTO `t_actbmjl` VALUES (31, 15, '张天志', '男', '17788145692');

-- ----------------------------
-- Table structure for t_check
-- ----------------------------
DROP TABLE IF EXISTS `t_check`;
CREATE TABLE `t_check`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `noticeId` int(11) NULL DEFAULT NULL,
  `userId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`userId`) USING BTREE,
  CONSTRAINT `userid` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_check
-- ----------------------------
INSERT INTO `t_check` VALUES (20, 15, 1);
INSERT INTO `t_check` VALUES (33, 17, 1);
INSERT INTO `t_check` VALUES (34, 16, 1);
INSERT INTO `t_check` VALUES (35, 15, 68);
INSERT INTO `t_check` VALUES (37, 17, 2);
INSERT INTO `t_check` VALUES (38, 17, 69);
INSERT INTO `t_check` VALUES (39, 19, 1);
INSERT INTO `t_check` VALUES (41, 19, 50);
INSERT INTO `t_check` VALUES (42, 17, 50);
INSERT INTO `t_check` VALUES (43, 19, 69);

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deptname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES (1, '宣传部', '负责对外宣传', 0);
INSERT INTO `t_dept` VALUES (2, '组织部', '社团各种活动的策划和组织举办', 0);
INSERT INTO `t_dept` VALUES (3, '技术部', '为社团提供技术支持', 0);
INSERT INTO `t_dept` VALUES (12, '调研部', '主要工作是市场调查', 0);
INSERT INTO `t_dept` VALUES (13, '新闻部', '负责发布新闻', 0);
INSERT INTO `t_dept` VALUES (14, '测试部', '测试', 0);

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES (54, '石榴', '10.0', 'upload/b14a9369875f46f8a7c59a1ca84f5b3e.jpg', '社团活动奖励，好吃的石榴', '可用');
INSERT INTO `t_goods` VALUES (55, '电脑', '888.0', 'upload/c73c2440968b464388841d0d8204dd22.jpg', '打代码专用电脑，社团办公室人手一台', '已损坏');
INSERT INTO `t_goods` VALUES (62, '苹果', '20.0', 'upload/f9aa2502da234c509fa254f6d9b637e6.jpg', '活动奖励', '可用');

-- ----------------------------
-- Table structure for t_minganci
-- ----------------------------
DROP TABLE IF EXISTS `t_minganci`;
CREATE TABLE `t_minganci`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_minganci
-- ----------------------------
INSERT INTO `t_minganci` VALUES (4, '敏感词');
INSERT INTO `t_minganci` VALUES (5, '傻');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `creater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT NULL,
  `countCheck` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES (15, 'textarea禁止bai输入或编辑的方du法是标签内增加readonly 属性。\r\n\r\n具体zhi方法为：\r\n\r\n<textarea readonly=\"readonly\"> </textarea>\r\nreadonly 属性规dao定文本zhuan区shu为只读。\r\n\r\n在只读的文本区中，无法对内容进行修改，但用户可以通过 tab 键切换到该控件，选取或复制其中的内容。', '卢松', '2020-12-20 17:45:42', NULL);
INSERT INTO `t_notice` VALUES (16, '常用命令有：\r\n\r\n　　backcolor:设置文档背景颜色、bold:文本加粗、copy:复制到剪切板、createlink:给选中文本设置连接、cut:剪切、delete:删除文本、fontname:字体名称\r\n\r\n　　fontsize:字体大小、forecolor:字体换色、formatblock:用HTML标签格式化选中文本、indet:缩进、inserthorizontalrule:插入<hr>、insertimage:插入图片\r\n\r\n　　insertorderedlist:插入<ol>、insertunorderedlist:插入<ul>、insertparagraph:插入<p>、italic:文本斜体、justifycenter:文本居中、justifyleft:左对齐\r\n\r\n　　outdent:减少缩进、paste:粘贴剪切板内容、removeformat:删除样式、selectall:选中所有文本、underline:添加下划线、unlink:撤销链接。', '卢松', '2020-12-20 17:49:35', NULL);
INSERT INTO `t_notice` VALUES (17, 'thymeleaf的下拉框(select option)回显选中\r\n在开发一个足球联赛管理系统中，需要在修改页面使用thymeleaf模板引擎先从数据库中取出数据显示在页面中，然后再对要修改的数据分别进行修改。\r\n在input或者textarea的回显都没有什么问题，只要之前传了对象过来，那么就在input标签里加上例如th:value=\"*{startDate}\"即可，那么输入框里变会显示数据库中已有的数据，点击后可进行修改。\r\n但是select标签相对会特殊一点，我们需要进行一个判断，判断数据库中选中的是哪个option，然后在回显页面将它默认选中，同时点击下拉框后还可以选中修改成其它选项。\r\n因为我刚开始对thymeleaf模板引擎不是很熟悉，所以在网上找了很多方案，也一个一个试了，很多对于我这个程序并没有作用，但是这里也都列出来作为一个解决方案的参考。', '卢松', '2020-12-21 22:45:04', NULL);
INSERT INTO `t_notice` VALUES (19, '明天5月18日中午12:30在北校区致远楼103开例会，全体成员提前10分钟到场，请带上会议记录本和笔，办公室负责点名，切勿迟到。各部门互相通知，收到请回复，谢谢!\r\n\r\n', '卢松', '2021-05-23 15:37:39', 0);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roles` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '卢松', '123456', '男', '宣传部', '超级管理员', '17777331934', '1539690306@qq.com', '软件工程', '');
INSERT INTO `t_user` VALUES (2, '刘备', '123456', '男', '技术部', '管理员', '18812345678', '1637483699@qq.com', '计算机', NULL);
INSERT INTO `t_user` VALUES (17, '关羽', '123456', '男', '技术部', '管理员', '15312345678', '1539690306@qq.com', '骑马', NULL);
INSERT INTO `t_user` VALUES (21, '孙尚香', '123456', '女', '宣传部', '普通用户', '17777331934', '1539690306@qq.com', '机制专业', NULL);
INSERT INTO `t_user` VALUES (33, '张辽', '123456', '男', '宣传部', '普通用户', '1361123456', '1361234568@qq.com', '播音主持', NULL);
INSERT INTO `t_user` VALUES (34, '大乔', '123456', '女', '技术部', '普通用户', '13712345678', '1005925692@qq.com', '英语', NULL);
INSERT INTO `t_user` VALUES (35, '曹操', '123456', '男', '组织部', '管理员', '17777331934', '1539690306@qq.com', '计算机', NULL);
INSERT INTO `t_user` VALUES (47, '胡一菲', '123456', '女', '组织部', '普通用户', '17777331934', '1539690306@qq.com', '计算机', '');
INSERT INTO `t_user` VALUES (50, '小乔', '123456', '女', '调研部', '普通用户', '17777331934', '1539690306@qq.com', '英语', NULL);
INSERT INTO `t_user` VALUES (57, '周瑜', '123456', '男', '测试部', '管理员', '17777331934', '1539690306@qq.com', '计算机', NULL);
INSERT INTO `t_user` VALUES (58, '莫德凯撒', '123456', '男', '调研部', '管理员', '17777331934', '1539690306@qq.com', '打铁', NULL);
INSERT INTO `t_user` VALUES (59, '夏侯惇', '123456', '男', '宣传部', '普通用户', '17777331934', '1539690306@qq.com', 'dasd', NULL);
INSERT INTO `t_user` VALUES (68, '典韦', '1539690306', '男', '新闻部', '普通用户', '17777331934', '1539690306@qq.com', '物流管理', NULL);
INSERT INTO `t_user` VALUES (69, '张天志', '1539690306', '男', '', '普通用户', '17788145692', '1539690306@qq.com', '计算机', '');

SET FOREIGN_KEY_CHECKS = 1;
