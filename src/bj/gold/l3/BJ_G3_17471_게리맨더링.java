package bj.gold.l3;

import java.io.*;
import java.util.*;

public class BJ_G3_17471_게리맨더링 {
	static int n;
	static int[] populations;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;

	static int ans = Integer.MAX_VALUE;
	static ArrayList<Integer> aList = new ArrayList<>();
	static ArrayList<Integer> bList = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// input
		n = Integer.parseInt(br.readLine());
		populations = new int[n + 1];
		graph = new ArrayList[n + 1];
		visited = new boolean[n + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
			populations[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			for (int j = 0; j < len; j++) {
				int dst = Integer.parseInt(st.nextToken());
				graph[i].add(dst);
			}
		}

		dfs(0);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	static void dfs(int idx) {
		if (idx == n) {
			if (isAllConnected(aList) && isAllConnected(bList)) {
				int populationA = 0, populationB = 0;
				for (int node : aList) {
					populationA += populations[node];
				}
				for (int node : bList) {
					populationB += populations[node];
				}
				ans = Math.min(ans, Math.abs(populationA - populationB));
			}
			return;
		}

		int nextIdx = idx + 1;
		aList.add(nextIdx);
		dfs(nextIdx);
		aList.remove(aList.size() - 1);

		bList.add(nextIdx);
		dfs(nextIdx);
		bList.remove(bList.size() - 1);
	}

	static boolean isAllConnected(ArrayList<Integer> nodeList) {
		if (nodeList.isEmpty())
			return false;

		Arrays.fill(visited, true);
		for (int node : nodeList) {
			visited[node] = false;
		}
		Queue<Integer> bfs = new ArrayDeque<>();

		int firstNode = nodeList.get(0);
		bfs.offer(firstNode);
		visited[firstNode] = true;
		int visitCnt = 1;

		while(!bfs.isEmpty()) {
			int node = bfs.poll();
			for (int next : graph[node]) {
				if (!visited[next]) {
					bfs.offer(next);
					visited[next] = true;
					visitCnt++;
				}
			}
		}

		return visitCnt == nodeList.size();
	}
}
