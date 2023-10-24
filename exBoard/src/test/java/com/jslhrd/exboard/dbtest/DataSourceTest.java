package com.jslhrd.exboard.dbtest;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataSourceTest {
	private Logger log = LoggerFactory.getLogger(DataSourceTest.class);
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void dbTest() {
		SqlSession session = sqlSessionFactory.openSession();
		Connection conn = session.getConnection();
		
		log.info("Sqlsession : " + session);
		log.info("Connection : " + conn);
	}
}
