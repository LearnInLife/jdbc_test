package cn.lzj.test.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestJunit {

	@Test
	public void testJunit() {
		System.out.println("hello junit!");
	}
	
	@Before
	public void testBefore() {
		System.out.println("test before!");
	}
	
	@After
	public void testAfter() {
		System.out.println("test after!");
	}
}
