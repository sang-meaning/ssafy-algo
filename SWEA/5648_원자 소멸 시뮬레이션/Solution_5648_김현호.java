package test;
import java.util.*;
public class swea_5648 {
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int[][] atoms = new int[N][4];
			boolean[] atomic = new boolean[N];
			int max = 0;
			int energy = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < 4; j++) {
					atoms[i][j] = sc.nextInt();
					max = Math.max(max, atoms[i][j]);
				}
			}
			for(int i = 0; i < max*2; i++) {
				int[][] place = new int[N][2];
				int count = 0;
				for(int[] atom : atoms) {
					atom[0] += dx[atom[2]];
					atom[1] += dy[atom[2]];
					place[count][0] = atom[0];
					place[count][1] = atom[1];
					count++;
				}
				for(int j = 0; j < N; j++) {
					if(atomic[j] == true) {
						continue;
					}
					boolean flag = false;
					for(int k = j + 1; k < N; k++) {
						if(atomic[k] == true) {
							continue;
						}
						if(atoms[j][0] == atoms[k][0] && atoms[j][1] == atoms[k][1]) {
							atomic[k] = true;
						}
					}
					if(flag) {
						atomic[j] = true;
					}
				}
			}
			for(int i = 0; i < N; i++) {
				if(atomic[i]) {
					energy += atoms[i][3];
				}
			}
			System.out.println("#" + test_case + " " + energy);
		}
	}
}
