import java.util.*;
import java.io.*;

public class Solution_5644_임성진{
	static int[] dr = { 0, -1, 0, 1, 0 };
	static int[] dc = { 0, 0, 1, 0, -1 };

	static int M, BC_cnt;
	static int[] moveA, moveB;
	static List<BC> bcList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc < T + 1; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			M = Integer.parseInt(st.nextToken());
			BC_cnt = Integer.parseInt(st.nextToken());

			moveA = new int[M];
			moveB = new int[M];

			st = new StringTokenizer(br.readLine().trim());
			for (int i = 0; i < M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine().trim());
			for (int i = 0; i < M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}

			bcList = new ArrayList<>();
			bcList.add(new BC(0, 0, 0, 0, 0));

			for (int i = 1; i <= BC_cnt; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int range = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());

				bcList.add(new BC(i, r, c, range, power));
			}

			ChargeManager game = new ChargeManager();
			int totalScore = game.run();

			sb.append('#').append(tc).append(' ').append(totalScore).append('\n');
		}

		System.out.print(sb);
	}

	static class ChargeManager {
		int[] r = { 1, 10 };
		int[] c = { 1, 10 };

		int run() {
			int total = 0;

			total += getMaxCharge();

			for (int t = 0; t < M; t++) {
				r[0] += dr[moveA[t]];
				c[0] += dc[moveA[t]];
				r[1] += dr[moveB[t]];
				c[1] += dc[moveB[t]];

				total += getMaxCharge();
			}

			return total;
		}

		int getMaxCharge() {
			List<BC> listA = getAvailableBCs(r[0], c[0]);
			List<BC> listB = getAvailableBCs(r[1], c[1]);

			int maxVal = 0;

			for (BC bcA : listA) {
				for (BC bcB : listB) {
					int sum = 0;

					if (bcA.id == bcB.id) {
						sum = bcA.power;
					} else {
						sum = bcA.power + bcB.power;
					}

					if (sum > maxVal) {
						maxVal = sum;
					}
				}
			}

			return maxVal;
		}

		List<BC> getAvailableBCs(int curR, int curC) {
			List<BC> available = new ArrayList<>();
			available.add(bcList.get(0));

			for (int i = 1; i <= BC_cnt; i++) {
				BC bc = bcList.get(i);
				int dist = Math.abs(curR - bc.r) + Math.abs(curC - bc.c);
				if (dist <= bc.range) {
					available.add(bc);
				}
			}

			return available;
		}
	}

	static class BC {
		int id;
		int r, c;
		int range;
		int power;

		BC(int id, int r, int c, int range, int power) {
			this.id = id;
			this.r = r;
			this.c = c;
			this.range = range;
			this.power = power;
		}
	}
}