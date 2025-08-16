package bj.gold.l4;

import java.io.*;
import java.util.*;

public class BJ_G4_02458_키_순서 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer>[] graph = new ArrayList[n + 1];
		ArrayList<Integer>[] reversedGraph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
			reversedGraph[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			reversedGraph[b].add(a);
		}
		
		int ans = 0;
		for (int i = 1 ; i <= n; i++) {
			if (getNodeCount(i, graph) + getNodeCount(i, reversedGraph) == n - 1) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}

	static int getNodeCount(int start, ArrayList<Integer>[] graph) {
		Queue<Integer> bfs = new ArrayDeque<>();
		boolean[] visited = new boolean[graph.length];
		int cnt = 0;

		bfs.offer(start);
		visited[start] = true;

		while (!bfs.isEmpty()) {
			int node = bfs.poll();

			for (int next : graph[node]) {
				if (!visited[next]) {
					bfs.offer(next);
					visited[next] = true;
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}
