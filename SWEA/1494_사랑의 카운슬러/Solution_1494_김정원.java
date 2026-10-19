package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1494_김정원 {
	static long[][] points;
	static boolean[] selected;
	static int N;
	static long answer;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			init();
			N = Integer.parseInt(br.readLine());
			StringTokenizer st;long x,y;
			for (int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				x = Long.parseLong(st.nextToken());
				y = Long.parseLong(st.nextToken());
				points[i] = new long[] {x,y}; 
			}
			// 이동할 지렁이 중복 없이 조합 선택
			comb(0,0);
			System.out.println(String.format("#%d %d", t, answer));
		}
	}
	
	static void init() {
		points = new long[N][];
		selected = new boolean[N];
		answer = Long.MAX_VALUE;
	}
	
	static void comb(int depth, int start) {
		// 기저 조건
		if (depth == N/2) {
			long[] sumVector = getVectorSum();
			long x = sumVector[0], y = sumVector[1];
			long d = (long) ((long) Math.pow(x, 2) + Math.pow(y, 2));
			answer = answer > d ? d : answer;
			return;
		}
		// 호출
		for (int i=start;i<N;i++) {
			selected[i] = true;
			comb(depth+1,i+1);
			selected[i] = false;
		}
	}
	
	static long[] getVectorSum() {
		long sx=0,sy=0,ex=0,ey=0;
		for (int i=0;i<N;i++) {
			if (selected[i]) {
				sx += points[i][0];
				sy += points[i][1];
			} else {
				ex += points[i][0];
				ey += points[i][1]; 
			}
		}
		return new long[]{ex-sx,ey-sy};
	}
}
