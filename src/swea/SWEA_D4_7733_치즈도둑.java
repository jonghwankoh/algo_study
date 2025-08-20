package swea;

import java.io.*;
import java.util.*;

class SWEA_D4_7733_치즈도둑 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[][] cheese = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cheese[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int maxBlockNum = 1;
            for (int day = 1; day <= 100; day++) {
                Queue<int[]> bfs = new ArrayDeque<>();
                boolean[][] visited = new boolean[N][N];
                int cnt = 0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (!visited[i][j] && cheese[i][j] > day) {
                            cnt++;
                            bfs.offer(new int[]{i, j});
                            visited[i][j] = true;
                            while (!bfs.isEmpty()) {
                                int[] node = bfs.poll();
                                int x = node[0];
                                int y = node[1];
                                for (int d = 0; d < 4; d++) {
                                    int nx = x + dx[d];
                                    int ny = y + dy[d];
                                    if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && cheese[nx][ny] > day) {
                                        bfs.offer(new int[]{nx, ny});
                                        visited[nx][ny] = true;
                                    }
                                }
                            }
                        }
                    }
                }
                maxBlockNum = Math.max(maxBlockNum, cnt);
            }

            sb.append("#" + test_case + ' ' + maxBlockNum + '\n');
        }
        System.out.println(sb);
    }
}