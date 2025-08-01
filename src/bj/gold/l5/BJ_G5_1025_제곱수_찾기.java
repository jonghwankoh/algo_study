package bj.gold.l5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_1025_제곱수_찾기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}

		int ans = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int stride1 = -i; stride1 < n - i; stride1++) {
					for (int stride2 = -j; stride2 < m - j; stride2++) {
						if (stride1 == stride2 && stride1 == 0) {
							if (isPerfect(arr[i][j])) {
								ans = Math.max(ans, arr[i][j]);
							}
							continue;
						}

						int x = i, y = j, sum = 0;
						while (x >= 0 && x < n && y >= 0 && y < m) {
							sum = sum * 10 + arr[x][y];
							x += stride1;
							y += stride2;
							if (isPerfect(sum)) {
								ans = Math.max(ans, sum);
							}
						}
					}
				}
			}
		}
		System.out.println(ans);
	}

	private static boolean isPerfect(int n) {
		int sqrt = (int) Math.sqrt(n);
		return sqrt * sqrt == n;
	}
}
