package swea_b.OTT;

import java.util.Scanner;

class Solution
{   
    private static UserSolution usersolution = new UserSolution();

    private final static int CMD_INIT       = 100;
    private final static int CMD_ADD        = 200;
    private final static int CMD_ERASE      = 300;
    private final static int CMD_WATCH      = 400;
    private final static int CMD_SUGGEST    = 500;

    private static long[] commandTimes = new long[600]; // Array to store cumulative times for each command type
    
    public final static class RESULT
    {
        int cnt;
        int[] IDs = new int[5];
        
        RESULT()
        {
            cnt = -1;
        }
    }
    
    private static boolean run(Scanner sc) throws Exception
    {
        int Q, N;
        int mID, mGenre, mTotal, mRating, uID;
        
        int ret = -1, cnt, ans;

        RESULT res;

        Q = sc.nextInt();

        boolean okay = false;

        for (int q = 0; q < Q; ++q)
        {
            long startTime = System.nanoTime(); // Start timing
            int cmd;
            cmd = sc.nextInt();

            switch(cmd)
            {
            case CMD_INIT:
                N = sc.nextInt();
                usersolution.init(N);
                okay = true;
                break;
            case CMD_ADD:
                mID = sc.nextInt();
                mGenre = sc.nextInt();
                mTotal = sc.nextInt();
                ret = usersolution.add(mID, mGenre, mTotal);
                ans = sc.nextInt();
                if (ret != ans){
                    okay = false;
                    System.out.println("Add Failed");
                }
                break;
            case CMD_ERASE:
                mID = sc.nextInt();
                ret = usersolution.erase(mID);
                ans = sc.nextInt();
                if (ret != ans){
                    okay = false;
                    System.out.println("Erase Failed");
                }
                break;
            case CMD_WATCH:
                uID = sc.nextInt();
                mID = sc.nextInt();
                mRating = sc.nextInt();
                ret = usersolution.watch(uID, mID, mRating);
                ans = sc.nextInt();
                if (ret != ans){
                    okay = false;
                    System.out.println("**" + q + " WATCH Failed");
                    System.out.println("actual: " + ret);
                    System.out.println("expected: " + ans);
                    System.out.println("===============");
                }
                break;
            case CMD_SUGGEST:
                uID = sc.nextInt();
                res = usersolution.suggest(uID);
                cnt = sc.nextInt();
                if (res.cnt != cnt){
                    okay = false;
                }
                int[] testIds = new int[cnt];
                for (int i = 0; i < cnt; ++i)
                {
                    ans = sc.nextInt();
                    testIds[i] = ans;
                    if (res.IDs[i] != ans){
                        okay = false;
                    }
                }
                if (!okay) {
                    System.out.println("**" + q + " SUGGEST Failed");
                    System.out.println("Actual: ");
                    for (int i = 0; i < res.cnt; ++i){
                        System.out.print(res.IDs[i]+ " ");
                    }
                    System.out.println("Expected: ");
                    for (int i = 0; i < cnt; ++i){
                        System.out.print(testIds[i] + " ");
                    }
                    System.out.println("===============");
                }
                break;
            default:
                okay = false;
                break;
            }

            long endTime = System.nanoTime(); // End timing
            commandTimes[cmd] += (endTime - startTime); // Accumulate time for the command
        }

        return okay;
    }

    public static void main(String[] args) throws Exception
    {
        System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        
        int TC = sc.nextInt();
        int MARK = sc.nextInt();
        
        long startTime = System.nanoTime();

        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            int score = run(sc) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        long endTime = System.nanoTime();

        System.out.println("Total time: " + (endTime - startTime) / 10e6 + "ms");

        // Find the command with the highest cumulative time
        int maxCmd = -1;
        long maxTime = 0;
        for (int i = 100; i <= 500; i += 100) {
            System.out.println("cmd: " + i + " (" + commandTimes[i] / 10e6 + " ms)");
            if (commandTimes[i] > maxTime) {
                maxTime = commandTimes[i];
                maxCmd = i;
            }
        }

        System.out.println("Command with the highest cumulative time: " + maxCmd + " (" + maxTime / 10e6 + " ns)");

        sc.close();
    }
}