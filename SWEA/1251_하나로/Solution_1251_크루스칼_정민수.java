package algo;

import java.util.*;

public class 하나로_크루스칼 {
	
	static class Edge{
		int from;
		int to;
		long cost;
		
		Edge(int from, int to, long cost){
			this.from = from;
            this.to = to;
            this.cost = cost;
		}
	}
	
	static int[] parent;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			int n = sc.nextInt();
			
			long[] x = new long[n];
			long[] y = new long[n];
			
			for(int i=0; i<n; i++) {
				x[i] = sc.nextLong();
			}
			for(int i=0; i<n; i++) {
				y[i] = sc.nextLong();
			}
			
			double e = sc.nextDouble();
			
			List<Edge> edges = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    long dx = x[i] - x[j];
                    long dy = y[i] - y[j];

                    long distance = dx * dx + dy * dy;

                    edges.add(new Edge(i, j, distance));
                }
            }
            
            edges.sort((a, b) -> Long.compare(a.cost, b.cost));

            parent = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            long total = 0;
            int count = 0;

            for (Edge edge : edges) {
                if (union(edge.from, edge.to)) {
                    total += edge.cost;
                    count++;

                    if (count == n - 1) {
                        break;
                    }
                }
            }

            long answer = Math.round(total * e);

            System.out.println("#" + test_case + " " + answer);
			
			
			
		}

	}
	
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a==b) {
			return false;
		}
		
		parent[b] = a;
		return true;
	}

}


/*
모든 섬을 연결해야함
(해저터널 연결 시, 환경부담금이 있음)
환경부담금 = E(환경부담세율) * L(각 해저터널의 길이) 제곱 만큼 지불 (E * L^2)

환경부담금을 최소로하여 N개의 섬을 연결 할 수 있도록 설계

1. T (테스트케이스 수)
2. N
3. N개 만큼 각 섬의 X
4. N개 만큼 각 섬의 Y
4. 해저터널 건설 세율E

*/