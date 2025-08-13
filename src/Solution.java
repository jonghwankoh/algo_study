<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
=======
>>>>>>> f1ef2467fb38ffc559857edd626babd8c9a0366b
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
<<<<<<< HEAD
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
=======
    static int N, ans;
    static int[] graph;
	public static void main(String args[]) throws Exception
	{
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            graph = new int[N + 1]; // graph[i] -> 인덱스 i 이상의 재료 중 조합 불가능한 재료 집합
            for (int i = 1; i <= N; i++) {
                graph[i] = 1 << i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (b > a) {
                    graph[a] |= 1 << b;
                } else {
                    graph[b] |= 1 << a;
                }
            }
            ans = 0;
			combination(0, 0);
            sb.append("#").append(test_case).append(' ').append(ans).append('\n');
		}
        System.out.println(sb);
	}

    static void combination(int depth, int blackList) {
        if (depth == N) {
            ans++;
            return;
        }

        combination(depth + 1, blackList);
        if ((blackList & (1 << (depth + 1))) == 0) {
            combination(depth + 1, blackList | graph[depth + 1]);
        }

>>>>>>> f1ef2467fb38ffc559857edd626babd8c9a0366b
    }
}