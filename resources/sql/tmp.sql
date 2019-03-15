/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.23-log : Database - webnovel
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`webnovel` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `webnovel`;

/*Data for the table `m_spider_rule` */

insert  into `m_spider_rule`(`id`,`name`,`book_list_url_format`,`book_list_start_page_no`,`book_list_charset`,`book_list_total_page_path`,`book_list_total_page_regular`,`book_node_path`,`book_node_name_path`,`book_node_url_path`,`book_node_author_path`,`book_node_img_path`,`book_node_introduction_path`,`book_node_last_update_time_path`,`catalog_charset`,`catalog_chapter_node_path`,`catalog_chapter_url_path`,`chapter_charset`,`chapter_node_path`,`chapter_weed`,`update_time`,`create_time`) values (1,'千千小说','https://www.qqxs.cc/lastupdate/{0}.html','1','GBK','//div[@class=\"pagelink\"]/a[@class=\"last\"]','','//div[@id=\"alistbox\"]','./div[@class=\"info\"]/div[@class=\"title\"]/h2/a','./div[@class=\"info\"]/div[@class=\"title\"]/h2/a/@href','./div[@class=\"info\"]/div[@class=\"title\"]/h2/a','./div[@class=\"pic\"]/a/img/@src','./div[@class=\"info\"]/div[@class=\"intro\"]','','GBK','//div[@id=\"list\"]/dl//dd/a','./@href','GBK','//div[@class=\"zhangjieTXT\"]','千千小说网 www.qqxs.cc,','2019-03-10 15:21:58','2019-03-03 20:50:17');

/*Data for the table `m_spider_website` */

insert  into `m_spider_website`(`id`,`name`,`url`,`state`,`rule_id`,`retrive_count`,`retrive_state`,`retrive_start_time`,`retrive_stop_time`,`retrive_fail_cause`,`update_time`,`create_time`) values (1,'言情小说吧','https://www.xs8.cn/',1,NULL,0,NULL,NULL,NULL,NULL,'2019-03-03 18:11:28','2019-03-03 18:11:28'),(2,'千千小说','https://www.qqxs.cc/',1,1,0,NULL,NULL,NULL,NULL,'2019-03-03 18:14:36','2019-03-03 18:14:36'),(3,'领域文学','https://www.lingyu.org/',1,NULL,0,NULL,NULL,NULL,NULL,'2019-03-03 18:46:54','2019-03-03 18:46:54');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
