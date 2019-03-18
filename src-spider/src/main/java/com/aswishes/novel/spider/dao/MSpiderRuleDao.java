package com.aswishes.novel.spider.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aswishes.novel.mvc.dao.SimpleJdbcDao;
import com.aswishes.novel.spider.entity.MSpiderRule;

@Repository
@Transactional
public class MSpiderRuleDao extends SimpleJdbcDao<MSpiderRule> {


}
