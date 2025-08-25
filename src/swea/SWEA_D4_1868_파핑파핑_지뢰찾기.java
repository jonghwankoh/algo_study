package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution
{
    static final int[] dy = {-1, -1,  0,  1,  1,  1,  0, -1};
    static final int[] dx = { 0,  1,  1,  1,  0, -1, -1, -1};

    public static void main(String args[]) throws Exception
    {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = Integer.parseInt(br.readLine());
            char[][] map = new char[n][n];
            boolean[][] visited = new boolean[n][n];
            int numCnt = 0;
            int clickCnt = 0;
            for(int i = 0; i < n; i++) {
                String line = br.readLine();
                for(int j = 0; j < n; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(map[i][j] != '.') continue;
                    for(int k = 0; k < 8; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if (0 > ny || ny >= n || 0 > nx || nx >= n || map[ny][nx] != '*') continue;

                        map[i][j] = '1'; // mark as number
                        numCnt++;
                        break;
                    }
                }
            }

            Queue<int[]> bfs = new LinkedList<>();
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(visited[i][j] || map[i][j] != '.') continue;
                    clickCnt++;
                    bfs.offer(new int[]{i, j});
                    visited[i][j] = true;

                    while(!bfs.isEmpty()) {
                        int[] next = bfs.poll();
                        int y = next[0];
                        int x = next[1];

                        for(int k = 0; k < 8; k++) {
                            int ny = y + dy[k];
                            int nx = x + dx[k];
                            if (0 > ny || ny >= n || 0 > nx || nx >= n || map[ny][nx] == '*' || visited[ny][nx]) continue;

                            if(map[ny][nx] == '.') {
                                bfs.offer(new int[]{ny, nx});
                            } else { // map[ny][nx] must be '1';
                                numCnt--;
                            }
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
            System.out.println("#" + test_case + " " + (clickCnt + numCnt));
        }
    }
}