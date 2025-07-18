package bj.platinum.l5;

import java.util.*;
import java.io.*;



public class BJ_P4_01517_버블소트 {
	/**
	 * @author: SSAFY
	 * @date: 2025. 7. 17.
	 * @performance:
	 * @category:
	 * @note:
	**/
	static int N;
	static int[] arr;
	static Pair[] sorted;
	static int[] segTree;
	
	static class Pair implements Comparable<Pair> {
		int value, idx;
		
		Pair(int value, int idx) {
			this.value = value;
			this.idx = idx;
		}
		
		// (value, asc), (idx, asc)
		@Override
		public int compareTo(Pair o) {
			if (this.value != o.value) 
				return Integer.compare(this.value, o.value);
			return Integer.compare(this.idx, o.idx);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		sorted = new Pair[N];
		
		for (int i = 0; i < N; i++) {
			sorted[i] = new Pair(arr[i], i);
		}
		Arrays.sort(sorted);
		
		// N * 4가 안정적인 것으로 알려져있음
		segTree = new int[N * 4];
		long ans = 0;
		for (int i = 0; i < N; i++) {
			int idx = sorted[i].idx;
			ans += query(1, 0, N - 1, idx + 1, N - 1); // [0, N - 1] 중에서 [idx + 1, N - 1]의 개수를 누적
			update(1, 0, N - 1, idx);
		}
		System.out.println(ans);
	}
	
	static int query(int node, int start, int end, int left, int right) {
		if (end < left || start > right) return 0;
		if (left <= start && end <= right) return segTree[node];
		
		int mid = (start + end) / 2;
		return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
	}
	
	static void update(int node, int start, int end, int target) {
		segTree[node]++;
		if (start == end) return;
		
		int mid = (start + end) / 2;
		if (mid >= target)
			update(node * 2, start, mid, target);
		else
			update(node * 2 + 1, mid + 1, end, target);
	}

}
