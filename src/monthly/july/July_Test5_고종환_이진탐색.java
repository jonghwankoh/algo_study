package monthly.july;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class July_Test5_고종환_이진탐색 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/monthly/july/Test5.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		int[][] dp = new int[N][2]; // height, idx

		int[] ans = new int[N];
		int[] reversedAns = new int[N];

		int[] nearest = new int[N];
		int[] reversedNearest = new int[N];
		Arrays.fill(nearest, -1);
		Arrays.fill(reversedNearest, -1);

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
				int length = nearest[i] != -1 ? Math.abs(nearest[i] - i) : Integer.MAX_VALUE;
				int reversedLength = reversedNearest[reversedIdx] != -1 ? Math.abs(reversedNearest[reversedIdx] - reversedIdx) : Integer.MAX_VALUE;

				int nearestIdx;
				if (length <= reversedLength) {
					nearestIdx = nearest[i];
				} else {
					nearestIdx = N - 1 - reversedNearest[reversedIdx];
				}
				sb.append(total).append(" ").append(nearestIdx + 1).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void solution(int n, int[][] dp, int[] ans, int[] nearest, int[] arr) {
		int size = 1;
		dp[0][0] = arr[0];
		dp[0][1] = 0;
		for (int i = 1; i < n; i++) {
			int idx = binarySearch(dp, size, arr[i]);
			dp[idx][0] = arr[i];
			dp[idx][1] = i;
			size = idx + 1;
			ans[i] += size - 1;
			if (size >= 2) {
				nearest[i] = dp[idx - 1][1];
			}
		}
	}

	// search for descending array
	private static int binarySearch(int[][] arr, int size, int target) {
		int low = 0;
		int high = size - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid][0] < target) {
				high = mid - 1;
			}  else if (arr[mid][0] > target) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		return low; // get right result
	}
}
