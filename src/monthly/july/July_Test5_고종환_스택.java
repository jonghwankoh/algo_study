package monthly.july;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class July_Test5_고종환_스택 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/monthly/july/Test5.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		int[] ans = new int[N];
		int[] reversedAns = new int[N];

		int[] nearest = new int[N];
		int[] reversedNearest = new int[N];
		int[] reversedArr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			reversedArr[N - 1 - i] = arr[i];
		}

		// left to right
		solution(N, ans, nearest, arr);

		// right to left
		solution(N, reversedAns, reversedNearest, reversedArr);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int reversedIdx = N - 1 - i;
			int total = ans[i] + reversedAns[reversedIdx];
			if (total == 0) {
				sb.append("0\n");
			} else {
				// select nearest building
				int leftDist = nearest[i] != -1 ? i - nearest[i] : Integer.MAX_VALUE;
				int rightDist = reversedNearest[reversedIdx] != -1 ? reversedIdx - reversedNearest[reversedIdx] : Integer.MAX_VALUE;

				int nearestIdx;
				if (leftDist <= rightDist) {
					nearestIdx = nearest[i];
				} else {
					nearestIdx = N - 1 - reversedNearest[reversedIdx];
				}
				sb.append(total).append(" ").append(nearestIdx + 1).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void solution(int n, int[] ans, int[] nearest, int[] arr) {
		int size = 1;
		int[] stack = new int[n];
		stack[0] = 0;
		nearest[0] = -1;
		for (int i = 1; i < n; i++) {
			while (size > 0 && arr[stack[size - 1]] <= arr[i]) {
				size--; // pop
			}
			// push
			stack[size++] = i;
			ans[i] += size - 1;
			if (size >= 2) {
				nearest[i] = stack[size - 2];
			} else {
				nearest[i] = -1;
			}
		}
	}
}
