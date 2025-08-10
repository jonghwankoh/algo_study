package recruitment.test;

import static recruitment.Autoever_2025_H2_P1.Solution.*;
import static recruitment.test.SimpleTestUtils.*;

public class Autoever_2025_H2_P1_Test {
	public static void main(String[] args) {
		testBasicCase();
		testAllShortestPaths();
		testNonShortestPath();
		testComplexMixedConditions();
		
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
	static void testComplexMixedConditions() {
		int n = 8;
		int[][] roads = {
			{1, 2, 10, 1},
			{2, 3, 5, 2},
			{3, 4, 3, 3},
			{1, 5, 20, 3},
			{5, 6, 1, 2},
			{6, 4, 2, 1},
			{2, 7, 7, 3},
			{7, 8, 8, 1},
			{8, 4, 1, 5},
			{1, 4, 30, 2},
			{2, 4, 15, 4},
			{3, 5, 12, 1},
			{6, 7, 4, 4}
		};
		int[] record = {-2, -1, 3};
		int[][] expected = {{2, 1}, {3, 5}, {6, 4}, {6, 6}, {6, 7}, {6, 8}};
		
		int[][] result = solution(n, roads, record);
		assertArrayEquals("Complex Mixed Conditions", expected, result);
	}
}