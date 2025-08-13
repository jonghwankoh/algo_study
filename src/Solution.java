import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    private static int N;
    private static int[] cardList;
    private static int[] opsCnt;
    private static int maxAns;
    private static int minAns;

    public static void main(String args[]) throws Exception
    {
        System.setIn(new FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());
            opsCnt = new int[4];
            cardList = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 4; i++) {
                opsCnt[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                cardList[i] = Integer.parseInt(st.nextToken());
            }

            maxAns = Integer.MIN_VALUE;
            minAns = Integer.MAX_VALUE;
            solution(0, cardList[0]);
            sb.append("#" + test_case + " " + (maxAns - minAns) + "\n");
        }
        System.out.println(sb);


    }

    private static void solution(int size, int result) {
        if (size == N - 1) {
            maxAns = Math.max(maxAns, result);
            minAns = Math.min(minAns, result);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (opsCnt[i] > 0) {
                opsCnt[i]--;
                int next = result;
                switch (i) {
                    case 0:
                        next += cardList[size + 1];
                        break;
                    case 1:
                        next -= cardList[size + 1];
                        break;
                    case 2:
                        next *= cardList[size + 1];
                        break;
                    case 3:
                        next /= cardList[size + 1];
                        break;
                }
                solution(size + 1, next);
                opsCnt[i]++;
            }
        }
    }
}