package bj.gold.l3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_G3_10986_나머지_합 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		int[] sum = new int[n + 1];
		long[] cnt = new long[m]; 
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		sum[0] = 0;
		cnt[0]++;
		for (int i = 1; i <= n; i++) {
			sum[i] = (sum[i - 1] + arr[i - 1]) % m;
			cnt[sum[i]]++;
		}
		
		long ans = 0;
		for (int i = 0; i < m; i++) {
			ans += cnt[i] * (cnt[i] - 1) / 2;
		}
		System.out.println(ans);
				
	}
}
