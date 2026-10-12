import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T,N;
	static int[] arr;
	static int result;
	static int MAX;
	
    public static void main(String[] args) throws Exception{
    	T = Integer.parseInt(br.readLine());
    	
    	for(int tc=1;tc<=T;tc++) {
    		N = Integer.parseInt(br.readLine());
    		result = 0;
    		arr = new int[N];
    		MAX = 0;
    		
    		st = new StringTokenizer(br.readLine());
    		for(int i=0;i<N;i++) {
    			arr[i] = Integer.parseInt(st.nextToken());
    			MAX = Math.max(MAX, arr[i]);
    		}

    		int one_count=0;
    		int two_count=0;
    		
    		for(int i=0;i<N;i++) {
    			int diff = MAX-arr[i];
    			if(diff == 0) {
    				
    			}else {
    				if(diff % 2 == 0) { // 짝수개
    					two_count += diff/2;
    				}else { // 홀수개
    					two_count += diff/2;
    					one_count++;
    				}
    			}
    		}
    		//System.out.printf("홀수:%d 짝수:%d \n",one_count,two_count);
    		if(one_count < two_count) {
    			int diff = two_count - one_count;
    			while(diff>1) {
    				one_count+=2;
    				two_count-=1;
    				diff = two_count - one_count;
    			}
    		}
    		
    		
    		//System.out.printf("홀수:%d 짝수:%d \n\n",one_count,two_count);
    		if(one_count > two_count) {

                result = one_count * 2 - 1;

            } else {

                result = two_count * 2;
            }
    		sb.append("#"+tc+" "+result+"\n");
    	}
    	System.out.print(sb);
	}
}
