package swea;

import java.util.*;
import java.io.*;

// 문제 요약: 1, 2, 1, 2, ...의 뺄셈 기회가 반복될 때, 일차원 배열을 모두 0으로 만들기 위한 최소 반복 횟수
/* 풀이(블로그 참고)
 * 1. 필요한 높이 총합을 구한다.
 * 
 * 2. 만약에 하나의 나무에서 모든 물이 필요한 이상적 경우를 가정하고, 걸리는 시간을 구해본다.
 * total / 3의 나머지가
 * 0인 경우 -> 1,2를 연달아 구성 가능 -> total // 3 * 2
 * 1인 경우 -> 마지막에 하루 더 물 주기 -> total // 3 * 2 + 1
 * 2인 경우 -> 마지막에 1 건너뛰고 2 물주기 -> total // 3 * 2 + 2
 * 일반화 -> day = total // 3 + total % 3
 * 
 * 3. 높이 총합을 이미 채웠으므로 이제 각각의 높이에 물을 할당할 수 있는지 확인한다.
 * 짝수 높이의 경우 
 * -> "사용 가능한 물의 총량 >= 높이"이면 항상 채울 수 있음. (증명 가능)
 * -> 이미 물의 총량이 충분하므로 짝수 높이의 나무는 고려하지 않아도 됨
 * 홀수 높이의 경우 -> 반드시 1개 이상의 홀수(1)가 필요. 1개 이상이면 채울 수 있음.
 * 
 * 4. 홀수 높이 나무의 개수가 필요한 1의 최소 개수이므로 
 * day >= oddCnt * 2 - 1이어야함.
 * 
 * 결론: 
 * day = max(total // 3 + total % 3, oddCnt * 2 - 1)
 */
class SWEA_D2_14510_나무_높이 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N];

            int maxHeight = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
        
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, arr[i]);
            }

            // convert heights to heightDiffs
            // get total
            // get oddCnt
            int total = 0, oddCnt = 0;
            for (int i = 0; i < N; i++) {
                arr[i] = maxHeight - arr[i];
                total += arr[i];
                if (arr[i] % 2 == 1) {
                    oddCnt++;
                }
            }
            
            // ideal day
            int day = total / 3 * 2 + total % 3;

            // consider odd height
            day = Math.max(day, oddCnt * 2 - 1);

            sb.append("#").append(test_case).append(' ').append(day).append('\n');
        }
        System.out.println(sb);
    }
}