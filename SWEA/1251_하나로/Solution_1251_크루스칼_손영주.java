package _submission;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int from;
	int to;
	double w;

	public Edge(int from, int to, double w) {
		super();
		this.from = from;
		this.to = to;
		this.w = w;
	}

	@Override
	public int compareTo(Edge other) {
		return Double.compare(this.w, other.w);
	}
}

public class Solution {
	
	// 크루스칼

	// 환경 부담 세율(E)과 각 해저터널 길이(L)의 제곱의 곱(E * L^2)만큼 지불
	// 1≤N≤1,000
	// 각 섬들의 정수인 X좌표, 세 번째 줄에는 각 섬들의 정수인 Y좌표가 주어진다 (0≤X≤1,000,000, 0≤Y≤1,000,000).
	// 환경 부담 세율 실수 E가 주어진다 (0≤E≤1).

	// 간선이 따로 없고 완전연결 그래프나 다름없으므로 사실 크루스갈보다 프림이 유리

	static int N;
	static double E;
	static int[] islandsX, islandsY, parents;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
		int T = Integer.parseInt(br.readLine());

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {
			N = Integer.parseInt(br.readLine()); // 1번째 줄

			islandsX = new int[N];
			islandsY = new int[N];
			parents = new int[N];

			makeSets();
			// 초기화

			StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 2번째 줄 - x
			for (int i = 0; i < N; i++) {
				islandsX[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " "); // 3번째 줄 - y
			for (int i = 0; i < N; i++) {
				islandsY[i] = Integer.parseInt(st.nextToken());
			}

			E = Double.parseDouble(br.readLine()); // 4번째 줄
			// 입력 완료

			// 모든 간선들 추가
			List<Edge> edgeList = new ArrayList<>();
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					edgeList.add(new Edge(i, j, calTex(i, j)));
				}
			}

			// 정렬
			edgeList.sort(null);

			// 최소 환경 부담금을 소수 첫째 자리에서 반올림하여 정수 형태로 출력
			int cnt = 0;
			double total = 0.0;
			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					cnt++;
					total += edge.w;
				}
				if (cnt == N - 1)
					break;
			}

			total = Math.round(total);

			System.out.println("#" + test_case_num + " " + (long) total);
		}

	}

	public static double calTex(int from, int to) {
		long distance = (long) (Math.pow(islandsX[from] - islandsX[to], 2)
				+ Math.pow(islandsY[from] - islandsY[to], 2));
		return distance * E;
	}

	// 각각의 정점들을 서로소 집합화
	public static void makeSets() {
		for (int i = 0; i < N; i++) {
			parents[i] = -1;
		} // 루트인 정점은 음수 사이즈를 가짐.
	}

	// 정점을 합치기
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;

		if (parents[aRoot] <= parents[bRoot]) {
			// 사이즈가 큰 쪽에 (a) 붙이기
			parents[aRoot] += parents[bRoot]; // 사이즈 늘리기
			parents[bRoot] = aRoot;
		} else {
			// 사이즈가 큰 쪽에 (b) 붙이기
			parents[bRoot] += parents[aRoot]; // 사이즈 늘리기
			parents[aRoot] = bRoot;
		}
		return true;
	}

	public static int find(int a) {
		if (parents[a] < 0)
			return a;
		else
			return parents[a] = find(parents[a]); // path compression
	}
}
