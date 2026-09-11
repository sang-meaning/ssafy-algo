import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	
	static int[] gyu = new int[9];
	static int[] in = new int[9];
	static boolean[] visited = new boolean[9];
	
	static int win;
	static int lose;
	
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <= T; test_case++)
        {
        	
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	boolean[] card = new boolean[19];
        	
        	for (int i = 0; i < 9; i++) {
        		gyu[i] = Integer.parseInt(st.nextToken());
        		card[gyu[i]] = true;
			}
        	
        	int idx = 0;
        	for (int i = 1; i <= 18; i++) {
				if (!card[i]) {
					in[idx++] = i;
				}
			}
        	
        	visited = new boolean[9];
        	
        	win = 0;
        	lose = 0;
        	
        	dfs(0, 0, 0);
    		
    		sb.append("#")
    		.append(test_case)
    		.append(" ")
    		.append(win)
    		.append(" ")
    		.append(lose)
    		.append("\n");
        }
        
        System.out.print(sb);
    }
    
    static void dfs(int depth, int gyuScore, int inScore) {  // depth: 현재 라운드
    	
    	if (depth == 9) {
    		
    		if (gyuScore > inScore) {
    			win++;
    		} else if (gyuScore < inScore) {
    			lose++;
    		}
    		
    		return;
    	}
    	
    	for (int i = 0; i < 9; i++) {
    		
    		if (visited[i]) continue;
    		
    		visited[i] = true;
    		
    		int sum = gyu[depth] + in[i];
    		
			if (gyu[depth] > in[i]) {
				dfs(depth + 1, gyuScore + sum, inScore);
			} else {
				dfs(depth + 1, gyuScore, inScore + sum);
			}
			
			visited[i] = false;
		}
    }
}