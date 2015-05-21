package utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static utils.Error.newMessage;

public class TestError {

	@Test
	public void test(){
		Error<Integer> msg = newMessage(0, null);
		assertEquals("MessagePair value should be 0", new Integer(0), msg.getVal());
		assertFalse("MessagePair should not have an error", msg.hasError());
		msg = newMessage(0, "error!");
		assertTrue("MessagePair should have an error", msg.hasError());
	}

	@Test
	public void testFormatting(){
		Error<Integer> msg = newMessage(0, "%s:%d", "hello", 7);
		assertEquals("MessagePair formatted incorrectly", "hello:7", msg.getError());
	}
	
	public static <X> void assertNoError(Error<X> err){
		assertFalse(err.getError(), err.hasError());
	}
	
	public static <X> void assertError(Error<X> err){
		assertTrue(err.getError(), err.hasError());
	}
}
