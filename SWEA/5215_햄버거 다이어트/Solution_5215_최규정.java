import java.util.*;

public class Solution {

    static int N;
    static int L;
    static int max;
    static int[] score;
    static int[] calorie;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            N = sc.nextInt();
            L = sc.nextInt();

            score = new int[N];
            calorie = new int[N];

            max = 0;

            for (int i = 0; i < N; i++) {
                score[i] = sc.nextInt();
                calorie[i] = sc.nextInt();
            }

            dfs(0, 0, 0);

            System.out.println("#" + test_case + " " + max);
        }
    }

    static void dfs(int index, int scoreSum, int calorieSum) {

        if (calorieSum > L) {
            return;
        }

        if (index == N) {
            max = Math.max(max, scoreSum);
            return;
        }

        dfs(index + 1, scoreSum + score[index], calorieSum + calorie[index]);

        dfs(index + 1, scoreSum, calorieSum);
    }
}