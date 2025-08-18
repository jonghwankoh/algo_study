package bj.gold.l4;

import java.io.*;
import java.util.*;

public class BJ_G4_어려운_소인수분해 {
    // GPT한테 스포당함...
    private static final int MAX_VAL = 5_000_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] primeFactors = new ArrayList[n];
        int[] spf = new int[MAX_VAL + 1];
        for (int i = 0; i < n; i++) {
            primeFactors[i] = new ArrayList<>();
        }

        for (int i = 2; i <= MAX_VAL; i++) {
            if (spf[i] != 0) continue;
            spf[i] = i;
            for (int j = i * 2; j <= MAX_VAL; j += i) {
                if (spf[j] == 0) {
                    spf[j] = i;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            while (tmp > 1) {
                sb.append(spf[tmp]).append(' ');
                tmp /= spf[tmp];
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}