package bj.gold.l2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 접근: 위의 빵집은 오른쪽 위를 그리디하게 탐색

public class BJ_G2_빵집 {
	static int ans, r, c;
	static boolean[][] isBuilding;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		isBuilding = new boolean[r][c]; // visited 배열이기도 함

		for (int i = 0; i < r; i++) {
			String line = br.readLine();
			for (int j = 0; j < c; j++) {
				isBuilding[i][j] = line.charAt(j) == 'x';
			}
		}

		ans = 0;
		for (int i = 0; i < r; i++) {
			dfs(i, 0);
		}
		System.out.println(ans);
	}

	static boolean dfs(int row, int col) {
		if(col == c - 1) {
			ans++;
			return true;
		}
		for (int dr = -1; dr <= 1; dr++) {
			int nr = row + dr, nc = col + 1;
			if (nr >= 0 && nr < r && !isBuilding[nr][nc]) {
				isBuilding[nr][nc] = true;
				if (dfs(nr, nc)) return true;
			}
		}
		return false;
	}
}
