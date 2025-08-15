package swea;

import java.util.*;
import java.io.*;

class SWEA_D4_1861_정사각형_방 {
    static int N;
    static int[][] arr;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            visited = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int ansRoom = Integer.MAX_VALUE;
            int ansCnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int cnt = dfs(i, j);
                    if (cnt > ansCnt) {
                        ansRoom = arr[i][j];
                        ansCnt = Math.max(ansCnt, cnt);
                    } else if (cnt == ansCnt) {
                        ansRoom = Math.min(ansRoom, arr[i][j]);
                    }
                }
            }

            sb.append(String.format("#%d %d %d\n", test_case, ansRoom, ansCnt));
        }
        System.out.println(sb);
    }

    static int dfs(int x, int y) {
        // case 1: 이미 방문
        if (visited[x][y] != 0) {
            return visited[x][y];
        }
        // case 2: 다른 방 가능
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if (nx >= 0 && ny >= 0 && nx < N && ny < N && arr[x][y] + 1 == arr[nx][ny]) {
                int cnt = dfs(nx, ny) + 1;
                visited[x][y] = cnt;
                return cnt;
            }
        }
        // case 3: 갈 곳 없음
        visited[x][y] = 1;
        return 1;
    }
}