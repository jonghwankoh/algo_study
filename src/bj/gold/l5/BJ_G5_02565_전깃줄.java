package bj.gold.l5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G5_02565_전깃줄 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[501];
		int[] DP = new int[501];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a] = b;
		}
		
		for (int i = 1; i <= 500; i++) {
			if (arr[i] == 0) continue;
			DP[i] = 1;
			for (int j = 1; j < i; j++) {
				if (arr[j] == 0) continue;
				if (arr[j] < arr[i]) {
					DP[i] = Math.max(DP[i], DP[j] + 1);
				}
			}
		}
		
		int ans = 0;
		for (int i = 1; i <= 500; i++) {
			ans = Math.max(ans, DP[i]);
		}
		
		System.out.println(n - ans);
	}
}
