package com.cqupt.shiro;

import com.cqupt.shiro.utils.SaltUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootShiroApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(SaltUtils.getSalt(4));
	}

}
