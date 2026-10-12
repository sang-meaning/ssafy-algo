import java.util.*;


class Solution {
	private final static int MAX_E = 2000;
	private final static int MAX_S = 3;
	private final static int CMD_INIT = 100;
	private final static int CMD_ADD = 200;
	private final static int CMD_CALC = 300;

	private final static UserSolution usersolution = new UserSolution();

	private static boolean run(Scanner sc) {
		int q = sc.nextInt();

		int n, m, k;
		String strTmp;
		int[] sCityArr = new int[MAX_E];
		int[] eCityArr = new int[MAX_E];
		int[] mLimitArr = new int[MAX_E];
		int[] mStopover = new int[MAX_S];
		int sCity, eCity, mLimit;
		int cmd, ans, ret = 0;
		boolean okay = false;

		for (int i = 0; i < q; ++i) {
			cmd = sc.nextInt();
			strTmp = sc.next();
			switch (cmd) {
				case CMD_INIT:
					okay = true;
					strTmp = sc.next();
					n = sc.nextInt();
					strTmp = sc.next();
					k = sc.nextInt();
					for (int j = 0; j < k; ++j) {
						strTmp = sc.next();
						sCityArr[j] = sc.nextInt();
						strTmp = sc.next();
						eCityArr[j] = sc.nextInt();
						strTmp = sc.next();
						mLimitArr[j] = sc.nextInt();
					}
					usersolution.init(n, k, sCityArr, eCityArr, mLimitArr);
					break;
				case CMD_ADD:
					strTmp = sc.next();
					sCity = sc.nextInt();
					strTmp = sc.next();
					eCity = sc.nextInt();
					strTmp = sc.next();
					mLimit = sc.nextInt();
					usersolution.add(sCity, eCity, mLimit);
					break;
				case CMD_CALC:
					strTmp = sc.next();
					sCity = sc.nextInt();
					strTmp = sc.next();
					eCity = sc.nextInt();
					strTmp = sc.next();
					m = sc.nextInt();
					for (int j = 0; j < m; ++j) {
						strTmp = sc.next();
						mStopover[j] = sc.nextInt();
					}
					strTmp = sc.next();
					ans = sc.nextInt();
					ret = usersolution.calculate(sCity, eCity, m, mStopover);
					if (ret != ans)
						okay = false;
					break;
				default:
					okay = false;
					break;
			}
		}
		return okay;
	}

	public static void main(String[] args) throws Exception {
		int TC, MARK;

		//System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

		Scanner sc = new Scanner(System.in);

		TC = sc.nextInt();
		MARK = sc.nextInt();

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run(sc) ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		sc.close();
	}
}

class UserSolution {
	static ArrayList<int[]>[] adj;
	static int[] d;
	static int INF = 0x3f3f3f3f;
	static int n; // N 전역변수


	public void init(int N, int K, int sCity[], int eCity[], int mLimit[]) {
		// N:노드, K:간선
		adj = new ArrayList[N]; // 노드 번호 0부터 시작
		d = new int[N];

		for (int i=0; i<N; i++) {
			adj[i] = new ArrayList<>();
			d[i] = -1; // 미방문
		}

		n=N;

		for (int i=0; i<K; i++) {
			int ss = sCity[i];
			int ee = eCity[i];
			int ww = mLimit[i];

			adj[ss].add(new int[] {ww, ee});
			adj[ee].add(new int[] {ww, ss});
		}

		return;
	}

	public void add(int sCity, int eCity, int mLimit) {
		adj[sCity].add(new int[] {mLimit, eCity});
		adj[eCity].add(new int[] {mLimit, sCity});

		return;
	}

	public int calculate(int sCity, int eCity, int M, int mStopover[]) {
	
		// 시작 -> a -> b -> c -> 도착
		// 가중치의 누적 합인 다익스트라가 아니므로 노드 방문 순서는 중요하지 않다
		// "시작", a, b, c 에 대해 각각 다익스트라를 돌려서 다음 도착지까지의 d 값을 모두 구한 다음 그 중 최솟값 고르기
		
		
		dijkstra(sCity);
		int result = d[mStopover[0]]; // 시작 -> a

		for (int i=0; i<M-1; i++) {
			dijkstra(mStopover[i]);
			result = Math.min(result, d[mStopover[i+1]]); // a-> b -> c
		}

		dijkstra(mStopover[M-1]);
		result = Math.min(result, d[eCity]); // c -> 도착


		if (result >= INF) return -1;
		else return result;
	}

	public void dijkstra(int startNode) {
		for (int i=0; i<n; i++)
			d[i] = -1;


		d[startNode] = INF;

		// 비용에 대해 내림차순
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(b[0],a[0])); // 비용, 노드
		pq.add(new int[] {INF, startNode});

		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curW = cur[0];
			int curN = cur[1];

			if (d[curN] > curW) continue;

			for (int[] nxt: adj[curN]) {
				int nxtW = nxt[0];
				int nxtN = nxt[1];

				int temp = Math.min(nxtW, curW); // 현재 노드까지의 최소 비용과 다음 노드까지의 간선 비용중 최솟값

				if (d[nxtN] >= temp) continue;

				d[nxtN] = temp;
				pq.add(new int[] {temp, nxtN});
			}
		}


	}


}