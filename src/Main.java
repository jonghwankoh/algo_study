/*
 * BJ S1 1325. 효율적인 해킹
 * 메모:
 * visit 체크를 큐에 넣는 순간 해야 시간초과나지 않는다.
 * 
**/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static ArrayList<Integer>[] graph;
	private static int[] visited;
	private static Queue<Integer> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		graph = new ArrayList[n+1];
		for (int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		visited = new int[n + 1];

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[b].add(a);
		}

		int[] virusCnt = new int[n + 1];
		int maxCnt = 0;
		for (int i = 1; i <= n; i++) {
			int cnt = bfs(i);
			virusCnt[i] = cnt;
			maxCnt = Math.max(maxCnt, cnt);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			if (virusCnt[i] == maxCnt) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb);
	}

	private static int bfs(int start) {
		int cnt = 1;

		queue.offer(start);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
            if (visited[cur] == start) {
                continue;
            }
            visited[cur] = start;
            cnt++;
			for (int next : graph[cur]) {
				if (visited[next] != start) {
					queue.offer(next);
				}
			}
		}

		return cnt;
	}
}