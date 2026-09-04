import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solution {

    static int n;
    static int m;
    static int[][] array;
    static List<Integer> temp = new ArrayList<>();
    static int min;

    static void backtracking(int start) {
        if (temp.size() == m) {
            int sum1 = 0;
            int sum2 = 0;
            int diff;
            Set<Integer> set = new HashSet<>(temp);
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length; j++) {
                    if(set.contains(i) && set.contains(j)) {
                        sum1 += array[i][j];
                    }else if(!set.contains(i) && !set.contains(j)){
                        sum2 += array[i][j];
                    }
                }
            }
            diff = sum1 > sum2 ? sum1-sum2 : sum2 - sum1;
            min = min >= diff ? diff : min;
            return;
        }

        for(int i=start; i<n; i++) {
            temp.add(i);
            backtracking(i + 1);
            temp.remove(temp.size() - 1);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {
            n =sc.nextInt();
            m = n /2;
            array = new int[n][n];

            temp.clear();
            min = Integer.MAX_VALUE;

            for(int j=0; j<n;j++) {
                for(int k=0; k<n; k++) {
                    array[j][k] = sc.nextInt();
                }
            }
            backtracking(0);

            System.out.println("#"+tc+" "+min);
        }
    }
} 