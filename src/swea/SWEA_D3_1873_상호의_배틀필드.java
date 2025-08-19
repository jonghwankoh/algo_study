package swea;

import java.util.*;
import java.io.*;

class SWEA_D3_1873_상호의_배틀필드 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static Map<Character, Integer> dirMap = new HashMap<>();
    static {
        dirMap.put('^', 0);
        dirMap.put('v', 1);
        dirMap.put('<',2);
        dirMap.put('>', 3);

        dirMap.put('U', 0);
        dirMap.put('D', 1);
        dirMap.put('L', 2);
        dirMap.put('R',3);
    }

    static char[] dirMark = {'^', 'v', '<', '>'};

    static int H, W;
    static char[][] gameMap;

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

                    // if is tank
                    if (dirMap.containsKey(c)) {
                        curY = i;
                        curX = j;
                        curDir = dirMap.get(c);
                        gameMap[i][j] = '.';
                    }
                }
            }

            int N = Integer.parseInt(br.readLine());
            String cmd = br.readLine();

            // main
            for (int i = 0; i < N; i++) {
                char c = cmd.charAt(i);
                if (dirMap.containsKey(c)) {
                    move(dirMap.get(c));
                } else {
                    shoot();
                }
            }

            sb.append("#" + test_case + ' ');
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(i == curY && j == curX ? dirMark[curDir] : gameMap[i][j]);
                }
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }
    
    static void move(int type) {
        curDir = type;
        int ny = curY + dy[curDir];
        int nx = curX + dx[curDir];
        if (ny >= 0 && ny < H && nx >= 0 && nx < W && gameMap[ny][nx] == '.') {
            curY = ny;
            curX = nx;
        }
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