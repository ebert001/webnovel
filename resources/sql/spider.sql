
create table wn_spider_website (
	id bigint not null auto_increment primary key comment '主键',
	name varchar(100) comment '网站名称',
	url varchar(200) comment '网站地址',
	last_retrive_time datetime comment '最近检索时间',
	update_time datetime comment '修改时间',
	create_time datetime comment '添加时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table wn_spider_book (
	id bigint not null auto_increment primary key comment '主键',
	website_id bigint comment '网站id',
	name varchar(100) comment '书籍名称',
	url varchar(200) comment '书籍地址',
	last_chapter_url varchar(200) comment '最近检索的章节地址',
	last_retrive_time datetime comment '最近检索时间',
	update_time datetime comment '修改时间',
	create_time datetime comment '添加时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





