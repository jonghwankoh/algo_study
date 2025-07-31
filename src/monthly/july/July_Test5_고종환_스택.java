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
		int[][] dp = new int[N][2]; // height, idx

		int[] ans = new int[N];
		int[] reversedAns = new int[N];

		int[][] nearest = new int[N][2]; // {idx, length}
		int[][] reversedNearest = new int[N][2];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] reversedArr = new int[N];
		for (int i = 0; i < N; i++) {
			reversedArr[i] = arr[N - 1 - i];
		}

		// left to right
		solution(N, dp, ans, nearest, arr);

		// right to left
		solution(N, dp, reversedAns, reversedNearest, reversedArr);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int reversedIdx = N - 1 - i;
			int total = ans[i] + reversedAns[reversedIdx];
			if (total == 0) {
				sb.append("0\n");
			} else {
				// select nearest building
				int nearestIdx = nearest[i][1] <= reversedNearest[reversedIdx][1] ? nearest[i][0] : N - 1 - reversedNearest[reversedIdx][0];
				sb.append(total).append(" ").append(nearestIdx + 1).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void solution(int n, int[][] dp, int[] ans, int[][] nearest, int[] arr) {
		int size = 1;
		dp[0][0] = arr[0];
		dp[0][1] = 0;
		nearest[0][1] = Integer.MAX_VALUE;
		for (int i = 1; i < n; i++) {
			while (size > 0 && dp[size - 1][0] <= arr[i]) {
				size--; // pop
			}
			// push
			dp[size][0] = arr[i];
			dp[size][1] = i;
			ans[i] += size;
			if (size >= 1) {
				nearest[i][0] = dp[size - 1][1];
				nearest[i][1] = i - dp[size - 1][1];
			} else {
				nearest[i][1] = Integer.MAX_VALUE;
			}
			size++;
		}
	}
}
