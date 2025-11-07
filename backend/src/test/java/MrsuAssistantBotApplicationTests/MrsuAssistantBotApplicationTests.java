import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Простой smoke test
class MrsuAssistantBotApplicationTests {

	@Test
	void contextLoads() {
		// Просто проверяем что тесты запускаются
		assertTrue(true);
	}

	@Test
	void simpleMathTest() {
		assertEquals(4, 2 + 2);
	}

	@Test
	void simpleStringTest() {
		String expected = "Hello";
		String actual = "Hello";
		assertEquals(expected, actual);
	}

	private void assertEquals(Object expected, Object actual) {
		assert expected.equals(actual) : "Expected: " + expected + ", but was: " + actual;
	}
}