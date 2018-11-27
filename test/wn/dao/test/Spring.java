package com.aswishes.wn.mvc.dao.test;

import junit.framework.TestCase;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Spring extends TestCase {

	private final String resource = "WebRoot/WEB-INF/spring-context.xml";
	
	protected ApplicationContext context = null;
	
	protected SqlSession sqlSession = null;
	
	protected void setUp() throws Exception {
		context = new FileSystemXmlApplicationContext(resource);
		
//		context = new ClassPathXmlApplicationContext(resource);
//		SqlSessionFactoryBean factoryBean = (SqlSessionFactoryBean) context.getBean("sqlSessionFactory");
//		sqlSession = factoryBean.getObject().openSession();
		
		DefaultSqlSessionFactory factoryBean = (DefaultSqlSessionFactory) context.getBean("sqlSessionFactory");
		sqlSession = factoryBean.openSession();
		
	}
}
