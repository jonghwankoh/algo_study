package bj.gold.l5;

import java.io.*;
import java.util.*;

public class BJ_G5_25682_체스판다시칠하기2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean[][] arr = new boolean[N + 1][M + 1];
		int[][] dp = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			
			for (int j = 1; j <= M; j++) {
				arr[i][j] = line.charAt(j - 1) != 'W';
				int isCorrect = arr[i][j] ^ ((i+j) % 2 == 0) ? 1 : 0;
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + isCorrect;
			}
		}
		int maxAns = 0, minAns = Integer.MAX_VALUE;
		for (int i = K; i <= N; i++) {
			for (int j = K; j <= M; j++) {
				int candidate = dp[i][j] - dp[i-K][j] - dp[i][j-K] + dp[i-K][j-K];
				maxAns = Math.max(maxAns, candidate);
				minAns = Math.min(minAns, candidate);
			}
		}
		System.out.println(Math.min(K*K - maxAns, minAns));
	}
}
