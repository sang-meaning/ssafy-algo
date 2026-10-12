import java.util.Scanner;

class Solution {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();

            int maxi = 0;
            int[] tree = new int[N];
            for (int i=0; i<N; i++) {
                tree[i] = sc.nextInt();
                maxi = Math.max(tree[i], maxi);
            }

            int one = 0, two = 0, day = 0;
            for (int i=0; i<N; i++) {
                int diff = maxi - tree[i];
                one += diff%2;
                two += diff/2;
            }

            while (two - one >= 2) {
                one+=2;
                two--;
            }

            if (one > two) {
                day += one*2 - 1;
            }
            else {
                day += two*2;
            }
            
            System.out.println("#" + test_case + " " + day);
        }

        sc.close();
    }
}