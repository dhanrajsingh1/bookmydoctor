package com.dds.bookmydoctor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//@SpringBootTest
class BookmydoctorApplicationTests {
	private MyCalc c = new MyCalc();
	@Test
	void contextLoads() {
		int expected = 6;

		int actual = c.sum(1, 2, 3);

		Assertions.assertEquals(expected, actual);
	}

}
