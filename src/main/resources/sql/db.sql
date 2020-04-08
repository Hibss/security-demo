/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.21-20-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;



CREATE TABLE `t_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `role` varchar(100) DEFAULT NULL COMMENT '角色权限',
  PRIMARY KEY (`id`),
  KEY `index_mobile` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=9460 DEFAULT CHARSET=utf8mb4;
insert into `t_user` (`id`, `password`, `mobile`, `role`) values('9268','$2a$04$sniqaPx4beQAcqvbpAqUSerRHkU5W00WH0oJOy0jc8jAT1FyEtvzW','18565842966','VIP1,VIP2');
insert into `t_user` (`id`, `password`, `mobile`, `role`) values('9269','$2a$04$sniqaPx4beQAcqvbpAqUSerRHkU5W00WH0oJOy0jc8jAT1FyEtvzW','13544909961','VIP1,VIP2,VIP3');
insert into `t_user` (`id`, `password`, `mobile`, `role`) values('9270','$2a$04$sniqaPx4beQAcqvbpAqUSerRHkU5W00WH0oJOy0jc8jAT1FyEtvzW','13393122702','VIP1');
