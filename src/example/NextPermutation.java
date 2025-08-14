package example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class nextPermutation
{
    public static void main(String args[]) throws Exception
    {
        System.setIn(new FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = Integer.parseInt(br.readLine());
            int[] ops = new int[N - 1];
            int[] nums = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int opsIdx = 0;
            for (int i = 0; i < 4; i++) {
                int cnt = Integer.parseInt(st.nextToken());
                for (int j = 0; j < cnt; j++) {
                    ops[opsIdx++] = i;
                }
            }
            int[] test = {1, 1, 2};
            do {
                System.out.println(Arrays.toString(test));
            } while(nextPermutation(test));
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(ops);
            Arrays.sort(nums);

            int maxVal = Integer.MIN_VALUE;
            int minVal = Integer.MAX_VALUE;
            do {
                do {
                    int result = nums[0];
                    for (int i = 1; i < N; i++) {
                        switch (ops[i - 1]) {
                            case 0:
                                result += nums[i];
                                break;
                            case 1:
                                result -= nums[i];
                                break;
                            case 2:
                                result *= nums[i];
                                break;
                            case 3:
                                result /= nums[i];
                                break;
                        }
                    }
                    maxVal = Math.max(maxVal, result);
                    minVal = Math.min(minVal, result);
                } while(nextPermutation(ops));
                reverse(ops, 0, ops.length - 1);
            } while (nextPermutation(nums));
            

            sb.append("#" + test_case + " " + (maxVal - minVal) + "\n");
            System.out.println(sb);
        }
        System.out.println(sb);
    }

    public static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while(i > 0 && arr[i - 1] >= arr[i]) i--;

        if (i == 0) return false;

        int j = arr.length - 1;
        while(arr[j] <= arr[i - 1]) j--;

        int tmp = arr[j];
        arr[j] = arr[i - 1];
        arr[i - 1] = tmp;

        reverse(arr, i, arr.length - 1);
        return true;
    }

    private static void reverse(int[] arr, int left, int right) {
        while (left < right) {
            int tmp2 = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp2;
            left++;
            right--;
        }
    }
}
