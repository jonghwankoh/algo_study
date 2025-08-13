package bj.gold.l3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
// 접근 1: 이중 for문으로 dst-src의 부호가 일치하는 묶음을 그리디하게 탐색하여 처리
import java.util.StringTokenizer;

public class BJ_G3_02879_코딩은_예쁘게 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] diff = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			diff[i] -= Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			diff[i] += Integer.parseInt(st.nextToken());
		}

		int i = 0, ans = 0;
		while (i < N) {
			// 1. 초기값 설정
			int minAbs = Math.abs(diff[i]); // 증가 또는 감수시켜야할 탭 크기
			int lastIdx = N - 1; // i ~ lastIdx 범위에 대하여 작업
			if (minAbs == 0) { // 작업이 필요 없으면 다음 줄 탐색(없어도 논리적으로 돌아감)
				i++;
				continue;
			}

			// 2. 부호가 같은 영역을 추출하고 영역에서의 최소값 추출
			for (int j = i + 1; j < N; j++) {
				if (diff[i] * diff[j] > 0) {
					minAbs = Math.min(minAbs, Math.abs(diff[j]));
				} else {
					lastIdx = j - 1;
					break;
				}
			}

			// 3. 작업 수행
			ans += minAbs;
			int diffSign = sign(diff[i]); // 부호 추출 -1, 0, 1
			for (int k = i; k <= lastIdx; k++) {
				diff[k] -= diffSign * minAbs;
			}
			if (diff[i] != 0) { // 아직 덜됐으면 다시 순회
				continue;
			}
			i++;
		}
		System.out.println(ans);
	}

	static int sign(int n) {
		if (n > 0) return 1;
		else if (n < 0) return -1;
		else return 0;
	}
}
