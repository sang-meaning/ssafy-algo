import java.util.*;

class Solution {
    public static void main(String[] args) {
        int N = 8;
        int[] A = new int[N + 1];
        int[] B = new int[N + 1];
        A[1] = 1;
        B[1] = 1;

        for(int i = 2; i <= N; i++) {
            A[i] = B[i - 1];
            B[i] = A[i - 1] + B[i - 1];
        }
        System.out.println(A[N] + B[N]);
    }
}