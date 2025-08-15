package swea;

import java.util.*;
import java.io.*;

// 문제 요약: 1, 2, 1, 2, ...의 뺄셈 기회가 반복될 때, 일차원 배열을 모두 0으로 만들기 위한 최소 반복 횟수
// 풀이
// 1. 각 나무에서 남은 높이를 3으로 나눈다.
// 몫의 경우
// day += 몫 * 2
// 나머지의 경우
// 0 -> 아무것도 안함
// 1 -> 추가로 필요한 홀수 개수 oddCnt++
// 2 -> 추가로 필요한 짝수 개수 evenCnt++

// 2. 분기 처리
// oddCnt == evenCnt + 1 -> day += oddCnt * 2 - 1
// oddCnt == evenCnt이면  -> day += oddCnt * 2
// oddCnt > evenCnt + 1 - >
class SWEA_D2_14510_나무_높이 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N];



            int day = 0;

            sb.append("#").append(test_case).append(' ').append("?").append('\n');
        }
        System.out.println(sb);
    }
}