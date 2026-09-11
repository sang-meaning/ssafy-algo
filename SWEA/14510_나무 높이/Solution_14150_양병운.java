import java.util.*;
import java.io.*;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int[] trees = new int[N];
            int maxHeight = 0;
            for(int i=0; i<N; i++){
                trees[i] = sc.nextInt();
                maxHeight = Math.max(maxHeight, trees[i]);
            }
            //홀수 1, 짝수 2
            int one = 0;
            int two = 0;
            for(int height : trees){
                int diff = maxHeight - height;
                one += diff%2;
                two += diff/2;
            }
            while(two > one+1) {
                two--;
                one +=2;
            }
          	int result;
            if(one > two)result = one * 2-1;  //마지막이 1로 끝난 경우
            else result = two * 2; //마지막이 2로 끝난 경우
            
            System.out.println("#"+test_case+" "+result);
        }
    }
}