package utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestException {
	
	public static void assertExceptionThrown(Runnable r){
		boolean exceptionThrown = false;
		try{
			r.run();
		} catch(Exception e){
			exceptionThrown = true;
		}
		assertTrue("Expected an exception to be thrown", exceptionThrown);
	}
	
	@Test
	public void testAssertExceptionThrown(){
		Runnable r = new Runnable(){
			@Override
			public void run() {
				throw new RuntimeException();
		}};
		assertExceptionThrown(r);
		
	}
}
