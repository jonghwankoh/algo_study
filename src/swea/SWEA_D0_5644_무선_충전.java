package swea;

import java.util.*;
import java.io.*;

class SWEA_D0_5644_무선_충전 {
    static int[] dy = {0, -1, 0, 1, 0};
    static int[] dx = {0, 0, 1, 0, -1};
    static class BatteryCharger {
        int idx, x, y, c, p;

        BatteryCharger(int idx, int x, int y, int c, int p) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.c = c;
            this.p = p;
        }
        boolean canUse(int x, int y) {
            return (Math.abs(this.x - x) + Math.abs(this.y - y)) <= c;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());

            int[] aMovement = new int[M];
            int[] bMovement = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                aMovement[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                bMovement[i] = Integer.parseInt(st.nextToken());
            }

            ArrayList<BatteryCharger> chargers = new ArrayList<>();
            chargers.add(new BatteryCharger(0, 5, 5, 20, 0)); // 선택을 하지 않는 경우를 위한 가상의 충전기

            for (int i = 1; i <= A; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                chargers.add(new BatteryCharger(i, x, y, c, p));
            }
            int y1, x1, y2, x2;
            y1 = x1 = 1;
            y2 = x2 = 10;
            int ans = 0;
            for (int t = 0; t <= M; t++) {
                int maxValue = 0;
                for (BatteryCharger chargerA : chargers) {
                    if (!chargerA.canUse(x1, y1)) continue;
                    for (BatteryCharger chargerB : chargers) {
                        if (chargerA.idx == chargerB.idx) continue;
                        if (!chargerB.canUse(x2, y2)) continue;
                        maxValue = Math.max(maxValue, chargerA.p + chargerB.p);
                    }
                }
                ans += maxValue;
                
                if (t == M) break;
                y1 += dy[aMovement[t]];
                x1 += dx[aMovement[t]];

                y2 += dy[bMovement[t]];
                x2 += dx[bMovement[t]];
            }

            sb.append("#").append(test_case).append(' ').append(ans).append('\n');
        }
        System.out.println(sb);
    }
}