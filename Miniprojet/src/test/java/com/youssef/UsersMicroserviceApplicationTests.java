package com.youssef;

import com.youssef.security.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsersMicroserviceApplicationTests {
	@Autowired
	CustomUserDetailsService fd;
	@Test
	void contextLoads() {
		System.out.println(fd.loadUserByUsername("youssef"));
	}

}
