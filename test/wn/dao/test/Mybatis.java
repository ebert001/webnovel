package com.aswishes.wn.mvc.dao.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import junit.framework.TestCase;

public class Mybatis extends TestCase {
	
	private final String resource = "db-config.xml";
	
	protected SqlSession sqlSession = null;

	/**
	 * 初始化mybatis资源信息。
	 * 此时，数据源配置在mybatis的配置文件中。
	 */
	protected void setUp() throws Exception {
		// 加载数据库的配置文件信息，后面测试使用
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		
		sqlSession = sqlSessionFactory.openSession();
	}
}
