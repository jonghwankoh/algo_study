package swea;

import java.io.*;
import java.util.*;

class SWEA_D4_1227_미로2 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine()); // ignore
            char[][] grid = new char[100][100];
            boolean[][] visited = new boolean[100][100];
            int startX = 0, startY = 0;
            int endX = 0, endY = 0;

            for (int i = 0; i < 100; i++) {
                String line = br.readLine();
                for (int j = 0; j < 100; j++) {
                    grid[i][j] = line.charAt(j);
                    if (grid[i][j] == '2') {
                        startX = i;
                        startY = j;
                    } else if (grid[i][j] == '3') {
                        endX = i;
                        endY = j;
                    }
                }
            }

            Queue<int[]> bfs = new ArrayDeque<>();

            bfs.offer(new int[]{startX, startY});
            visited[startX][startY] = true;
            
            boolean isPossible = false;
            while (!isPossible && !bfs.isEmpty()) {
                int[] node = bfs.poll();
                int x = node[0], y = node[1];
                
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d], ny = y + dy[d];
                    if (nx >= 0 && ny >= 0 && nx < 100 && ny < 100 && !visited[nx][ny] && grid[nx][ny] != '1') {
                        bfs.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        if (nx == endX && ny == endY){
                            isPossible = true;
                            break;
                        }
                    }
                }
            }

            sb.append("#" + test_case + " " + (isPossible ? 1 : 0) + '\n');
        }
        System.out.println(sb);
    }
}