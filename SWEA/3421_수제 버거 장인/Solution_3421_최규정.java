import java.util.*;

public class Solution {
    static int ingredientCount;
    static boolean[][] incompatible;
    static boolean[] selected;
    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            ingredientCount = sc.nextInt();
            int pairCount = sc.nextInt();

            incompatible = new boolean[ingredientCount + 1][ingredientCount + 1];
            selected = new boolean[ingredientCount + 1];
            answer = 0;

            for (int i = 0; i < pairCount; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                incompatible[a][b] = true;
                incompatible[b][a] = true;
            }

            dfs(1);

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }

    static void dfs(int ingredient) {
        if (ingredient > ingredientCount) {
            answer++;
            return;
        }

        dfs(ingredient + 1);

        for (int i = 1; i < ingredient; i++) {
            if (selected[i] && incompatible[ingredient][i]) {
                return;
            }
        }

        selected[ingredient] = true;
        dfs(ingredient + 1);

        selected[ingredient] = false;
    }
}