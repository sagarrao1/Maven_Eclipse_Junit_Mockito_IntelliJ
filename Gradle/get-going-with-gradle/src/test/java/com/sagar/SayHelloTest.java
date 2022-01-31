package com.sagar;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class SayHelloTest {

	@Test
	void testSayHello() throws IOException {
		SayHello.main(new String[] {"en"});
	}

}
