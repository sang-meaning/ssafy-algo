import java.io.*;
import java.util.*;
 
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            int[] trees = new int[N];
            int maxHeight = 0;
 
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, trees[i]);
            }
 
            int odd = 0; 
            int even = 0; 
 
            for (int i = 0; i < N; i++) {
                int diff = maxHeight - trees[i];
                even += diff / 2;
                odd += diff % 2;
            }
 
            while (even > odd + 1) {
                even--;
                odd += 2;
            }
 
            int ans = 0;
            if (odd > even) {
                ans = odd * 2 - 1;
            } else {
                ans = even * 2;
            }
 
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
 
        System.out.print(sb);
    }
}