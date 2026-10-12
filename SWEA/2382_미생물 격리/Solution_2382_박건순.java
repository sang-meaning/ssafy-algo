import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution {

	static class Microbe {
		int x;
		int y;
		int count;
		int dir;

		// 같은 위치에서 방향을 결정하기 위한
		// 가장 큰 원래 군집 크기
		int max_count;

		public Microbe(int x, int y, int count, int dir) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.dir = dir;
			this.max_count = count;
		}
	}

	static int N;

	// 1: 상, 2: 하, 3: 좌, 4: 우
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			ArrayList<Microbe> microbes = new ArrayList<>();

			for (int i = 0; i < K; i++) {

				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				microbes.add(new Microbe(x, y, count, dir));
			}

			for (int time = 0; time < M; time++) {

				move_microbes(microbes);

				microbes = merge_microbes(microbes);
			}

			int answer = 0;

			for (Microbe microbe : microbes) {
				answer += microbe.count;
			}

			System.out.println("#" + test_case + " " + answer);
		}
	}

	static void move_microbes(ArrayList<Microbe> microbes) {

		for (Microbe microbe : microbes) {

			// 1. 이동
			microbe.x += dx[microbe.dir];
			microbe.y += dy[microbe.dir];

			// 2. 약품 셀 도착 여부 확인
			if (is_border(microbe.x, microbe.y)) {

				microbe.count /= 2;

				microbe.dir = reverse_dir(microbe.dir);
			}

			// 이동 후 현재 군집 크기가
			// 병합 시 비교 기준이 됨
			microbe.max_count = microbe.count;
		}

		microbes.removeIf(microbe -> microbe.count == 0);
	}

	static ArrayList<Microbe> merge_microbes(ArrayList<Microbe> microbes) {

		Microbe[][] map = new Microbe[N][N];

		ArrayList<Microbe> merged_list = new ArrayList<>();

		for (Microbe microbe : microbes) {

			int x = microbe.x;
			int y = microbe.y;

			// 해당 위치에 아무 군집도 없다면
			if (map[x][y] == null) {

				map[x][y] = microbe;

				merged_list.add(microbe);
			}

			// 이미 군집이 있다면 병합
			else {

				Microbe merged = map[x][y];

				// 현재 들어온 군집이 더 크다면
				// 방향을 현재 군집 방향으로 변경
				if (microbe.count > merged.max_count) {

					merged.max_count = microbe.count;
					merged.dir = microbe.dir;
				}

				// 미생물 수는 모두 합산
				merged.count += microbe.count;
			}
		}

		return merged_list;
	}

	static boolean is_border(int x, int y) {

		return x == 0 || x == N - 1 || y == 0 || y == N - 1;
	}

	static int reverse_dir(int dir) {

		switch (dir) {

		case 1:
			return 2;

		case 2:
			return 1;

		case 3:
			return 4;

		case 4:
			return 3;
		}

		return dir;
	}
}