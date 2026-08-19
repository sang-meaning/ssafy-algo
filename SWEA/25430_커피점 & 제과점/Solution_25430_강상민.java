import java.util.*;

class Solution {
	private final static int MAX_E = 30000;
	private final static int MAX_SHOP = 1000;
	private final static int CMD_INIT = 100;
	private final static int CMD_ADD = 200;
	private final static int CMD_CALC = 300;

	private final static UserSolution usersolution = new UserSolution();

	private static boolean run(Scanner sc) {
		int q = sc.nextInt();

		int n, k, m, p, r;
		int[] sBuildingArr = new int[MAX_E];
		int[] eBuildingArr = new int[MAX_E];
		int[] mDistArr = new int[MAX_E];
		int[] mCoffee = new int[MAX_SHOP];
		int[] mBakery = new int[MAX_SHOP];
		int sBuilding, eBuilding, mDist;
		int cmd, ans, ret = 0;
		boolean okay = false;

		for (int i = 0; i < q; ++i) {
			cmd = sc.nextInt();
			switch (cmd) {
				case CMD_INIT:
					okay = true;
					n = sc.nextInt();
					k = sc.nextInt();
					for (int j = 0; j < MAX_E; ++j) {
						sBuildingArr[j] = -1;
						eBuildingArr[j] = -1;
						mDistArr[j] = 0;
					}
					for (int j = 0; j < k; ++j) {
						sBuildingArr[j] = sc.nextInt();
						eBuildingArr[j] = sc.nextInt();
						mDistArr[j] = sc.nextInt();
					}
					usersolution.init(n, k, sBuildingArr, eBuildingArr, mDistArr);
					break;
				case CMD_ADD:
					sBuilding = sc.nextInt();
					eBuilding = sc.nextInt();
					mDist = sc.nextInt();
					usersolution.add(sBuilding, eBuilding, mDist);
					break;
				case CMD_CALC:
					m = sc.nextInt();
					p = sc.nextInt();
					r = sc.nextInt();
					for (int j = 0; j < MAX_SHOP; ++j) {
						mCoffee[j] = -1;
						mBakery[j] = -1;
					}
					for (int j = 0; j < m; ++j) {
						mCoffee[j] = sc.nextInt();
					}
					for (int j = 0; j < p; ++j) {
						mBakery[j] = sc.nextInt();
					}
					ret = usersolution.calculate(m, mCoffee, p, mBakery, r);
					ans = sc.nextInt();
					if (ans != ret)
						okay =false;
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

/*
다익 시작을 커피, 제과에서 한다면
30000 * log 1000 * 2 = 20만
25 * 100 * 20만 = 5억
*/

class UserSolution {
  static int n,k; // 건물수, 도로수
  static ArrayList<int[]>[] adj;
  static int[] d1; // 커피들에서 시작
  static int[] d2; // 제과점들에서 시작 
  static int INF = 0x3f3f3f3f;

  
	public void init(int N, int K, int sBuilding[], int eBuilding[], int mDistance[]) {
    n=N;
    k=K;

    adj = new ArrayList[n];
    d1 = new int[n];
    d2 = new int[n];

    for (int i=0; i<n; i++) {
      adj[i] = new ArrayList<>();
      d1[i] = INF;
      d2[i] = INF;
    }

    for (int i=0; i<k; i++) {
      int ss = sBuilding[i];
      int ee = eBuilding[i];
      int ww = mDistance[i];

      adj[ss].add(new int[] {ww,ee});
      adj[ee].add(new int[] {ww,ss});
    }

		return;
	}

	public void add(int sBuilding, int eBuilding, int mDistance) {
    adj[sBuilding].add(new int[] {mDistance, eBuilding});
    adj[eBuilding].add(new int[] {mDistance, sBuilding});

		return;
	}

	public int calculate(int M, int mCoffee[], int P, int mBakery[], int R) {
    for (int i=0; i<n; i++) {
      d1[i] = INF;
      d2[i] = INF;
    }

   

    boolean[] vis = new boolean[n];
    for (int i=0; i<M; i++)
      vis[mCoffee[i]] = true;

    for (int i=0; i<P; i++)
      vis[mBakery[i]] = true;


    dijkstra(M, mCoffee, d1, R); // 커피

    dijkstra(P, mBakery, d2, R); // 베이커리

    int result = Integer.MAX_VALUE;

    for (int i=0; i<n; i++) {
      if (vis[i]) continue;

      int t1 = d1[i];
      int t2 = d2[i];

      if (t1 > R || t2 > R) continue;

      int temp = t1+t2;
      result = Math.min(result, temp);


    }

    if (result == Integer.MAX_VALUE) return -1;
    else return result;
	}

  public void dijkstra(int N, int[] b, int[] d, int R) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((aa,bb) -> Integer.compare(aa[0],bb[0]));

    for (int i=0; i<N; i++) {
      d[b[i]] = 0;
      pq.add(new int[] {0, b[i]});
    }

    while(!pq.isEmpty()) {
      int[] cur = pq.poll();
      int curW = cur[0];
      int curN = cur[1];

      
      if (curW > R) break; // 더이상 탐색 무의미

      if (d[curN] != curW) continue;

      for (int[] nxt: adj[curN]) {
        int nxtW = nxt[0];
        int nxtN = nxt[1];

        if (d[nxtN] <= d[curN] + nxtW) continue;
        d[nxtN] = d[curN] + nxtW;
        pq.add(new int[] {d[nxtN], nxtN});
      }
    }


  }
}