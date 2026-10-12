import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	
	static int N, L;
	static int[] score, calorie;
	static int maxScore;
	
    public static void main(String args[]) throws Exception
    {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int test_case = 1; test_case <= T; test_case++)
        {
        	
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	L = Integer.parseInt(st.nextToken());
        	
        	score = new int[N];
        	calorie = new int[N];
        	
        	for (int i = 0; i < N; i++) {
            	st = new StringTokenizer(br.readLine());
        		
        		score[i] = Integer.parseInt(st.nextToken());
        		calorie[i] = Integer.parseInt(st.nextToken());
			}
        	
        	maxScore = 0;
        	
        	dfs(0, 0, 0);
    		
    		sb.append("#")
    		.append(test_case)
    		.append(" ")
    		.append(maxScore)
    		.append("\n");
        }
        
        System.out.print(sb);
    }

	static void dfs(int idx, int sumScore, int sumCalorie) {
		
		if (sumCalorie > L) return;  // 제한 칼로리 초과
		
		if (idx == N) {  // 모든 재료를 확인함
			maxScore = Math.max(maxScore, sumScore);
			return;
		}
		
		dfs(idx + 1, sumScore + score[idx], sumCalorie + calorie[idx]);  // 현재 재료 선택
		
		dfs(idx + 1, sumScore, sumCalorie);  // 현재 재료 선택하지 않음
	}
}