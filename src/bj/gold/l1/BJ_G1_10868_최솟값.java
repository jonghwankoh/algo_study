package bj.gold.l1;

import java.io.*;
import java.util.*;

/**
 * @author: SSAFY
 * @date: 2025. 7. 17.
 * @performance: 520ms
 * @category: 세그먼트 트리
 * @note: 버블소트 이후 2번째 세그먼트 트리 풀이
**/

public class BJ_G1_10868_최솟값 {
	static int N;
	static int[] arr;
	static int[] segTree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		segTree = new int[N * 4];
		init(1, 0, N - 1);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			sb.append(query(1, 0, N - 1, a, b)).append("\n");
		}
		
		System.out.println(sb);
	}

	static int init(int node, int start, int end) {
		if (start == end) {
			segTree[node] = arr[start];
			return arr[start];
		}
		
		int mid = (start + end) / 2;
		int left = init(node * 2, start, mid);
		int right = init(node * 2 + 1, mid + 1, end);
		
		segTree[node] = Math.min(left, right);
		
		return segTree[node];
	}
	
	static int query(int node, int start, int end, int a, int b) {
		if (end < a || b < start) return Integer.MAX_VALUE;
		if (a <= start && end <= b) return segTree[node];
		
		int mid = (start + end) / 2;
		
		return Math.min(query(node * 2, start, mid, a, b), query(node * 2 + 1, mid + 1, end, a, b));
	}
}