
import java.util.*;

public class 수제버거장인 {
	
	static int n,m,count;
	
	static int[][] m_arr;
	static boolean[] visited;

	
	
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			
			
			n = sc.nextInt();
			m = sc.nextInt();
			
			m_arr = new int[m][2];
			
			for(int i=0; i<m; i++) {
				m_arr[i][0] = sc.nextInt();
				m_arr[i][1] = sc.nextInt();
			}
			
			count = 0;
			
			visited = new boolean[n+1];
			
			dfs(1);
			
			System.out.println("#"+test_case+" "+count);
			
		}
	}
	
	public static void dfs(int num) {
		
		for(int i=0; i<m_arr.length; i++) {
			int a = m_arr[i][0];
			int b = m_arr[i][1];
			
			if(visited[a] && visited[b]) return;
		}
		
		
		if(num>n) {
			count++;
			return;
		}
		
		
		visited[num] = true;
		dfs(num+1);
		
		visited[num] = false;
		dfs(num+1);
		
	}

}

/*
신메뉴개발
N가지의 재료 사용가능
궁합이 맞지않는재료는 함께 사용불가
M개의 쌍의 맞지않는 재료들

최대로 몇가지 종류의 버거를 만들 수 있는지 출력


1. 테스트케이스
2. N, M
3. M개의 쌍
 */
