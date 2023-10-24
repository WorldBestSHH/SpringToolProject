package com.jslhrd.exguest.mappertest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jslhrd.exguest.domain.GuestDTO;
import com.jslhrd.exguest.mapper.GuestMapper;

@SpringBootTest
public class GuestMapperTest {
	private static final Logger log = LoggerFactory.getLogger(GuestMapperTest.class);
	
	@Autowired
	private GuestMapper mapper;
	
/*	@Test
	public void guestCountTest() {
		int cnt=mapper.guestCount();
		log.info("Guest Count : " + cnt);
	}
*/
	
	@Test
	public void guestListTest() {
		mapper.guestList().forEach(guest->log.info(guest.toString()));
	}
}
