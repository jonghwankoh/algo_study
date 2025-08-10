package recruitment.test;

import static recruitment.Autoever_2025_H2_P1.solution;

public class Autoever_2025_H2_P1_Test {
	public static void main(String[] args) {
		// 제공 테스트 케이스 1
		int n = 6; // Number of intersections
        int[][] roads = {
            {1, 2, 3, 1}, // Red road
            {1, 3, 4, 3}, // Blue road
            {1, 5, 4, 2}, // Green road
            {1, 6, 7, 4}, // Orange road
            {2, 4, 1, 2}, // Green road
            {2, 5, 5, 4}, // Orange road
            {3, 6, 2, 1}, // Red road
            {4, 5, 4, 1}, // Red road
            {5, 6, 5, 3}  // Blue road
        };
        int[] record = {1, -4, 2}; // Example record

        int[][] result = solution(n, roads, record);
		// Print the result to verify
        System.out.println("Possible (start, end) pairs:");
        for (int[] pair : result) {
            System.out.println("(" + pair[0] + ", " + pair[1] + ")");
        }
		// 예상 출력: (3, 5), (4, 5)
		// 추가 테스트 케이스 1
		System.out.println("--- Test Case 1: All moves are shortest paths ---");
		int n1 = 4;
		int[][] roads1 = {
			{1, 2, 10, 1},
			{2, 3, 10, 2},
			{3, 4, 10, 3},
			{1, 4, 40, 4},
		};
		int[] record1 = {1, 2, 3};

		int[][] result1 = solution(n1, roads1, record1);
		System.out.println("Possible (start, end) pairs:");
		for (int[] pair : result1) {
			System.out.println("(" + pair[0] + ", " + pair[1] + ")");
		}
		// 예상 출력: (1, 4)

		// 추가 테스트 케이스 2
		System.out.println("--- Test Case 2: Non-shortest path is required ---");
		int n2 = 4;
		int[][] roads2 = {
			{1, 2, 5, 1},
			{1, 3, 1, 2},
			{3, 4, 1, 3},
			{2, 4, 10, 4},
		};
		int[] record2 = {-1, 4};

		int[][] result2 = solution(n2, roads2, record2);
		System.out.println("Possible (start, end) pairs:");
		for (int[] pair : result2) {
			System.out.println("(" + pair[0] + ", " + pair[1] + ")");
		}
		// 예상 출력: (1, 4)

		// 추가 테스트 케이스 3
		System.out.println("--- Test Case 3: Complex paths with mixed conditions ---");
		int n3 = 8;
		int[][] roads3 = {
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
			{6, 7, 4, 4},
		};
		int[] record3 = {-2, -1, 3};

		int[][] result3 = solution(n3, roads3, record3);
		System.out.println("Possible (start, end) pairs:");
		for (int[] pair : result3) {
			System.out.println("(" + pair[0] + ", " + pair[1] + ")");
		}
		// 예상 출력: (2, 1), (3, 5), (6, 4), (6, 6), (6, 7), (6, 8)
	}
}