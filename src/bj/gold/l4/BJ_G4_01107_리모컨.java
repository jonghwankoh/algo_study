package bj.gold.l4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_G4_01107_리모컨 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		boolean[] isWorking = new boolean[10];
		Arrays.fill(isWorking, true);

		if (m > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				isWorking[tmp] = false;
			}
		}
		
		
		int ans = solution(isWorking, n);
		System.out.println(ans);
	}

	private static int solution(boolean[] isWorking, int n) {
		int ans = Math.abs(100 - n);
		int i = 0 ;
		while (i <= 1_000_000) {
			int tmp = i;
			int digits = 0, pow = 1;
			boolean isPossible = true;
			do {
				if (!isWorking[tmp % 10]) {
					i += pow;
					isPossible = false;
					break;
				}
				tmp /= 10;
				digits++;
				pow *= 10;
			} while(tmp > 0);
			if (isPossible) {
				ans = Math.min(ans, Math.abs(n - i) + digits);
				if (i > n) {
					break;
				}
				i++;
			}
		}
		return ans;
	}
}
