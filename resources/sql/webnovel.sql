
CREATE DATABASE webnovel;
USE webnovel;

/**
 * 系统用户表
 */
CREATE TABLE wn_user (
	`id` bigint NOT NULL auto_increment COMMENT '用户名',
	`name` varchar(32) NOT NULL COMMENT '用户名',
	`email` varchar(64) DEFAULT NULL COMMENT '邮件地址',
	`phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
	`alias` varchar(64) DEFAULT NULL COMMENT '别名',
	`alias_used` int DEFAULT NULL COMMENT '是否启用别名：0、不启用 1、启用',
	`status` int DEFAULT NULL COMMENT '状态：1,正常 2, 锁定(1年未登录) 3, 冻结(因为账户登录异常等原因) 4, 注销',
	`birthday` datetime DEFAULT NULL COMMENT '生日',
	`sex` int DEFAULT NULL COMMENT '性别：0、女 1、男',
	`avatar_url` varchar(64) DEFAULT NULL COMMENT '头像地址',
	`remark` varchar(250) DEFAULT NULL COMMENT '备注',
	`last_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
	`alg` varchar(20) NOT NULL COMMENT '密码的哈希算法',
	`salt` varchar(40) NOT NULL COMMENT '盐',
	`pwd` varchar(128) NOT NULL COMMENT '用户密码',
	`reg_time` datetime DEFAULT NULL COMMENT '注册时间，精确到日',
	PRIMARY KEY(`id`),
	UNIQUE KEY `uq_user_name` (`name`),
	UNIQUE KEY `uq_user_email` (`email`),
	UNIQUE KEY `uq_user_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



/**
 * 书籍表。存储所有的书籍信息，并对书籍进行分类。
 */
CREATE TABLE wn_book (
	`id` varchar(32) NOT NULL COMMENT '主键，唯一标识符',
	`book_name` varchar(120) DEFAULT NULL COMMENT '书籍名称',
	`desc` varchar(2000) DEFAULT NULL COMMENT '书籍的简要描述',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间(开始写作时间)',
	`update_time` datetime DEFAULT NULL COMMENT '更新时间(最新章节更新时间)',
	`status` int DEFAULT NULL COMMENT '状态：1、写作中 2、已完结',
	`type_by_time` int DEFAULT NULL COMMENT '类型细分(古典文学、网络文学、近代文学....)',
	`type_by_area` int DEFAULT NULL COMMENT '类型细分，按地域(中国、英国、美国....)',
	`type_by_field` int DEFAULT NULL COMMENT '类型细分，按领域(玄幻、武侠、科幻、校园....)',
	`words` int DEFAULT NULL COMMENT '字数',
	`click_times` int DEFAULT NULL COMMENT '点击次数',
	`comment_times` int DEFAULT NULL COMMENT '评论次数',
	`author_id` varchar(32) DEFAULT NULL COMMENT '外键，作者id',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 书籍--分卷表。存储所有的书籍分卷信息。
 */
CREATE TABLE wn_volume (
	`id` varchar(32) NOT NULL COMMENT '主键，唯一标识符',
	`volume_name` varchar(120) DEFAULT NULL COMMENT '卷名称',
	`charged` int DEFAULT NULL COMMENT '是否收费',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间(开始写作时间)',
	`update_time` datetime DEFAULT NULL COMMENT '更新时间(最新章节更新时间)',
	`words` int DEFAULT NULL COMMENT '字数',
	`book_id` varchar(32) DEFAULT NULL COMMENT '外键，书籍id',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 书籍章节表。存储书籍的章节信息。该表的信息量会比较大，需要考虑存取的效率问题。
 */
CREATE TABLE wn_chapter (
	`id` varchar(32) NOT NULL COMMENT '主键，唯一标识符',
	`subject` varchar(64) DEFAULT NULL COMMENT '章节标题',
	`content` text DEFAULT NULL COMMENT '章节内容',
	`serial_no` int DEFAULT 0 COMMENT '章节序列号', 
	`charged` int DEFAULT NULL COMMENT '是否收费，默认不收费',
	`write_time` datetime DEFAULT NULL COMMENT '写作时间',
	`input_time` datetime DEFAULT NULL COMMENT '录入时间',
	`volume_id` varchar(32) DEFAULT NULL COMMENT '外键，分卷id',
	`book_id` varchar(32) DEFAULT NULL COMMENT '外键，书籍id',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 书籍的评论表。存储用户对书籍的评论信息。
 */
CREATE TABLE wn_comment (
	`id` varchar(32) NOT NULL COMMENT '主键，唯一标识符',
	`content` text DEFAULT NULL COMMENT '评论内容',
	`status` int COMMENT '帖子状态。0、关闭，1、审核，2、打开',
	`create_time` datetime DEFAULT NULL COMMENT '评论时间',
	`refer_id` varchar(32) COMMENT '团员对团员的回复',
	`book_id` varchar(32) DEFAULT NULL COMMENT '外键，书籍id',
	`user_id` varchar(32) DEFAULT NULL COMMENT '外键，用户id',
	`user_alias` varchar(64) COMMENT '用户别名',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 书架表。存储书架信息。
 */
CREATE TABLE wn_bookshelf (
	`id` varchar(32) NOT NULL COMMENT '主键，唯一标识符',
	`shelf_name` varchar(64) DEFAULT NULL COMMENT '书架名称',
	`user_id` varchar(32) DEFAULT NULL COMMENT '外键，用户id',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 书架书籍表。存储书架上的图书信息。
 */
CREATE TABLE wn_bookshelf_books (
	`id` varchar(32) NOT NULL COMMENT '主键，唯一标识符',
	`book_id` varchar(64) DEFAULT NULL COMMENT '外键，书籍id',
	`book_name` varchar(64) COMMENT '书籍名称',
	`shelf_id` varchar(64) DEFAULT NULL COMMENT '外键，书架id',
	`shelf_name` varchar(64) COMMENT '书架名称',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 论坛主题表。
 */
CREATE TABLE wn_forum_subject (
	`id` varchar(32) NOT NULL COMMENT '主键，唯一标识符',
	`subject` varchar(120) COMMENT '帖子标题',
	`type` int COMMENT '帖子类型',
	`content` text COMMENT '帖子内容',
	`status` int COMMENT '帖子状态。0、关闭，1、审核，2、打开',
	`create_time` datetime COMMENT '发布时间',
	`update_time` datetime COMMENT '最近更新时间',
	`read_times` int COMMENT '阅读次数',
	`reply_times` int COMMENT '回复次数',
	`user_id` varchar(32) DEFAULT NULL COMMENT '外键，用户id',
	`user_alias` varchar(64) COMMENT '用户别名',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 论坛表。
 */
CREATE TABLE wn_forum (
	`id` varchar(32) NOT NULL COMMENT '主键，唯一标识符',
	`content` text COMMENT '帖子内容',
	`status` int COMMENT '帖子状态。0、关闭，1、审核，2、打开',
	`create_time` datetime COMMENT '帖子的发布时间',
	`subject_id` varchar(32) COMMENT '外键，帖子id。用户回复帖子，持有的主帖id。',
	`refer` text COMMENT '团员对团员的回复',
	`user_id` varchar(32) DEFAULT NULL COMMENT '外键，用户id',
	`user_alias` varchar(64) COMMENT '用户别名',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 备忘表。
 */
CREATE TABLE WN_MEMO (
	`id` varchar(32) NOT NULL COMMENT '主键，唯一标识符',
	`title` varchar(120) COMMENT '备忘标题',
	`content` text COMMENT '备忘内容',
	`create_time` datetime COMMENT '创建时间',
	`update_time` datetime COMMENT '最后修改时间',
	`user_id` varchar(32) COMMENT '外键，用户id',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 用户反馈表。
 */
CREATE TABLE wn_feedback (
	`id` varchar(32) NOT NULL COMMENT '主键，唯一标识符',
	`title` varchar(120) COMMENT '备忘标题',
	`advice` text COMMENT '备忘内容',
	`status` int COMMENT '状态',
	`anonymity` int COMMENT '匿名',
	`create_time` datetime COMMENT '创建时间',
	`user_id` varchar(32) COMMENT '外键，用户id',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;








