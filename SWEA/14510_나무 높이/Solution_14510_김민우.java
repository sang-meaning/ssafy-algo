import java.io.*;
import java.util.*;

public class Solution_14510_김민우 {

    static int T, N;
    static int[] trees;

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        T = Integer.parseInt(st.nextToken());
        
        for(int test_case = 1; test_case <= T; test_case++){
        	st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            
            trees = new int[N];
            
            int max = 0;
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
            	trees[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, trees[i]);
            }
            
            int even = 0;
            int odd = 0;
            
            for(int heights : trees){
            	int diff = max - heights;
                even += diff/2;
                odd += diff%2;
            }
            
            while(even > odd+1){
            	even--;
                odd += 2;
            }
            
            int answer = 0;
            
            if(odd > even)
                answer = 2*odd - 1;
            else
                answer = 2*even;
            
            System.out.printf("#%d %d\n", test_case, answer);
        
        }//test_case 끝
    
    }//main 끝
}