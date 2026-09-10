import java.util.Scanner;

public class Solution {
    static int employeeCount, shelfHeight, answer;
    static int[] heights;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            employeeCount = sc.nextInt();
            shelfHeight = sc.nextInt();

            heights = new int[employeeCount];
            int totalHeight = 0;

            for (int i = 0; i < employeeCount; i++) {
                heights[i] = sc.nextInt();
                totalHeight += heights[i];
            }

            answer = totalHeight - shelfHeight;
            dfs(0, 0);

            System.out.println("#" + tc + " " + answer);
        }

        sc.close();
    }

    static void dfs(int index, int heightSum) {

        if (answer == 0) {
            return;
        }
        
        if (heightSum >= shelfHeight) {
            answer = Math.min(answer, heightSum - shelfHeight);
            return;
        }

        if (index == employeeCount) {
            return;
        }

        dfs(index + 1, heightSum + heights[index]);
        
        dfs(index + 1, heightSum);
    }
}