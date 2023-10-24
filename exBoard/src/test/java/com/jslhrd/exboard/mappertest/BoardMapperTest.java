package com.jslhrd.exboard.mappertest;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jslhrd.exboard.mapper.BoardMapper;

@SpringBootTest
public class BoardMapperTest {
	private Logger log = LoggerFactory.getLogger(BoardMapperTest.class);
	
	@Autowired
	BoardMapper mapper;
	
	@Test
	public void boardCountTest() {
		int totcount = mapper.boardCount();
		log.info("totcount = " + totcount);
	}
}
