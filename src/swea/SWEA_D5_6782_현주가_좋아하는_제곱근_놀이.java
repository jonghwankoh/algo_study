package swea;

import java.io.*;

class SWEA_D5_6782_현주가_좋아하는_제곱근_놀이 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            long N = Long.parseLong(br.readLine());
            
            int cnt = 0;
            while (N > 2) {
                long sqrtN = (long) Math.sqrt(N);
                if (sqrtN * sqrtN == N) {
                    cnt++;
                    N = sqrtN;
                } else {
                    long nextN = sqrtN + 1;
                    cnt += nextN * nextN - N + 1;
                    N = nextN;
                }
            }
            sb.append("#").append(test_case).append(' ').append(cnt).append('\n');
        }
        System.out.println(sb);
    }
}