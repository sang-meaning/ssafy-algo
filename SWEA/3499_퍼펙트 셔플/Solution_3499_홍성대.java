import java.util.Scanner;
import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            Queue<String> q1 = new ArrayDeque<>();
            Queue<String> q2 = new ArrayDeque<>();
            
            int half = (n + 1) / 2;
            
            for (int i = 0; i < half; i++) {
                q1.add(sc.next());
            }
            for (int i = half; i < n; i++) {
                q2.add(sc.next());
            }
            
            System.out.print("#" + test_case);
            
            while (!q1.isEmpty() || !q2.isEmpty()) {
                if (!q1.isEmpty()) {
                    System.out.print(" " + q1.poll());
                }
                if (!q2.isEmpty()) {
                    System.out.print(" " + q2.poll());
                }
            }
            System.out.println();
        }
        
        sc.close();
    }
}