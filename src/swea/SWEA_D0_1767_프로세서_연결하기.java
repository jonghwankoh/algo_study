package swea;

import java.util.*;
import java.io.*;

class SWEA_D0_1767_프로세서_연결하기 {
    static class Point {
        final int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static boolean[][] arr;
    static ArrayList<Point> processorList;
    static ArrayList<Point> selectedList;
    static int maxCnt; // 최대가 되어야함
    static int minLength; // 가능한 최소로

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new boolean[N][N];

            processorList = new ArrayList<>();
            selectedList = new ArrayList<>();
            maxCnt = 0;
            minLength = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = st.nextToken().charAt(0) == '1';
                    if (arr[i][j] && i != 0 && j != 0 && i != N - 1 && j != N - 1) {
                        processorList.add(new Point(i, j));
                    }
                }
            }

            selectCore(0);
            sb.append("#").append(test_case).append(' ').append(minLength).append('\n');
        }
        System.out.println(sb);
    }

    static void selectCore(int n) {
        if (n == processorList.size()) {
            if (maxCnt > selectedList.size())
                return; // backtrack1
            installLine(0, 0);
            return;
        }

        selectCore(n + 1);
        selectedList.add(processorList.get(n));
        selectCore(n + 1);
        selectedList.remove(selectedList.size() - 1);
    }

    static void installLine(int n, int lengthSum) {
        if (n == selectedList.size()) {
            if (selectedList.size() > maxCnt) {
                minLength = lengthSum;
            } else { // 같은 경우
                minLength = Math.min(minLength, lengthSum);
            }
            // backtrack1에 의해 항상 같거나 큰 값으로 갱신
            maxCnt = selectedList.size();
            return;
        }

        Point curPoint = selectedList.get(n);
        for (int d = 0; d < 4; d++) {
            int x = curPoint.x;
            int y = curPoint.y;
            int length = 0;
            while (true) {
                x += dx[d];
                y += dy[d];
                length++;

                if (arr[x][y]) {
                    break;
                }

                if (x == 0 || y == 0 || x == N - 1 || y == N - 1) {
                    for (int i = 1; i <= length; i++) {
                        arr[curPoint.x + dx[d] * i][curPoint.y + dy[d] * i] = true;
                    }
                    installLine(n + 1, lengthSum + length);
                    for (int i = 1; i <= length; i++) {
                        arr[curPoint.x + dx[d] * i][curPoint.y + dy[d] * i] = false;
                    }
                    break;
                }
            }
        }
    }
}