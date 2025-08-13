package bj.gold.l1;

import java.io.*;
import java.util.*;

public class BJ_G1_01700_멀티탭_스케줄링 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] aList = new int[K];
		Set<Integer> aSet = new HashSet<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			aList[i] = Integer.parseInt(st.nextToken());
		}

		int ans = 0;
		for (int i = 0; i < K; i++) {
			int next = aList[i];
			if (aSet.size() < N) {
				aSet.add(next);
				continue;
			}
			if (aSet.contains(next)) continue;
			
			HashSet<Integer> nonVisitSet = new HashSet<>();
			aSet.forEach(nonVisitSet::add);
			for (int j = i + 1; j < K; j++) {
				if (nonVisitSet.size() == 1) {
					break;
				}
				nonVisitSet.remove(aList[j]);
			}
			ans++;
			aSet.remove(nonVisitSet.iterator().next());
			aSet.add(next);
		}
		System.out.println(ans);
	}
}