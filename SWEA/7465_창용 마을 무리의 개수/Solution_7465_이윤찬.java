import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_7465_이윤찬 {
	static int N;
	static int T;
	static List<Integer>[] graph;
	static int M;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N+1];

			for (int i = 0; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int j = 0; j<M; j++) {
				st = new StringTokenizer(br.readLine());
				
				int a= Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a].add(b);
				graph[b].add(a);
			}
			visited = new boolean[N+1];
			
		
		}
	}
}
