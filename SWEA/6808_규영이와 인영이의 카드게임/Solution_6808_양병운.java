import java.util.*;
import java.io.*;

class Solution {
    static int[] gyu, opposite;
    static boolean[] visitedOppo;
    static int totalWin;
    static int totalLose;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            totalWin = 0;
            totalLose = 0;
            gyu = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            boolean[] used = new boolean[19];
            for(int card : gyu) used[card] = true;
            opposite = new int[9];
            visitedOppo = new boolean[9];
            int idx = 0;
            for(int i = 1; i <= 18; i++) {
                if(!used[i]) opposite[idx++] = i;
            }
            dfs(0, 0, 0);
            System.out.println("#" + test_case + " " + totalWin + " " + totalLose);
        }
    }

    public static void dfs(int win, int lose, int depth) {
        if(depth == 9) {
            if(win > lose) totalWin++;
            else if(win < lose) totalLose++;
            return;
        }

        for(int i = 0; i < 9; i++) {
            if(visitedOppo[i]) continue;
            visitedOppo[i] = true;
            int gyuCard = gyu[depth];
            int oppoCard = opposite[i];
            if(gyuCard > oppoCard) dfs(win + gyuCard + oppoCard,lose,depth + 1);
            else dfs(win,lose + gyuCard + oppoCard,depth + 1);
            visitedOppo[i] = false;
        }
    }
}