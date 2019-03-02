
create table wn_spider_website (
	id bigint not null auto_increment primary key comment '主键',
	name varchar(100) comment '网站名称',
	url varchar(200) comment '网站地址',
	
	rule_id bigint comment '规则id',
	
	retrive_count bigint not null default 0 comment '检索次数',
	retrive_state tinyint comment '检索状态 1 检索完成(成功) 2 等待检索 3 正在检索 4 检索失败',
	retrive_start_time datetime comment '检索开始时间',
	retrive_stop_time datetime comment '检索结束时间',
	retrive_fail_cause text comment '检索失败原因',
	
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
	
	retrive_count bigint not null default 0 comment '检索次数',
	retrive_state tinyint comment '检索状态 1 检索完成(成功) 2 等待检索 3 正在检索 4 检索失败',
	retrive_start_time datetime comment '检索开始时间',
	retrive_stop_time datetime comment '检索结束时间',
	retrive_fail_cause text comment '检索失败原因',
	
	update_time datetime comment '修改时间',
	create_time datetime comment '添加时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table wn_spider_rule (
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





