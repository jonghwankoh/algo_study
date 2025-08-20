package swea;

import java.io.*;
import java.util.*;

class SWEA_D0_1238_Contact {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            boolean[][] graph = new boolean[101][101];
            boolean[] visited = new boolean[101];
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from][to] = true;
            }

            Queue<Integer> bfs = new ArrayDeque<>();

            bfs.offer(start);
            visited[start] = true;
            int lastIdx = start;

            while (!bfs.isEmpty()) {
                int size = bfs.size();
                lastIdx = 0;
                for (int i = 0; i < size; i++) {
                    int node = bfs.poll();
                    for (int j = 1; j <= 100; j++) {
                        if (!visited[j] && graph[node][j]) {
                            bfs.offer(j);
                            visited[j] = true;
                        }
                    }
                    lastIdx = Math.max(lastIdx, node);
                }
            }

            sb.append("#" + test_case + ' ' + lastIdx + '\n');
        }
        System.out.println(sb);
    }
}