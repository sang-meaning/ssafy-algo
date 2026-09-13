import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = sc.nextInt();

            int[] arr = new int[n];
            int max = 0;

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
                max = Math.max(max, arr[i]);
            }

            int one = 0;
            int two = 0;

            for (int i = 0; i < n; i++) {
                int diff = max - arr[i];

                one += diff % 2;
                two += diff / 2;
            }

            int day = 0;

            while (true) {

                int oddDay = (day + 1) / 2;
                int evenDay = day / 2;

                int useTwo = Math.min(two, evenDay);

                int needOne = one + (two - useTwo) * 2;

                if (needOne <= oddDay) {
                    break;
                }

                day++;
            }

            System.out.println("#" + test_case + " " + day);
        }
    }
}