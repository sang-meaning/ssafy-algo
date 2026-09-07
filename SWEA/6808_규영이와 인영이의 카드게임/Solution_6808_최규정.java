import java.util.*;

public class Solution {

    static int win_cnt;
    static int loss_cnt;

    static int[] mine;
    static int[] yours;

    static boolean[] isSelected;
    static int[] numbers;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            boolean[] check = new boolean[19];

            win_cnt = 0;
            loss_cnt = 0;
            mine = new int[9];
            yours = new int[9];

            isSelected = new boolean[9];
            numbers = new int[9];

            for (int i = 0; i < 9; i++) {
                mine[i] = sc.nextInt();
                check[mine[i]] = true;
            }

            int n = 0;

            for (int i = 1; i <= 18; i++) {
                if (!check[i]) {
                    yours[n] = i;
                    n++;
                }
            }

            permutation(0);

            System.out.println("#" + test_case + " " + win_cnt + " " + loss_cnt);
            
        }
    }

    static void permutation(int cnt) {


        if (cnt == 9) {

            int mine_score = 0;
            int your_score = 0;


            for (int i = 0; i < 9; i++) {
                if (mine[i] > numbers[i]) {
                    mine_score = mine_score + mine[i] + numbers[i];
                }else {
                    your_score = your_score + mine[i] + numbers[i];
                }
            }

            if (mine_score > your_score) {
                win_cnt++;
            }else if (mine_score < your_score) {
                loss_cnt++;
            }

            return;
        }

        
        for (int i = 0; i < 9; i++) {
            if (isSelected[i]) {
                continue;
            }

            isSelected[i] = true;
            numbers[cnt] = yours[i];
           
            permutation(cnt + 1);

            isSelected[i] = false;
        }
    }
}