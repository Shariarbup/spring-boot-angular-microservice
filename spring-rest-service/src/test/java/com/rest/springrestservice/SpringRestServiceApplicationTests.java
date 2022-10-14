package com.rest.springrestservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class SpringRestServiceApplicationTests {
	
	AddTwoNumber underTesTwoNumber = new AddTwoNumber();
	@Test
	void contextLoads() {
		int n_one =20;
		int n_two =30;
		int result = underTesTwoNumber.add(n_one,n_two);
		assertThat(result).isEqualTo(30);
	}
	
	

}
class AddTwoNumber {
	int add(int a, int b) {
		return a+b;
	}
}
