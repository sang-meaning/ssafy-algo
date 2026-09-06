import java.util.*;
import java.io.*;
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int testcase = 1; testcase <= T; testcase++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
             
            int[] snacks = new int[N];
            int answer = -1;
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; i++){
                snacks[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(snacks);
            // 조합(2가지만 보면 됨)
            int left = 0;
            int right = N-1;
            while(left < right){
                int weight = snacks[left] + snacks[right];
                if(weight <= M) {
                    answer = Math.max(weight, answer);
                    left++;
                }
                else{
                    right--;
                }
            }
 
            System.out.println("#"+testcase + " " + answer);
        }
    }
}