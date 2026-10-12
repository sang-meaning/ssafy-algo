import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연산자가 리프 노드 or 숫자가 리프 노드 아니면 불가능
class Solution {
	public static int N;
	public static boolean answer;
	public static String[] node_value;
	public static int[][] node_child;
	
	public static void main(String args[]) throws Exception {
		// Fast Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// Fast Output
		StringBuilder sb = new StringBuilder();
		
		// Execute TC
		// int T = Integer.parseInt(br.readLine());
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			// 변수 저장 및 선언
			N = Integer.parseInt(br.readLine());
			
			node_value = new String[N+1];
			node_child = new int[N+1][2];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int idx = Integer.parseInt(st.nextToken());
				node_value[idx] = st.nextToken();
				node_child[idx][0] = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : -1;
				node_child[idx][1] = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : -1;
			}
			
			answer = true;
			for (int i=1; i<=N; i++) {
				boolean isLeaf = ((node_child[i][0] == -1) && (node_child[i][1] == -1));
				if (node_value[i].equals("+") ||
					node_value[i].equals("-") ||
					node_value[i].equals("*") ||
					node_value[i].equals("/")) {
					if (isLeaf) {
						answer = false;
						break;
					}
				} else {
					if (!isLeaf) {
						answer = false;
						break;
					}
				}
			}
			
			// Add Output
			sb.append('#').append(test_case).append(' ');
			if (answer) sb.append("1\n");
			else sb.append("0\n");
		}
		
		// Print Output
		System.out.println(sb);
	}
}
