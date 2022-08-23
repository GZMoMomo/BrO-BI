/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.29 : Database - bisystem
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bisystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `bisystem`;

/*Table structure for table `db_list` */

DROP TABLE IF EXISTS `db_list`;

CREATE TABLE `db_list` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `host` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据库地址',
  `port` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据库端口',
  `username` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据库用户名',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据库密码',
  `database` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据库名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

/*Data for the table `db_list` */

insert  into `db_list`(`id`,`host`,`port`,`username`,`password`,`database`) values (1,'127.0.0.1','3306','root','root','test'),(2,'127.0.0.1','3306','root','root','spring-boot-demo');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.29 : Database - test
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `test`;

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(11) DEFAULT NULL,
  `age` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `test` */

insert  into `test`(`id`,`name`,`age`) values (1,'mm','12'),(2,'hh','13'),(3,'oo','14'),(4,'uu','84');

/*Table structure for table `本部门诊工作报表` */

DROP TABLE IF EXISTS `本部门诊工作报表`;

CREATE TABLE `本部门诊工作报表` (
  `报表日期` varchar(255) DEFAULT NULL,
  `统一科号` varchar(255) DEFAULT NULL,
  `科室名称` varchar(255) DEFAULT NULL,
  `总诊疗人次` double DEFAULT NULL,
  `门诊诊疗数` double DEFAULT NULL,
  `专家门诊` double DEFAULT NULL,
  `急诊诊疗数` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `本部门诊工作报表` */

insert  into `本部门诊工作报表`(`报表日期`,`统一科号`,`科室名称`,`总诊疗人次`,`门诊诊疗数`,`专家门诊`,`急诊诊疗数`) values ('2021-01-02','TMZNK*1','*内科',43468,38729,13022,4516),('2021-01-03','TMZWK*1','*外科',44304,41708,23219,2596),('2021-01-04','MFCK','*妇产科',37841,37172,18487,669),('2021-01-05','TMZFK*1','**妇科',12430,12163,3484,267),('2021-01-06','TMZJSK*1','**计划生育科',1161,1161,0,0),('2021-01-07','TMZCK*1','**产科',6163,5761,2267,402),('2021-01-08','TMZSZYX*1','**生殖医学中心',18087,18087,12736,0),('2021-01-09','TMZEK*1','*儿科',8183,6095,3816,2088),('2021-01-10','TMZSJK*1','*神经科',15377,14474,10021,903),('2021-01-11','TMZXXG*1','*心血管医学部',16631,16631,10450,0),('2021-01-12','TMZEBHK*1','*耳鼻咽喉科',13163,12440,7891,723),('2021-01-13','TMZYK*1','*眼科',4058,3901,2904,157),('2021-01-14','TMZKQK*1','*口腔科',4412,4281,1168,131),('2021-01-15','TMZZYK*1','*中医科',11288,11288,5388,0),('2021-01-16','TMZZGK*1','*正骨科',3173,3173,1910,0),('2021-01-17','TMZZJK*1','*针灸科',1041,1041,439,0),('2021-01-18','TMZPFK*1','*皮肤科',8367,8117,4212,250),('2021-01-19','TMZKFK*1','*康复医学科',1487,1487,595,0),('2021-01-20','MJZK','*急诊科',250,100,100,250),('2021-01-21','TMZJZ*1','**急诊室',100,250,250,100),('2021-01-22','TMZGCQ*1','**急诊观察区',100,100,250,100),('2021-01-23','MWZZS','**危重症室',250,250,100,100),('2021-01-24','MYXYXK','*医学影象科',2799,2799,2395,0),('2021-01-25','TMZFSK*1','**放射诊断科',204,204,42,0),('2021-01-26','MFSJRK','**放射介入科',379,379,242,0),('2021-01-27','MZLJRK','**肿瘤介入科',389,389,287,0),('2021-01-28','TMZHYX*1','**核医学科',1827,1827,1824,0),('2021-01-29','MZLZX','*肿瘤中心',2123,2123,1337,0),('2021-01-30','MZLFLK','**放射治疗科',1498,1498,721,0),('2021-01-31','MZLK','**肿瘤科',625,625,616,0),('2021-02-01','TMZCSBK*1','*超声医学科',705,705,355,0),('2021-02-02','TMZBLK*1','*病理科',1140,1140,20,0),('2021-02-03','M保健体检中心','*保健体检中心',14213,14042,337,171),('2021-02-04','TMZBJK*1','**保健科',7568,7568,0,0),('2021-02-05','TMZBXMZ*1','**北校区门诊部',2884,2884,0,0),('2021-02-06','MDQ','**东校区门诊部',2032,1861,0,171),('2021-02-07','TMZTJ*1','**体检中心',1729,1729,337,0),('2021-02-08','TMZTZK*1','*特需医疗与健康管理中心',5955,5955,5671,0),('2021-02-09','TMZYYK*1','*营养科',397,397,96,0),('2021-02-10','TMZGYYC*1','*高压氧科',102,102,0,0),('2021-02-11','TMZLJS*1','*老教授',6,6,0,0),('2021-02-12','TMZFR*1','*发热门诊',1723,1723,0,0),('2021-02-13','TMZQTK*1','*其他科',584,584,0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
