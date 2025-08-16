package bj.gold.l1;

import java.io.*;
import java.util.*;

public class BJ_G1_17472_다리_만들기_2 {
	static class Edge implements Comparable<Edge> {
		int src, dst, weight;

		Edge(int src, int dst, int weight) {
			this.src = src;
			this.dst = dst;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int N;
	static int M;
	static boolean[][] map;
	static boolean[][] visited;
	static int[][] islandMap;
	static int[] parent;

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		visited = new boolean[N][M];
		islandMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = st.nextToken().charAt(0) == '1';
			}
		}

		// 1. 섬 찾고 인덱스 붙이기
		ArrayList<ArrayList<int[]>> islandList = new ArrayList<>();
		islandList.add(null); // 인덱스는 1부터
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] || !map[i][j]) continue;
				islandList.add(findBordersOfArea(islandList.size(), i, j));
			}
		}
		// 2. 섬끼리 다리 길이 최단 거리 구하기
		int islandNum = islandList.size() - 1;
		int[][] adj = new int[islandNum + 1][islandNum + 1];
		for (int i = 1; i <= islandNum; i++) {
			Arrays.fill(adj[i], Integer.MAX_VALUE);
		}

		for (int i = 1; i <= islandNum; i++) {
			ArrayList<int[]> borderList = islandList.get(i);
			for (int[] border : borderList) {
				int x = border[0], y = border[1];
				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d], ny = y + dy[d], len = 0;

					while (nx >= 0 && ny >= 0 && nx < N && ny < M) {
						if (islandMap[nx][ny] != 0) {
							if (len < 2) break;
							int dst = islandMap[nx][ny];
							adj[i][dst] = Math.min(adj[i][dst], len);
							break;
						}

						nx += dx[d];
						ny += dy[d];
						len++;
					}
				}
			}
		}

		// 3. 최소 스패닝 트리의 간선 길이 합이 정답.
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		for (int i = 1; i <= islandNum; i++) {
			for (int j = i + 1; j <= islandNum; j++) {
				if (adj[i][j] != Integer.MAX_VALUE) {
					pq.offer(new Edge(i, j, adj[i][j]));
				}
			}
		}

		int edgeCnt = 0, totalWeight = 0;
		parent = new int[islandNum + 1];
		for (int i = 1; i <= islandNum; i++) {
			parent[i] = i;
		}

		while (!pq.isEmpty() && edgeCnt < islandNum - 1) {
			Edge edge = pq.poll();

			int rootSrc = findRoot(edge.src);
			int rootDst = findRoot(edge.dst);
			if (rootSrc != rootDst) {
				parent[rootSrc] = rootDst;
				edgeCnt++;
				totalWeight += edge.weight;
			}
		}
		System.out.println(edgeCnt == islandNum - 1 ? totalWeight : -1);
	}

	static ArrayList<int[]> findBordersOfArea(int idx, int startX, int startY) {
		ArrayList<int[]> borders = new ArrayList<>();

		Queue<int[]> bfs = new ArrayDeque<>();

		bfs.offer(new int[]{startX, startY});
		visited[startX][startY] = true;

		while (!bfs.isEmpty()) {
			int[] pos = bfs.poll();
			int x = pos[0], y = pos[1];
			
			boolean isBorder = false;
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
					if (visited[nx][ny]) continue;

					if (map[nx][ny]) {
						bfs.offer(new int[]{nx, ny});
						visited[nx][ny] = true;
					} else {
						isBorder = true;
					}
				}
			}

			if (isBorder){
				islandMap[x][y] = idx;
				borders.add(new int[]{x, y});
			}
		}

		return borders;
	}

	static int findRoot(int child) {
		if (parent[child] == child) return child;
		return findRoot(parent[child]);
	}
}