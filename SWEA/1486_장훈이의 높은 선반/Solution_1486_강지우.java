import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	
	static int N, B, minHeight;
	static int[] height;
	
    public static void main(String args[]) throws Exception
    {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <= T; test_case++)
        {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	B = Integer.parseInt(st.nextToken());
        	
        	st = new StringTokenizer(br.readLine());
        	height = new int[N];
        	for (int i = 0; i < N; i++) {
        		height[i] = Integer.parseInt(st.nextToken());
			}
        	
        	minHeight = Integer.MAX_VALUE;

        	dfs(0, 0);
        	
        	int diff = minHeight - B;
        	        	
    		sb.append("#")
    		.append(test_case)
    		.append(" ")
    		.append(diff)
    		.append("\n");
        }
        
        System.out.print(sb);
    }
    
    static void dfs(int idx, int curSum) {
    	
    	if (curSum >= B) {
			minHeight = Math.min(minHeight, curSum);
			return;
		}
    	
    	if (idx == N) return;
    	
		dfs(idx+1, curSum+height[idx]);
		
		dfs(idx+1, curSum);
		
    }
}