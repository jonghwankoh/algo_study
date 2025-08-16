package swea;

import java.util.*;
import java.io.*;

class SWEA_D4_1249_보급로 {
    static class Node implements Comparable<Node> {
        int x, y, time;

        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.time, o.time);
        }
    }

    static int N;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];
            boolean[][] visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = line.charAt(j) - '0';
                }
            }

            // dijkstra
            int ans = 0;
            PriorityQueue<Node> pq = new PriorityQueue<>();
            
            pq.add(new Node(0, 0, arr[0][0]));
            visited[0][0] = true;

            while (!pq.isEmpty()) {
                Node next = pq.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = next.x + dx[d];
                    int ny = next.y + dy[d];
                    int time = next.time;

                    if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                        pq.offer(new Node(nx, ny, time + arr[nx][ny]));
                        visited[nx][ny] = true;
                        if (nx == N - 1 && ny == N - 1) { // 어차피 도착할때 늘어나는 가중치는 같으므로 찾자마자 종료
                            ans = time + arr[nx][ny];
                            pq.clear();
                            break;
                        }
                    }
                }
            }

            sb.append(String.format("#%d %d\n", test_case, ans));
        }
        System.out.println(sb);
    }
}