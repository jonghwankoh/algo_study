package bj.gold.l5;

import java.io.*;
import java.util.*;

public class BJ_G5_01790_수이어쓰기2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		System.out.println(solution(0, 1, 1, N, k - 1));
	}
	
	private static int solution(long idx, long digits, int len, int N, int k) {
		long nextIdx = idx + 9 * digits * len;
		if (k < nextIdx) {
			long dstNum = digits + (k - idx) / len;
			int delta = (int) (k - idx) % len;
			
			if (dstNum > N) {
				return -1;
			}
			
			String dstNumStr = String.valueOf(dstNum);
			return dstNumStr.charAt(delta) - '0';
		}
		return solution(nextIdx, digits * 10, len + 1, N, k);
	}
}
