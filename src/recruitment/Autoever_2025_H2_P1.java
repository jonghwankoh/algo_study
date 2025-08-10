package recruitment;

import java.util.*;

public class Autoever_2025_H2_P1 {
	static class Pair implements Comparable<Pair> {
		int start, end;

		Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.start == o.start)
				return Integer.compare(this.end, o.end);
			return Integer.compare(this.start, o.start);
		}
	}

	public static int[][] solution(int n, int[][] roads, int[] record) {
		// 2025 하반기 오토에버 신입채용 문제1
		// 네비게이션에 저장된 도로 색상 정보를 이용하여 출발지와 목적지를 역추적하는 문제

		// n: 교차로의 수 (3<=n<=100)
		// 교차로 인덱스: 1 ~ n

		// roads -> {src, dst, weigth, color}
		// roads는 양방향 (3 <= roads.length <= 3000)
		// 1 <= weight <= 1000
		// 1 <= color <= 1000
		// 1 <= record.length <= 100
		// -100 <= record[i] <= 100
		// 모든 교차로는 연결되는 도로의 유도색이 겹치지 않음
		// 모든 교차로는 서로 이동 가능함

		// roads -> {src, dst, weigth, color}
		// record -> 경로가 지나간 색상의 조합(부호 있음)
		// record[i]의 부호가 +인 경우, 최단거리에 해당하는 이동
		// record[i]의 부호가 -인 경우, 최단거리에 해당되지 않는 이동
		
		int[][] dist = new int[n + 1][n + 1]; // 모든 노드끼리의 최단 거리 정보(플로이드 워셜)
		int[][] colorMap = new int[n + 1][101]; // colorMap[i][j] = k, 교차로 i에서 j 색상의 도착지는 k

		// 플로이드 워셜
		for (int i = 1; i <= n; i++) {
			Arrays.fill(dist[i], 1_000_000);
			dist[i][i] = 0;
		}

		for (int[] road : roads) {
			int src = road[0];
			int dst = road[1];
			int weight = road[2];
			int color = road[3];
			dist[src][dst] = weight;
			dist[dst][src] = weight;
			colorMap[src][color] = dst;
			colorMap[dst][color] = src;
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		// 모든 {출발지, 목적지} 쌍에 대하여 경로 유효성 검증
		ArrayList<Pair> ansList = new ArrayList<>();
		for (int src = 1; src <= n; src++) {
			boolean flag = true;
			// 경로 유효성 검사 및 경로 추출
			int[] path = new int[record.length];
			int node = src;
			for (int i = 0; i < record.length; i++) {
				int nextNode = colorMap[node][Math.abs(record[i])];
				if (nextNode == 0) {
					flag = false;
					break;
				}
				path[i] = nextNode;
				node = nextNode;
			}
			if (!flag) continue; // 경로 완성 실패

            for (int dst = 1; dst <= n; dst++) {
				flag = true;
				int prev = src;
                for (int k = 0; k < record.length; k++) {
                    boolean isShortestPath = record[k] > 0; // 부호에 따른 최단거리 여부 (+: 최단거리, -: 비최단거리)
					int next = path[k];
					// 부호에 따라 조건 처리
					if (isShortestPath) {
						// 최단거리 조건: prev->next->dst는 최단거리여야 함
						if (dist[prev][dst] < dist[prev][next] + dist[next][dst]) {
							flag = false;
							break;
						}
					} else {
						// 비최단거리 조건: dist[i][j]는 최단거리가 아니어야 함
						if (dist[prev][dst] == dist[prev][next] + dist[next][dst]) {
							flag = false;
							break;
						}
					}

					prev = next;
                }
				if (flag) {
					ansList.add(new Pair(src, dst));
				}
            }
        }

		Collections.sort(ansList);
		int[][] result = new int[ansList.size()][2];

		for (int i = 0; i < ansList.size(); i++) {
			result[i][0] = ansList.get(i).start;
			result[i][1] = ansList.get(i).end;
		}

		return result;
	}
}