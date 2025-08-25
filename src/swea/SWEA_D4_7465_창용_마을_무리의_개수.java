package swea;

import java.io.*;
import java.util.*;

class SWEA_D4_7465_창용_마을_무리의_개수 {
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];
            int relationNum = N;
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                if (union(a, b)) relationNum--;
            }

            sb.append("#" + tc + ' ' + relationNum + '\n');
        }
        System.out.println(sb);
    }

    static int find(int n) {
        while(parent[n] != n) n = parent[n];
        return n;
    }

    static boolean union(int a, int b) {
        int ap = find(a);
        int bp = find(b);
        if (ap == bp) return false;
        parent[bp] = ap;
        return true;
    }


}