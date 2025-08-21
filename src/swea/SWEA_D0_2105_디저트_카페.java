package swea;

import java.io.*;
import java.util.*;

class SWEA_D0_2105_디저트_카페 {
    static int N;
    static int[][] grid;
    static boolean[] visited;
    static ArrayList<Integer> tmpList;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            grid = new int[N][N];
            visited = new boolean[101];
            tmpList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = -1;
            for (int i = 2; i < N; i++) {
                for (int j = 1; j < N - 1; j++) {
                    int down = grid[i][j];
                    int left = grid[i-1][j-1];
                    int right = grid[i-1][j+1];
                    if (down == left || left == right || down == right) {
                        continue;
                    }
                    visited[down] = true;
                    visited[left] = true;
                    visited[right] = true;
                    dfs(i, j, 1, 1);
                    visited[down] = false;
                    visited[left] = false;
                    visited[right] = false;
                }
            }
            
            sb.append("#" + test_case + ' ' + ans + '\n');
        }
        System.out.println(sb);
    }

    static void dfs(int x, int y, int left, int right) {
        int leftX = x - left;
        int leftY = y - left;
        int rightX = x - right;
        int rightY = y + right;
        int upX = leftX + rightX - x;
        int upY = leftY + rightY - y;

        if (!inRange(upX, upY)) {
            return; // backtrack
        }

        checkAnswer(x, y, left, right);

        // restore tmpVisit
        for (int i : tmpList) {
            visited[i] = false;
        }
        tmpList.clear();

        // traverse downside route
        if (inRange(leftX - 1, leftY - 1) && !visited[grid[leftX - 1][leftY - 1]]){
            visited[grid[leftX - 1][leftY - 1]] = true;
            dfs(x, y, left + 1, right);
            visited[grid[leftX - 1][leftY - 1]] = false;
        }
        if (inRange(rightX - 1, rightY + 1) && !visited[grid[rightX - 1][rightY + 1]]){
            visited[grid[rightX - 1][rightY + 1]] = true;
            dfs(x, y, left, right + 1);
            visited[grid[rightX - 1][rightY + 1]] = false;
        }
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    static void checkAnswer(int x, int y, int left, int right) {
        // check possibility of upside route
        // check top point
        int next = grid[x - left - right][y - left + right];
        if (visited[next]) return;
        visited[next] = true;
        tmpList.add(next);
        
        // check <\>
        for (int i = 1; i < left; i++) {
            next = grid[x - right - i][y + right - i];
            if (visited[next]) return;
            visited[next] = true;
            tmpList.add(next);
        }
        // check </>
        for (int i = 1; i < right; i++) {
            next = grid[x - left - i][y - left + i];
            if (visited[next]) return;
            visited[next] = true;
            tmpList.add(next);
        }
        ans = Math.max(ans, (left + right) * 2);
    }
}