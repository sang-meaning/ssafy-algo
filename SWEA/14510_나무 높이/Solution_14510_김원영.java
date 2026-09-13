import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {

            int N = sc.nextInt();
            int[] tree = new int[N];
            int max = 0;

            for(int i=0; i<N; i++) {
                tree[i] = sc.nextInt();
                max = Math.max(max, tree[i]);
            }

            int one = 0;
            int two = 0;

            for(int i=0; i<N; i++) {
                int diff = max - tree[i];

                two += diff / 2;
                one += diff % 2;
            }

            while(two > one + 1) {
                two--;
                one += 2;
            }
            int answer;

            if(one > two) {
                answer = one * 2 - 1;
            }else {
                answer = two * 2;
            }
            System.out.println("#" + tc + " " + answer);
        }
        sc.close();
    }
}