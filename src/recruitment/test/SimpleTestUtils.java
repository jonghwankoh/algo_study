package recruitment.test;

import java.util.*;

public class SimpleTestUtils {
	private static int totalTests = 0;
	private static int passedTests = 0;

	public static void assertEquals(String testName, Object expected, Object actual) {
		totalTests++;
		if (expected.equals(actual)) {
			passedTests++;
			System.out.println("✅ " + testName + " - PASS");
		} else {
			System.out.println("❌ " + testName + " - FAIL");
			System.out.println("   Expected: " + expected);
			System.out.println("   Actual: " + actual);
		}
	}

	public static void assertArrayEquals(String testName, int[][] expected, int[][] actual) {
		totalTests++;
		if (Arrays.deepEquals(expected, actual)) {
			passedTests++;
			System.out.println("✅ " + testName + " - PASS");
		} else {
			System.out.println("❌ " + testName + " - FAIL");
			System.out.println("   Expected: " + Arrays.deepToString(expected));
			System.out.println("   Actual: " + Arrays.deepToString(actual));
		}
	}

	public static void summary() {
		System.out.println("\n=== Test Summary ===");
		System.out.println("Passed: " + passedTests + "/" + totalTests);
	}
}