package swea;

import java.util.Scanner;

class SWEA_D3_2805_농작물_수확하기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = Integer.parseInt(sc.nextLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(sc.nextLine());

            int center = N / 2;
            int ans = 0;
            for (int i = 0; i < N; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < N; j++) {
                    int profit = line.charAt(j) - '0';
                    if (Math.abs(i - center) + Math.abs(j - center) <= center) {
                        ans += profit;
                    }
                }
            }

            sb.append("#").append(test_case).append(' ').append(ans).append('\n');
        }
        System.out.println(sb);
        sc.close();
    }
}