
CREATE DATABASE webnovel default character set utf8 collate utf8_general_ci;
USE webnovel;

CREATE TABLE m_version (
	`db_version` int NOT NULL COMMENT '数据库版本',
	`app_version` varchar(32) DEFAULT NULL COMMENT '应用版本'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into m_version(db_version, app_version) values(1, '1.0');

/**
 * 系统用户表
 */
CREATE TABLE m_user (
	`id` bigint NOT NULL auto_increment COMMENT '主键',
	`name` varchar(32) NOT NULL COMMENT '用户名',
	`email` varchar(64) DEFAULT NULL COMMENT '邮件地址',
	`phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
	`alias` varchar(64) DEFAULT NULL COMMENT '别名',
	`state` int DEFAULT NULL COMMENT '状态：1,正常 2, 锁定(1年未登录) 3, 冻结(因为账户登录异常等原因) 4, 注销',
	`birthday` datetime DEFAULT NULL COMMENT '生日',
	`sex` int DEFAULT NULL COMMENT '性别：0、女 1、男',
	`avatar_url` varchar(64) DEFAULT NULL COMMENT '头像地址',
	`remark` varchar(250) DEFAULT NULL COMMENT '备注',
	`last_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
	`alg` varchar(20) NULL COMMENT '密码的哈希算法',
	`salt` varchar(40) NULL COMMENT '盐',
	`pwd` varchar(128) NOT NULL COMMENT '用户密码',
	`system` smallint NOT NULL DEFAULT 0 COMMENT '是否是系统用户. 0 否 1 是',
	`reg_time` datetime DEFAULT NULL COMMENT '注册时间，精确到日',
	PRIMARY KEY(`id`),
	UNIQUE KEY `uq_user_name` (`name`),
	UNIQUE KEY `uq_user_email` (`email`),
	UNIQUE KEY `uq_user_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/** 默认密码 111111 */
insert into m_user(name, alias, state, alg, salt, pwd, system, reg_time) values('admin', 'admin', 1, 'SHA256', '1', 'JVijTU0glkyh0nKrJszOlRHYgFeVk81MngGrke0A8yU=', 1, now());

/**
 * 角色表
 */
CREATE TABLE m_role (
	`id` bigint NOT NULL auto_increment COMMENT '主键',
	`name` varchar(32) NOT NULL COMMENT '角色名',
	`description` varchar(128) DEFAULT NULL COMMENT '描述信息',
	`system` smallint NOT NULL DEFAULT 0 COMMENT '是否是系统角色. 0 否 1 是',
	
	`update_time` datetime DEFAULT NULL COMMENT '更新时间',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY(`id`),
	UNIQUE KEY `uq_role_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into m_role(name, description, system, update_time, create_time) values('admin', '超级管理员', 1, now(), now());
insert into m_role(name, description, system, update_time, create_time) values('maintenance', '运维人员', 1, now(), now());
insert into m_role(name, description, system, update_time, create_time) values('author', '作者', 1, now(), now());
insert into m_role(name, description, system, update_time, create_time) values('reader', '读者', 1, now(), now());

/**
 * 用户-角色表
 */
CREATE TABLE m_user_role (
	`id` bigint NOT NULL auto_increment COMMENT '主键',
	`user_id` bigint NOT NULL COMMENT '用户id',
	`role_id` bigint NOT NULL COMMENT '角色id',
	
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY(`id`),
	UNIQUE KEY `uq_user_role` (`user_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into m_user_role (user_id, role_id, create_time) values(
	(select id from m_user where name = 'admin'),
	(select id from m_role where name = 'admin'),
	now()
);

/**
 * 菜单表
 */
CREATE TABLE m_menu (
	`id` bigint NOT NULL auto_increment COMMENT '主键',
	`name` varchar(50) NOT NULL COMMENT '名称',
	`label` varchar(100) comment '标签',
	`uri` varchar(100) COMMENT '地址',
	`sequence` int COMMENT '顺序号',
	`parent_menu_id` bigint comment '父菜单id',
	
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY(`id`),
	UNIQUE KEY `uq_permission_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into m_menu (id, name, label, uri, sequence, create_time) values(1, 'UserSetting', '用户设置', null, 1, now());
insert into m_menu (id, name, label, uri, sequence, create_time) values(2, 'AuthorArea', '作家专区', null, 2, now());
insert into m_menu (id, name, label, uri, sequence, create_time) values(3, 'Maintenance', '运维监控', null, 3, now());

insert into m_menu (name, label, uri, sequence, parent_menu_id, create_time) values('BasicInfo', '基本信息', 'user/info', 1, 1, now());
insert into m_menu (name, label, uri, sequence, parent_menu_id, create_time) values('ModifyPwd', '修改密码', 'user/toUpdatePwd', 2, 1, now());
insert into m_menu (name, label, uri, sequence, parent_menu_id, create_time) values('Bookshelf', '我的书架', 'bookshelf/toList', 3, 1, now());

insert into m_menu (name, label, uri, sequence, parent_menu_id, create_time) values('MyBooks', '我的作品', 'book/list', 1, 2, now());
insert into m_menu (name, label, uri, sequence, parent_menu_id, create_time) values('Writing', '我要写作', 'book/toWritePage', 2, 2, now());
insert into m_menu (name, label, uri, sequence, parent_menu_id, create_time) values('ToAuthor', '信息注册', 'user/toCreateAuthor', 3, 2, now());
insert into m_menu (name, label, uri, sequence, parent_menu_id, create_time) values('AuthorProtocol', '作者协议', 'user/toAuthorPrincple', 4, 2, now());

insert into m_menu (name, label, uri, sequence, parent_menu_id, create_time) values('UserManager', '用户管理', 'user/list', 1, 3, now());
insert into m_menu (name, label, uri, sequence, parent_menu_id, create_time) values('RuleManager', '规则管理', 'spider/toSpiderWebsite', 2, 3, now());
insert into m_menu (name, label, uri, sequence, parent_menu_id, create_time) values('ChapterExamine', '文章审核', 'spider/toUnauditBooks', 3, 3, now());
insert into m_menu (name, label, uri, sequence, parent_menu_id, create_time) values('BookManager', '文章管理', 'book/list', 4, 3, now());

/**
 * 权限表
 */
CREATE TABLE m_permission (
	`id` bigint NOT NULL auto_increment COMMENT '主键',
	`name` varchar(50) NOT NULL COMMENT '权限名',
	`label` varchar(100) NOT NULL COMMENT '标签',
	`uri` varchar(100) COMMENT '地址',
	`sequence` int COMMENT '顺序号',
	`menu_id` bigint comment '菜单id',
	
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY(`id`),
	FOREIGN KEY fk_menu_id (`menu_id`) REFERENCES `m_menu` (`id`),
	UNIQUE KEY `uq_permission_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into m_permission (name, label, sequence, menu_id, create_time) 
	values ('UserManager.Add', '新增', 1, (select id from m_menu where name = 'UserManager'), now());
insert into m_permission (name, label, sequence, menu_id, create_time) 
	values ('UserManager.Update', '修改', 2, (select id from m_menu where name = 'UserManager'), now());
insert into m_permission (name, label, sequence, menu_id, create_time) 
	values ('UserManager.Delete', '删除', 3, (select id from m_menu where name = 'UserManager'), now());
	
insert into m_permission (name, label, sequence, menu_id, create_time) 
	values ('RuleManager.Add', '新增', 1, (select id from m_menu where name = 'RuleManager'), now());
insert into m_permission (name, label, sequence, menu_id, create_time) 
	values ('RuleManager.Update', '修改', 2, (select id from m_menu where name = 'RuleManager'), now());
insert into m_permission (name, label, sequence, menu_id, create_time) 
	values ('RuleManager.Delete', '删除', 3, (select id from m_menu where name = 'RuleManager'), now());

/**
 * 角色-权限表
 */
CREATE TABLE m_role_permission (
	`id` bigint NOT NULL auto_increment COMMENT '主键',
	`role_id` bigint NOT NULL COMMENT '角色id',
	`permission_id` bigint NOT NULL COMMENT '权限id',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY(`id`),
	UNIQUE KEY `uq_role_permission` (`role_id`, `permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into m_role_permission (role_id, permission_id, create_time) values (
	(select id from m_role where name = 'admin'),
	(select id from m_permission where name = 'RuleManager.Add'),
	now()
);

/**
 * 书籍表。存储所有的书籍信息，并对书籍进行分类。后续可能会进行调整。书籍检索，会单独作为一个服务存在。
 */
CREATE TABLE m_book (
	`id` bigint NOT NULL auto_increment primary key COMMENT '主键，唯一标识符',
	`name` varchar(120) DEFAULT NULL COMMENT '名称',
	`introduction` text DEFAULT NULL COMMENT '简介',

	`author_id` bigint DEFAULT NULL COMMENT '外键，作者id',
	`author` varchar(32) comment '作者名称',
	`img` varchar(100) comment '封面',
	
	`last_chapter_url` varchar(200) comment '最近检索的章节地址',
	
	`create_time` datetime DEFAULT NULL COMMENT '创建时间(开始写作时间)',
	`update_time` datetime DEFAULT NULL COMMENT '更新时间(最新章节更新时间)',
	`url` varchar(100) comment '书籍地址',
	
	`retrive_count` int not null default 0 comment '检索次数',
	`retrive_state` tinyint comment '检索状态 1 检索完成(成功) 2 等待检索 3 正在检索 4 检索失败',
	`retrive_start_time` datetime comment '检索开始时间',
	`retrive_stop_time` datetime comment '检索结束时间',
	`retrive_fail_cause` text comment '检索失败原因',
	
	`state` tinyint not null default 1 COMMENT '状态 1 正常 2 关闭 3 未审核',
	`serialize_status` tinyint DEFAULT NULL COMMENT '状态：1、写作中 2、已完结',
	`tags` varchar(30) DEFAULT NULL COMMENT '标签',
	`words` int DEFAULT NULL COMMENT '字数',
	`charged` tinyint not null DEFAULT 0 COMMENT '是否收费，0不收费 1收费',
	`charge` int comment '费用, 单位: 分',
	`click_times` int DEFAULT NULL COMMENT '点击次数',
	`comment_times` int DEFAULT NULL COMMENT '评论次数',
	
	`website_id` bigint comment '网站id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 书籍--分卷表。存储所有的书籍分卷信息。
 */
CREATE TABLE m_volume (
	`id` bigint NOT NULL auto_increment COMMENT '主键，唯一标识符',
	`volume_name` varchar(120) DEFAULT NULL COMMENT '卷名称',
	`charged` int DEFAULT NULL COMMENT '是否收费',
	`create_time` datetime DEFAULT NULL COMMENT '创建时间(开始写作时间)',
	`update_time` datetime DEFAULT NULL COMMENT '更新时间(最新章节更新时间)',
	`words` int DEFAULT NULL COMMENT '字数',
	`book_id` bigint DEFAULT NULL COMMENT '外键，书籍id',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 书籍章节表。存储书籍的章节信息。该表的信息量会比较大，需要考虑存取的效率问题。
 */
CREATE TABLE m_chapter (
	`id` bigint NOT NULL auto_increment COMMENT '主键，唯一标识符',
	`book_id` bigint DEFAULT NULL COMMENT '外键，书籍id',
	`subject` varchar(64) DEFAULT NULL COMMENT '章节标题',
	`volume_id` bigint DEFAULT NULL COMMENT '外键，分卷id',
	`content` text DEFAULT NULL COMMENT '章节内容',
	`serial_no` int DEFAULT 0 COMMENT '章节序列号', 
	`charged` int DEFAULT NULL COMMENT '是否收费，默认不收费',
	`price` int NOT NULL DEFAULT 0 COMMENT '计费单价',
	`state` tinyint not null default 1 comment '状态. 1 正常 2 关闭 3 未审核',
	`audit_time` datetime DEFAULT NULL COMMENT '审核时间',
	`write_time` datetime DEFAULT NULL COMMENT '写作时间',
	`input_time` datetime DEFAULT NULL COMMENT '录入时间',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE m_tag (
	`id` bigint NOT NULL auto_increment COMMENT '主键，唯一标识符',
	`name` varchar(120) DEFAULT NULL COMMENT '卷名称',
	`create_time` datetime DEFAULT NULL COMMENT '添加时间',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into m_tag (name, create_time) values ('古典', now());
insert into m_tag (name, create_time) values ('科幻', now());
insert into m_tag (name, create_time) values ('玄幻', now());
insert into m_tag (name, create_time) values ('言情', now());
insert into m_tag (name, create_time) values ('散文', now());
insert into m_tag (name, create_time) values ('武侠', now());
insert into m_tag (name, create_time) values ('历史', now());
insert into m_tag (name, create_time) values ('纪实文学', now());
insert into m_tag (name, create_time) values ('诗歌', now());

/**
 * 书籍的评论表。存储用户对书籍的评论信息。
 */
CREATE TABLE m_comment (
	`id` bigint NOT NULL COMMENT '主键，唯一标识符',
	`content` text DEFAULT NULL COMMENT '评论内容',
	`status` int COMMENT '帖子状态。0、关闭，1、审核，2、打开',
	`create_time` datetime DEFAULT NULL COMMENT '评论时间',
	`refer_id` bigint COMMENT '团员对团员的回复',
	`book_id` bigint DEFAULT NULL COMMENT '外键，书籍id',
	`user_id` bigint DEFAULT NULL COMMENT '外键，用户id',
	`user_alias` varchar(64) COMMENT '用户别名',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 书架表。存储书架图书信息。
 */
CREATE TABLE m_bookshelf (
	`id` bigint NOT NULL auto_increment COMMENT '主键，唯一标识符',
	`book_id` bigint DEFAULT NULL COMMENT '外键，书籍id',
	`book_name` varchar(64) COMMENT '书籍名称',
	`book_author` varchar(64) COMMENT '书籍作者',
	
	`user_id` bigint DEFAULT NULL COMMENT '外键，用户id',
	`update_time` datetime comment '更新时间',
	`create_time` datetime comment '添加时间',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 论坛主题表。
 */
CREATE TABLE m_forum_subject (
	`id` bigint NOT NULL auto_increment COMMENT '主键，唯一标识符',
	`subject` varchar(120) COMMENT '帖子标题',
	`type` int COMMENT '帖子类型',
	`content` text COMMENT '帖子内容',
	`status` int COMMENT '帖子状态。0、关闭，1、审核，2、打开',
	`create_time` datetime COMMENT '发布时间',
	`update_time` datetime COMMENT '最近更新时间',
	`read_times` int COMMENT '阅读次数',
	`reply_times` int COMMENT '回复次数',
	`user_id` bigint DEFAULT NULL COMMENT '外键，用户id',
	`user_alias` varchar(64) COMMENT '用户别名',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 论坛表。
 */
CREATE TABLE m_forum (
	`id` bigint NOT NULL auto_increment COMMENT '主键，唯一标识符',
	`content` text COMMENT '帖子内容',
	`status` int COMMENT '帖子状态。0、关闭，1、审核，2、打开',
	`create_time` datetime COMMENT '帖子的发布时间',
	`subject_id` bigint COMMENT '外键，帖子id。用户回复帖子，持有的主帖id。',
	`refer` text COMMENT '团员对团员的回复',
	`user_id` bigint DEFAULT NULL COMMENT '外键，用户id',
	`user_alias` varchar(64) COMMENT '用户别名',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 备忘表。
 */
CREATE TABLE m_memo (
	`id` bigint NOT NULL auto_increment COMMENT '主键，唯一标识符',
	`title` varchar(120) COMMENT '备忘标题',
	`content` text COMMENT '备忘内容',
	`create_time` datetime COMMENT '创建时间',
	`update_time` datetime COMMENT '最后修改时间',
	`user_id` bigint COMMENT '外键，用户id',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 用户反馈表。
 */
CREATE TABLE m_feedback (
	`id` bigint NOT NULL auto_increment COMMENT '主键，唯一标识符',
	`title` varchar(120) COMMENT '备忘标题',
	`advice` text COMMENT '备忘内容',
	`status` int COMMENT '状态',
	`anonymity` int COMMENT '匿名',
	`create_time` datetime COMMENT '创建时间',
	`user_id` bigint COMMENT '外键，用户id',
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table m_spider_website (
	id bigint not null auto_increment primary key comment '主键',
	name varchar(100) comment '网站名称',
	url varchar(200) comment '网站地址',
	
	rule_id bigint comment '规则id',
	state tinyint not null default 1 comment '状态 1 正常 2 关闭',
	
	retrive_count bigint not null default 0 comment '检索次数',
	retrive_state tinyint comment '检索状态 1 检索完成(成功) 2 等待检索 3 正在检索 4 检索失败',
	retrive_start_time datetime comment '检索开始时间',
	retrive_stop_time datetime comment '检索结束时间',
	retrive_fail_cause text comment '检索失败原因',
	
	update_time datetime comment '修改时间',
	create_time datetime comment '添加时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table m_spider_rule (
	id bigint not null auto_increment primary key comment '主键',
	name varchar(100) comment '网站名称',
		
	book_list_url_format varchar(100) comment '书籍分页地址',
	book_list_start_page_no varchar(100) comment '书籍分页的起始页码',
	book_list_charset varchar(20) comment '书籍分页页面编码',
	book_list_total_page_path varchar(100) comment '总页数xpath',
	book_list_total_page_regular varchar(100) comment '总页数正则表达式',
	book_node_path varchar(100) comment '书籍目录节点xpath',
	book_node_name_path varchar(100) comment '书籍名称xpath',
	book_node_url_path varchar(100) comment '书籍目录地址xpath',
	book_node_author_path varchar(100) comment '作者xpath',
	book_node_img_path varchar(100) comment '封面xpath',
	book_node_introduction_path varchar(100) comment '简介xpath',
	book_node_last_update_time_path varchar(100) comment '最近更新xpath',
	
	catalog_charset varchar(20) comment '目录页面编码',
	catalog_chapter_node_path varchar(100) comment '目录页面章节节点xpath',
	catalog_chapter_url_path varchar(100) comment '目录页面章节地址xpath',
	
	chapter_charset varchar(20) comment '章节内容编码',
	chapter_node_path varchar(100) comment '章节内容xpath',
	chapter_weed varchar(500) comment '内容中需要剔除的关键字',
		
	update_time datetime comment '修改时间',
	create_time datetime comment '添加时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;







