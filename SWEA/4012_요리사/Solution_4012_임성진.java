import java.util.*;
import java.io.*;

public class Solution_4012_임성진 {
	static int N, min;
	static int[][] s;
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc < T + 1; tc++) {
			
			N = Integer.parseInt(br.readLine().trim());
			
			s = new int[N][N];
			selected = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					s[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			selected[0] = true;
			pick(1, 1);
			
			System.out.printf("#%d %d\n", tc, min);
		}
	}

	static void pick(int idx, int chosen) {
	    if (chosen == N / 2) {                            // A그룹 인원이 다 찼다
	        min = Math.min(min, Math.abs(taste(true) - taste(false)));
	        return;
	    }
	    if (idx == N) return;                             // 재료가 떨어졌는데 못 채웠다

	    selected[idx] = true;                             // 이번 재료를 A에 넣는 갈래
	    pick(idx + 1, chosen + 1);

	    selected[idx] = false;                            // 넣지 않는 갈래로 되돌린다
	    pick(idx + 1, chosen);
	}

	static int taste(boolean group) {
	    int sum = 0;
	    for (int i = 0; i < N; i++) {
	        if (selected[i] != group) continue;           // 이 그룹 소속이 아니면 건너뛴다
	        for (int j = 0; j < N; j++) {
	            if (i == j || selected[j] != group) continue;
	            sum += s[i][j];
	        }
	    }
	    return sum;
	}
}
