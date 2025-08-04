package bj.gold.l1;

import java.io.*;
import java.util.*;

public class BJ_G1_12100_2048_Easy {
	static int N, ans = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				ans = Math.max(ans, board[i][j]);
			}
		}
		recursive(board, 0);
		System.out.println(ans);
	}

	private static void recursive(int[][] board, int moveCnt) {
		if (moveCnt == 5)
			return;

		for (int type = 0; type < 4; type++) {
			int[][] newBoard = flipBoard(board, type);
			for (int i = 0; i < N; i++) {
				Queue<Integer> queue = new ArrayDeque<>();
				for (int j = 0; j < N; j++) {
					if (newBoard[i][j] != 0)
						queue.offer(newBoard[i][j]);
				}
				int idx = 0;
				while (!queue.isEmpty()) {
					int node = queue.poll();
					if (!queue.isEmpty() && node == queue.peek()) {
						queue.poll();
						node *= 2;
					}
					newBoard[i][idx++] = node;
					ans = Math.max(ans, node);
				}
				for (int j = idx; j < N; j++) {
					newBoard[i][j] = 0;
				}
			}
			recursive(newBoard, moveCnt + 1);
		}
	}

	private static int[][] flipBoard(int[][] src, int type) {
		int[][] dst = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (type == 0) {
					dst[i][j] = src[i][j];
				} else if (type == 1) {
					dst[i][j] = src[i][N-1-j]; // right
				} else if (type == 2) {
					dst[i][j] = src[j][i]; // down
				} else if (type == 3) {
					dst[i][j] = src[N-1-j][i]; // up
				}
			}
		}
		return dst;
	}
}