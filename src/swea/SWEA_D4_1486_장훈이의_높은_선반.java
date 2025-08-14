package swea;

import java.util.*;
import java.io.*;

class SWEA_D4_1486_장훈이의_높은_선반
{
	static int n;
	static int[] arr;
	static int ans;
	static int target;
	
	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		StringBuilder output = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			target = Integer.parseInt(st.nextToken());

			arr = new int[n];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = Integer.MAX_VALUE;
			dfs(0, 0);
			output.append(String.format("#%d %d\n", test_case, ans));
		}
		System.out.println(output);
	}
	
	static void dfs(int idx, int height) {
		if (idx == n) {
			if (height >= target)
				ans = Math.min(ans, height - target);
			return;
		}
		if (height - target >= ans) return;
		dfs(idx + 1, height + arr[idx]);
		dfs(idx + 1, height);
	}
}