/*
SQLyog Ultimate v11.13 (64 bit)
MySQL - 5.1.68-community : Database - zp_quartz
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zp_quartz` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zp_quartz`;

/*Table structure for table `qrtz_job_details` */

DROP TABLE IF EXISTS `qrtz_job_details`;

CREATE TABLE `qrtz_job_details` (
  `job_id` int(10) NOT NULL AUTO_INCREMENT,
  `job_class` varchar(255) DEFAULT NULL,
  `job_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;


/*Table structure for table `qrtz_log_error` */

DROP TABLE IF EXISTS `qrtz_log_error`;

CREATE TABLE `qrtz_log_error` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `jobname` varchar(500) DEFAULT NULL,
  `error` varchar(500) DEFAULT NULL,
  `creattime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `qrtz_log_error` */

/*Table structure for table `qrtz_triggers` */

DROP TABLE IF EXISTS `qrtz_triggers`;

CREATE TABLE `qrtz_triggers` (
  `trigger_id` int(10) NOT NULL AUTO_INCREMENT,
  `trigger_name` varchar(255) DEFAULT NULL,
  `trigger_group` varchar(255) DEFAULT NULL,
  `job_id` int(10) DEFAULT NULL,
  `next_fire_time` int(10) DEFAULT NULL COMMENT '执行开始时间',
  `trigger_state` char(1) DEFAULT NULL COMMENT '0:停止，1:启用',
  `trigger_cron` varchar(255) DEFAULT NULL COMMENT '设定时间',
  PRIMARY KEY (`trigger_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;