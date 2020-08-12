package com.zh.online_class;

import com.zh.online_class.model.entity.User;
import com.zh.online_class.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class OnlineClassApplicationTests {

	@Test
	public void testGeneJwt() {

		User user = new User();
		user.setId(66);
		user.setName("小N");
		user.setHeadImg("jpg");
		String token = JWTUtils.geneJsonWebToken(user);

		System.out.println(token);

		Claims claims = JWTUtils.checkJWT(token);

		System.out.println(claims.get("name"));
//		Assert.isTrue();
	}


}
