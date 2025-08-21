package swea;

import java.io.*;
import java.util.*;

class SWEA_D6_1267_작업순서 {
    static ArrayList<Integer>[] reversedGraph;
    static ArrayList<Integer> taskList;
    static boolean[] visited;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            reversedGraph = new ArrayList[V + 1];
            visited = new boolean[V + 1];
            for (int i = 1; i <= V; i++) {
                reversedGraph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                reversedGraph[to].add(from);
            }
            
            taskList = new ArrayList<>();
            for (int i = 1; i <= V; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }

            StringBuilder ans = new StringBuilder();
            for (int task : taskList) {
                ans.append(task).append(' ');
            }
            sb.append("#" + tc + ' ' + ans + '\n');
        }
        System.out.println(sb);
    }

    static void dfs(int dst) {
        for (int preCondition : reversedGraph[dst]) {
            if (!visited[preCondition]) {
                dfs(preCondition);
            }
        }

        visited[dst] = true;
        taskList.add(dst);
        return;
    }
}