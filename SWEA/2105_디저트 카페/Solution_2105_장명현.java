import java.util.*;

class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			
			int [][] arr = new int[N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			int answer = -1;
			for (int si=0; si<N-1; si++) {
				for (int sj=1; sj<N-1; sj++) {
					
					for (int ei=si+2; ei<N; ei++) {
						for (int ej=sj - (ei-si-2); ej<=sj + (ei-si-2); ej+=2) {
							if (ej < 0 || ej >= N) continue;
							
							int mi = si + (ei - si)/2;
							int mj = sj + (ej - sj)/2;
							
							int li, lj, ri, rj;
							if (ej <= sj) {
								li = mi + Math.abs(ej - mj);
								lj = mj - Math.abs(ei - mi);
								ri = mi - Math.abs(sj - mj);
								rj = mj + Math.abs(si - mi);
							} else {
								li = mi - Math.abs(sj - mj);
								lj = mj - Math.abs(si - mi);
								ri = mi + Math.abs(ej - mj);
								rj = mj + Math.abs(ei - mi);
							}
							
							if (lj < 0 || rj >= N) continue;
							
							int count = 0;
							boolean cont = false;
							boolean[] visited = new boolean[101];
							
							for (int x=si, y=sj; x<li && y>lj; x++, y--) {
								if (visited[arr[x][y]]) {
									cont = true;
									break;
								}

								visited[arr[x][y]] = true;
								count++;
							}
							if (cont) continue;
							
							for (int x=li, y=lj; x<ei && y<ej; x++, y++) {
								if (visited[arr[x][y]]) {
									
									cont = true;
									break;
								}

								visited[arr[x][y]] = true;
								count++;
							}
							if (cont) continue;
							
							for (int x=ei, y=ej; x>ri && y<rj; x--, y++) {
								if (visited[arr[x][y]]) {
									cont = true;
									break;
								}

								visited[arr[x][y]] = true;
								count++;
							}
							if (cont) continue;
							
							for (int x=ri, y=rj; x>si && y>sj; x--, y--) {
								if (visited[arr[x][y]]) {
									cont = true;
									break;
								}

								visited[arr[x][y]] = true;
								count++;
							}
							if (cont) continue;
							
							answer = Math.max(answer, count);
						}
					}
				}
			}
			
			
			System.out.println("#" + test_case + " " + answer);
		}
	}
}