import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int TC = Integer.parseInt(br.readLine());
        int max;
        
        for(int test_case = 1; test_case <= TC; test_case++)
        {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	int N = Integer.parseInt(st.nextToken());
        	int M = Integer.parseInt(st.nextToken());
        	
        	st = new StringTokenizer(br.readLine());
        	
        	int[] snack = new int[N];
        	for (int i=0; i<N; i++) {
        		snack[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	max = -1;
    		for (int i=0; i<snack.length-1; i++) {	// 시작 지점
    			if (snack[i] > M) continue;
    			
    			for (int j=i+1; j<snack.length; j++) {
    				if (snack[i] + snack[j] > M) continue;
    				
    				int sum = snack[i] + snack[j];
    				if (sum > max) {
    					max = sum;
    				}
    				
    			}
    		}
    		
    		sb.append("#")
    		.append(test_case)
    		.append(" ")
    		.append(max)
    		.append("\n");
        }
        
        System.out.print(sb);
    }
}