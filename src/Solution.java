import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
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

    }
}