package com.forexapp.forexapplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ForexapplicationApplicationTests {

	@Test
	void contextLoads() {
	}

}
