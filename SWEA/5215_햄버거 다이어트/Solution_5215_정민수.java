import java.util.*;


public class Solution {
	static int n,l;
	static int[][] food;
	
	static int max;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case=1; test_case<=T; test_case++) {
			n = sc.nextInt();
			l = sc.nextInt();
            
            max = Integer.MIN_VALUE;
			
			food = new int[n][2];
			for(int i=0; i<n; i++) {
				food[i][0] = sc.nextInt();
				food[i][1] = sc.nextInt();
			}
			
			dfs(0, 0, 0);
			
			System.out.println("#"+ test_case +" "+max);
		}
		

	}
	
	public static void dfs(int i, int score, int cal) {
		
		if(cal>l) return;
		
		if(i == n) {
			max = Math.max(max, score);
			return;
		}
		
		dfs(i+1, score, cal);
		
		dfs(i+1, score+food[i][0], cal+food[i][1]);
	
	}

}

/*
같은 재료를 여러번 사용할 수 없음

1. 테스트케이스 수 T
2. 재료의 수(N), 제한칼로리(L)
3. n개의 줄 만큼 맛에대한점수, 칼로리


주어진 제한칼로리이내의 가장 맛에 대한 점수가 높은 햄버거 출력
*/