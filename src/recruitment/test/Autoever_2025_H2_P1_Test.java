package recruitment.test;

import static recruitment.Autoever_2025_H2_P1.Solution.*;
import static recruitment.test.SimpleTestUtils.*;

public class Autoever_2025_H2_P1_Test {
	public static void main(String[] args) {
		testBasicCase();
		testAllShortestPaths();
		testNonShortestPath();
		
		summary();
	}

	static void testBasicCase() {
		int n = 6;
		int[][] roads = {
			{1, 2, 3, 1}, {1, 3, 4, 3}, {1, 5, 4, 2}, {1, 6, 7, 4},
			{2, 4, 1, 2}, {2, 5, 5, 4}, {3, 6, 2, 1}, {4, 5, 4, 1}, {5, 6, 5, 3}
		};
		int[] record = {1, -4, 2};
		int[][] expected = {{3, 5}, {4, 5}};
		
		int[][] result = solution(n, roads, record);
		assertArrayEquals("Basic Case", expected, result);
	}

	static void testAllShortestPaths() {
		int n = 4;
		int[][] roads = {{1, 2, 10, 1}, {2, 3, 10, 2}, {3, 4, 10, 3}, {1, 4, 40, 4}};
		int[] record = {1, 2, 3};
		int[][] expected = {{1, 4}};
		
		int[][] result = solution(n, roads, record);
		assertArrayEquals("All Shortest Paths", expected, result);
	}

	static void testNonShortestPath() {
		int n = 4;
		int[][] roads = {{1, 2, 5, 1}, {1, 3, 1, 2}, {3, 4, 1, 3}, {2, 4, 10, 4}};
		int[] record = {-1, 4};
		int[][] expected = {{1, 4}};
		
		int[][] result = solution(n, roads, record);
		assertArrayEquals("Non-Shortest Path", expected, result);
	}
}