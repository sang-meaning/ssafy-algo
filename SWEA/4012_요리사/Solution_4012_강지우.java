import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static int N;
	static int[][] synergy;
	static boolean[] selected;
	static int min;
	
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <= T; test_case++)
        {
        	
        	N = Integer.parseInt(br.readLine());
        	
        	synergy = new int[N][N];
        	selected = new boolean[N];
        	
        	for (int i=0; i<N; i++) {
        		StringTokenizer st = new StringTokenizer(br.readLine());
        		for (int j=0; j<N; j++) {
        			synergy[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	min = Integer.MAX_VALUE;
        	
        	selected[0] = true;
        	
        	combination(1, 1);
    		
    		sb.append("#")
    		.append(test_case)
    		.append(" ")
    		.append(min)
    		.append("\n");
        }
        
        System.out.print(sb);
    }
    
    static void combination(int start, int count) {
    	
    	if (count == N/2) {
    		calculate();
    		return;
    	}
    	
    	for (int i=start; i<N; i++) {
    		selected[i] = true;    // 선택
    		
    		combination(i+1, count+1);
    		
    		selected[i] = false;    // 선택 취소
    	}
    }
    
    static void calculate() {
    	
    	int tasteA = 0;
    	int tasteB = 0;
    	
    	for (int i=0; i<N; i++) {
    		for (int j=i+1; j<N; j++) {
    			
    			if (selected[i] && selected[j]) {
    				tasteA += synergy[i][j] + synergy[j][i];
    			}
    			
    			if (!selected[i] && !selected[j]) {
    				tasteB += synergy[i][j] + synergy[j][i];
    			}
    		}
    	}
    	
    	int diff = Math.abs(tasteA - tasteB);
    	
    	min = Math.min(min, diff);
    }
}