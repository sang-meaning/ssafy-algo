import java.util.*;
import java.io.*;
class Solution {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
            long N = Long.parseLong(br.readLine());
            long cnt = 0;
            while( N > 2) {
                long root = (long) Math.sqrt(N);
                if(root * root == N) {
					N = root; 
                	cnt++;
                }else {
                    root++;
                    long nextSquare = root * root;
                    cnt += nextSquare - N;
                    cnt++;
                    N = root;
                }
            }
            System.out.println("#"+test_case+" "+cnt);
		}
	}
}