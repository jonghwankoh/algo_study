package swea;

import java.util.*;
import java.io.*;

class SWEA_D3_1873_상호의_배틀필드 {
    static int H, W;
    static char[][] gameMap;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static char[] dirMark = {'^', 'v', '<', '>'};
    static int curDir, curY, curX;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            // input & init
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            gameMap = new char[H][W];

            for (int i = 0; i < H; i++) {
                String input = br.readLine();
                for (int j = 0; j < W; j++) {
                    char c = input.charAt(j);
                    gameMap[i][j] = c;

                    boolean isTank = true;
                    if (c == '^') {
                        curDir = 0;
                    } else if (c == 'v') {
                        curDir = 1;
                    } else if (c == '<') {
                        curDir = 2;
                    } else if (c == '>') {
                        curDir = 3;
                    } else {
                        isTank = false;
                    }
                    if (isTank) {
                        curY = i;
                        curX = j;
                    }
                }
            }

            int N = Integer.parseInt(br.readLine());
            String cmd = br.readLine();

            // main
            for (int i = 0; i < N; i++) {
                char c = cmd.charAt(i);
                switch (c) {
                    case 'U':
                        move(0);
                        break;
                    case 'D':
                        move(1);
                        break;
                    case 'L':
                        move(2);
                        break;
                    case 'R':
                        move(3);
                        break;
                    case 'S':
                        shoot();
                        break;
                }
            }

            sb.append("#").append(test_case).append(' ');
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(gameMap[i][j]);
                }
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }
    
    static void move(int type) {
        curDir = type;
        int ny = curY + dy[type];
        int nx = curX + dx[type];
        if (ny >= 0 && ny < H && nx >= 0 && nx < W && gameMap[ny][nx] == '.') {
            gameMap[curY][curX] = '.';
            curY = ny;
            curX = nx;
        }
        gameMap[curY][curX] = dirMark[type];
    }

    static void shoot() {
        int ny = curY + dy[curDir];
        int nx = curX + dx[curDir];

        while (ny >= 0 && ny < H && nx >= 0 && nx < W) {
            if (gameMap[ny][nx] == '*') {
                gameMap[ny][nx] = '.';
                break;
            } else if(gameMap[ny][nx] == '#') {
                break;
            }
            ny += dy[curDir];
            nx += dx[curDir];
        }
    }
}