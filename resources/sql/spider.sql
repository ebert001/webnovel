
create table wn_spider_website (
	id bigint not null auto_increment primary key comment '主键',
	name varchar(100) comment '网站名称',
	url varchar(200) comment '网站地址',
	
	book_list_url_prefix varchar(100) comment '书籍分页地址前缀',
	page_no varchar(100) comment '书籍分页的当前页码',
	book_list_url_suffix varchar(100) comment '书籍分页地址后缀',
	book_list_charset varchar(100) comment '书籍分页页面编码',
	book_node_path varchar(100) comment '书籍节点xpath',
	book_name_path varchar(100) comment '书籍名称xpath',
	total_page_path varchar(100) comment '总页数xpath',
	total_page_express varchar(100) comment '总页数正则表达式',
	author_path varchar(100) comment '作者xpath',
	img_path varchar(100) comment '封面xpath',
	introduction_path varchar(100) comment '简介xpath',
	last_update_time_path varchar(100) comment '最近更新xpath',
		
	last_retrive_time datetime comment '最近检索时间',
	update_time datetime comment '修改时间',
	create_time datetime comment '添加时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table wn_spider_book (
	id bigint not null auto_increment primary key comment '主键',
	website_id bigint comment '网站id',
	name varchar(100) comment '书籍名称',
	url varchar(200) comment '书籍地址',
	
	author varchar(100) comment '作者',
	img varchar(100) comment '封面',
	introduction varchar(100) comment '简介',
	last_update_time varchar(100) comment '最近更新',
	
	last_chapter_url varchar(200) comment '最近检索的章节地址',
	last_retrive_time datetime comment '最近检索时间',
	update_time datetime comment '修改时间',
	create_time datetime comment '添加时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





