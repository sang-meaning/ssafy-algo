import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	
	static int N, M;
	static boolean[][] cannot;
	static boolean[] selected;
	static int answer;
	
    public static void main(String args[]) throws Exception
    {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <= T; test_case++)
        {
        	
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	
        	cannot = new boolean[N][N];
        	selected = new boolean[N];
        	
        	for (int i = 0; i < M; i++) {
        		st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				
				cannot[a][b] = true;
				cannot[b][a] = true;
			}
    		
        	answer = 0;
        	
        	dfs(0);
        	
    		sb.append("#")
    		.append(test_case)
    		.append(" ")
    		.append(answer)
    		.append("\n");
        }
        
        System.out.print(sb);
    }

	static void dfs(int idx) {
		
		if (idx == N) {
			answer++;
			return;
		}
		
		// 1. 현재 재료를 안 넣는 경우
		selected[idx] = false;
		dfs(idx+1);
		
		// 2. 현재 재료를 넣어도 되는지 검사
		boolean possible = true;
		
		for (int i = 0; i < idx; i++) {
			if (selected[i] && cannot[idx][i]) {  // i번 재료가 버거에 들어가 있고, 궁합이 맞지 않으면
				possible = false;
				break;
			}
		}
		
		// 3. 넣을 수 있으면 넣는 경우
		if (possible) {
			selected[idx] = true;
			dfs(idx+1);
			
			selected[idx] = false;
		}
	}
}