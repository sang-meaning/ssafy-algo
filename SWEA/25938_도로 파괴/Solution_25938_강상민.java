import java.util.*;

/*
cal 하기 전, dijk 해서 최솟값 저장하고, 그 때의 경로 id를 큐에 저장
큐에서 1개씩 꺼내며 그 경로 파괴, dijk 하고, 얼마나 지연되는지, 혹은 도달할 수 없는지 판단, 반복
dijk은 1000 log 5000 -> 4000 이하. 간선 파괴 5000개까지니 20 * 100만 = 2000만, 25 * 200 = 5000번 : 터질까?

*/

// HashMap<Integer, Boolean> map = new  ...
// ArrayList<int[]>[] adj 

class Solution {
	private final static int MAX_K = 5000;
	private final static int CMD_INIT = 100;
	private final static int CMD_ADD = 200;
	private final static int CMD_REMOVE = 300;
	private final static int CMD_CALC = 400;

	private final static UserSolution usersolution = new UserSolution();

	private static boolean run(Scanner sc) {
		int q = sc.nextInt();

		int n, k;
		int[] mIdArr = new int[MAX_K];
		int[] sCityArr = new int[MAX_K];
		int[] eCityArr = new int[MAX_K];
		int[] mTimeArr = new int[MAX_K];
		int mId, sCity, eCity, mTime;
		int cmd, ans, ret = 0;
		boolean okay = false;

		for (int i = 0; i < q; ++i) {
			cmd = sc.nextInt();
			switch (cmd) {
				case CMD_INIT:
					okay = true;
					n = sc.nextInt();
					k = sc.nextInt();
					for (int j = 0; j < k; ++j) {
						mIdArr[j] = sc.nextInt();
						sCityArr[j] = sc.nextInt();
						eCityArr[j] = sc.nextInt();
						mTimeArr[j] = sc.nextInt();
					}
					usersolution.init(n, k, mIdArr, sCityArr, eCityArr, mTimeArr);
					break;
				case CMD_ADD:
					mId = sc.nextInt();
					sCity = sc.nextInt();
					eCity = sc.nextInt();
					mTime = sc.nextInt();
					usersolution.add(mId, sCity, eCity, mTime);
					break;
				case CMD_REMOVE:
					mId = sc.nextInt();
					usersolution.remove(mId);
					break;
				case CMD_CALC:
					sCity = sc.nextInt();
					eCity = sc.nextInt();
					ans = sc.nextInt();
					ret = usersolution.calculate(sCity, eCity);
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
  static HashMap<Integer, Boolean> map; // 간선 파괴용
  static int n,k;
  static int[] history; // 노드 번호 역추적
  static int[] history2; // 간선 id 역추적


	public void init(int N, int K, int mId[], int sCity[], int eCity[], int mTime[]) {
    n=N;
    k=K;

    adj = new ArrayList[N];
    d = new int[N];
    history = new int[N];
    history2 = new int[N];
    map = new HashMap<>();

    for (int i=0; i<N; i++) {
      adj[i] = new ArrayList<>();
      d[i] = INF;
    }

    for (int i=0; i<K; i++) {
      int ss = sCity[i];
      int ee = eCity[i];
      int ww = mTime[i];
      int id = mId[i];
      adj[ss].add(new int[] {ww,ee,id});
      map.put(id, true); // 선택 가능 의미
    }


		return;
	}

	public void add(int mId, int sCity, int eCity, int mTime) {
    adj[sCity].add(new int[] {mTime, eCity, mId});
    map.put(mId, true);

		return;
	}

	public void remove(int mId) {
    map.put(mId, false);

		return;
	}


	public int calculate(int sCity, int eCity) {
    // dijk 돌리고, history 만듦
    dijkstraInit(sCity);
    int cur = d[eCity]; // 부수기 전 비용

    if (cur == INF) return -1; // 도착지 도착 못함

    int result = 0;

    int curNode = eCity;

    while(curNode != sCity) {
      int curId = history2[curNode];

      map.put(curId, false); // 간선 파괴
      dijkstra(sCity);
      int temp = d[eCity];

      if (temp == INF) { // 간선 파괴 -> 도착지 도착 못함 : -1 리턴
        result = -1;
        map.put(curId, true);
        break;
      }

      if (temp > cur) {
        int dif = temp - cur;
        if (result < dif) result = dif;
      }

      map.put(curId, true); // 간선 파괴 복구
      curNode = history[curNode];
    }

		return result;
	}

  public void dijkstraInit(int startNode) {
    history = new int[n];
    history2 = new int[n];

    for (int i=0; i<n; i++)
      d[i] = INF;

    d[startNode] = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0],b[0])); // 비용, 노드
    pq.add(new int[] {0,startNode});

    while(!pq.isEmpty()) {
      int[] cur = pq.poll();
      int curW = cur[0];
      int curN = cur[1];

      if (d[curN] != curW) continue;

      for (int[] nxt: adj[curN]) {
        int nxtW = nxt[0];
        int nxtN = nxt[1];
        int nxtId = nxt[2];

        if (map.get(nxtId) == false) continue; // 파괴한 곳이면 패스

        if (d[nxtN] <= d[curN] + nxtW) continue;
        d[nxtN] = d[curN] + nxtW;
        pq.add(new int[] {d[nxtN], nxtN});
        history[nxtN] = curN;
        history2[nxtN] = nxtId;
        
      }
    }

  }

  public void dijkstra(int startNode) {
    for (int i=0; i<n; i++)
      d[i] = INF;

    d[startNode] = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0],b[0])); // 비용, 노드
    pq.add(new int[] {0,startNode});

    while(!pq.isEmpty()) {
      int[] cur = pq.poll();
      int curW = cur[0];
      int curN = cur[1];

      if (d[curN] != curW) continue;

      for (int[] nxt: adj[curN]) {
        int nxtW = nxt[0];
        int nxtN = nxt[1];
        int nxtId = nxt[2];

        if (map.get(nxtId) == false) continue; // 파괴한 곳

        if (d[nxtN] <= d[curN] + nxtW) continue;
        d[nxtN] = d[curN] + nxtW;
        pq.add(new int[] {d[nxtN], nxtN});
      }
    }

  }
}