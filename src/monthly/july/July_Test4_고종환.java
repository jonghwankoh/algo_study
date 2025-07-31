package monthly.july;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class July_Test4_고종환 {
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class Pair {
		int x, y;
		
		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/monthly/july/Test4.txt"));
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int R = sc.nextInt();
		
		int[][] arr = new int[N][M];
		int[][] result = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		for (int level = 0; level < Math.min(M, N) / 2; level++) {
			int height = N - level * 2;
			int width = M - level * 2;
			
			int i = level, j = level, dir = -1;
			do {
				Pair target = getPosition(height, width, (getIdx(height, width, i - level, j - level) + R) % ((height + width - 2) * 2));
				result[level + target.x][level + target.y] = arr[i][j];
				if (i == level && j == level) {
					dir = 0;
				} else if (i == level + height - 1 && j == level) {
					dir = 1;
				} else if (i == level + height - 1 && j == level + width - 1) {
					dir = 2;
				} else if (i == level && j == level + width - 1) {
					dir = 3;
				}

				i += dx[dir];
				j += dy[dir];
			} while(i != level || j != level);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	private static int getIdx(int N, int M, int x, int y) {
		if (y == 0) {
			return x;
		} else if (x == N - 1) {
			return N - 1 + y;
		} else if (y == M - 1) {
			return 2 * N + M - 3 - x;
		} else {
			return 2 * N + 2 * M - 4 - y;
		}
	}
	
	private static Pair getPosition(int N, int M, int idx) {
		if (idx <= N + M - 2) {
			if (idx <= N - 1) {
				return new Pair(idx, 0);
			} else {
				return new Pair(N - 1, idx - (N - 1));
			}
		} else {
			if (idx <= 2 * N + M - 3) {
				return new Pair(2*N + M - 3 - idx, M - 1);
			} else {
				return new Pair(0, 2*N + 2*M - 4 - idx);
			}
		}
	}

}
