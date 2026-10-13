import java.io.*;
import java.util.*;

class Solution {
    static int daily, monthly, threeMonth, yearly;
    static int minCost;
    static int[] schedule;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            daily = Integer.parseInt(st.nextToken());
            monthly = Integer.parseInt(st.nextToken());
            threeMonth = Integer.parseInt(st.nextToken());
            yearly = Integer.parseInt(st.nextToken());

            schedule = new int[12];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 12; i++) {
                schedule[i] = Integer.parseInt(st.nextToken());
            }

            minCost = yearly;
            calculate(0, 0);

            result.append("#").append(tc)
                  .append(" ").append(minCost).append("\n");
        }

        System.out.print(result);
    }

    static void calculate(int month, int cost) {
        if (cost >= minCost) return;

        if (month >= 12) {
            minCost = cost;
            return;
        }

        if (schedule[month] == 0) {
            calculate(month + 1, cost);
        } else {
            int oneMonthCost = Math.min(
                schedule[month] * daily, monthly
            );

            calculate(month + 1, cost + oneMonthCost);
            calculate(month + 3, cost + threeMonth);
        }
    }
}