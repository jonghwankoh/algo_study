package swea;

import java.util.*;
import java.io.*;

class SWEA_D0_4012_요리사 {
    static int N;
    static int[][] synergy;
    static int ans;
    static int[] aList, bList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            synergy = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if (i < j)
                        synergy[i][j] += tmp;
                    else
                        synergy[j][i] += tmp;
                }
            }

            ans = Integer.MAX_VALUE;
            aList = new int[N];
            bList = new int[N];

            dfs(0, 0, 0, 0, 0);

            sb.append("#").append(test_case).append(' ').append(ans).append('\n');
        }
        System.out.println(sb);
    }

    static void dfs(int idx, int aTotal, int bTotal, int aSize, int bSize) {
        if (idx == N) {
            ans = Math.min(ans, Math.abs(aTotal - bTotal));
            return;
        }

        int aSum = 0;
        for(int i = 0; i < aSize; i++) {
            aSum += synergy[aList[i]][idx];
        }
        aList[aSize] = idx;
        dfs(idx + 1, aTotal + aSum, bTotal, aSize + 1, bSize);

        int bSum = 0;
        for(int i = 0; i < bSize; i++) {
            bSum += synergy[bList[i]][idx];
        }
        bList[bSize] = idx;
        dfs(idx + 1, aTotal, bTotal + bSum, aSize, bSize + 1);
    }
}