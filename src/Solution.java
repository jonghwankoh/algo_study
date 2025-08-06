import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        // System.setIn(new FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;

        StringBuilder sb = new StringBuilder();
        int[][] input = new int[100][100];
        int dst = 0;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            br.readLine(); // ignore first line
            ArrayList<Integer> ladderList = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    input[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < 100; i++) {
                if (input[0][i] == 1) {
                    ladderList.add(i);
                    if (input[99][i] == 2) {
                        dst = i;
                    }
                }
            }

            for (int i = 0; i < ladderList.size(); i++) {
                int cur = i;
                for (int j = 1; j < 99; j++) {
                    int curPos = ladderList.get(cur);
                    if (cur > 0 && input[j][curPos - 1] == 1) {
                        cur--;
                    } else if (cur < ladderList.size() - 1 && input[j][curPos + 1] == 1) {
                        cur++;
                    }
                }
                if (ladderList.get(cur) == dst) {
                    sb.append("#" + test_case + " " + ladderList.get(i) + "\n");
                    break;
                }
            }
        }
        System.out.println(sb);
    }
}