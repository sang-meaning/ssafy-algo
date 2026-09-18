import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int[] dx = {0, 0, -1, 1};
		int[] dy = {1, -1, 0, 0};
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] dir = new int[N];
			int[] pow = new int[N];
			int[][] loc = new int[N][2];
			HashSet<Integer> s = new HashSet<>();

			int xmax = -10000, xmin = 10000, ymax = -10000, ymin = 10000;
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) * 2;
				int y = Integer.parseInt(st.nextToken()) * 2;
				int d = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				
				xmax = Math.max(xmax, x);
				xmin = Math.min(xmin, x);
				ymax = Math.max(ymax, y);
				ymin = Math.min(ymin, y);
				
				s.add(i);
				dir[i] = d;
				pow[i] = p;
				loc[i][0] = x;
				loc[i][1] = y;
			}

			int answer = 0;
			int time = Math.max(xmax-xmin, ymax-ymin);
			for (int t=0; t<time; t++) {
				HashMap<Long, Integer> m = new HashMap<>();
				ArrayList<Integer> dead = new ArrayList<>();
				ArrayList<Integer> out = new ArrayList<>();
				
				// 위치 이동
				for (int i:s) {
					loc[i][0] += dx[dir[i]];
					loc[i][1] += dy[dir[i]];
					
					if (loc[i][0] < xmin || loc[i][0] > xmax || loc[i][1] < ymin || loc[i][1] > ymax) {
						out.add(i);
						continue;
					}
					
					long key = (((long) loc[i][0]) << 32) | (loc[i][1] & 0xffffffffL);
					Integer prev = m.get(key);

					if (prev == null) {
						m.put(key, i);
					} else if (prev >= 0) {
						dead.add(prev);
						dead.add(i);
						m.put(key, -1);
					} else {
						dead.add(i);
					}
				}
				
				for (int i : out) s.remove(i);
				
				for (int i : dead) {
					answer += pow[i];
					s.remove(i);
				}
				
				if (s.size() < 2) break;
			}
			
			sb.append("#").append(test_case).append(' ');
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);
	}
}