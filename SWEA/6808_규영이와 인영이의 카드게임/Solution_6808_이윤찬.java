import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int T;
	static List<Integer> a;
	static List<Integer> b;

	static boolean[] used;
	static boolean[] visited;
	static int win;
	static int lose;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			a = new ArrayList<>();
			b = new ArrayList<>();
			win = 0;
			lose = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				a.add(Integer.parseInt(st.nextToken()));
			}
			for (int j = 1; j <= 18; j++) {
				if(a.contains(j))continue;
				b.add(j);
			}

			dfs(0,0,0);
			
			System.out.println("#"+t+" "+win+" "+lose);
		}
	}
	
	static void dfs(int depth, int aScore, int bScore) {
		
		if(depth ==9) {
			if(aScore> bScore) {
				win++;
			}
			else {
				lose++;
			}
			return;
		}
		
		for(int i = 0; i < 9; i++) {
			if(visited[i]){continue;
			}
			visited[i] =true;
			
			int aCard = a.get(depth);
			int bCard = b.get(i);
			
			if(aCard > bCard) {
				dfs(depth+1,aScore+aCard+bCard, bScore);
			}
			else {
				dfs(depth+1, aScore,bScore+aCard+bCard);
			}
			visited[i]= false;
		}
	}
	
}