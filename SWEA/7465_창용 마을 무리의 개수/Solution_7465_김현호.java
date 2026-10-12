package test;
import java.util.*;
public class swea_7465 {
	static int[] parent;
    static int[] size;
    static int answer;
    static int find(int a) {
        if (parent[a] != a) {
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            return;
        }
        answer--;
        // 작은 집합을 큰 집합에 붙이기
        if (size[pa] < size[pb]) {
            parent[pa] = pb;
            size[pb] += size[pa];
        } else {
            parent[pb] = pa;
            size[pa] += size[pb];
        }
    }
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	int T = sc.nextInt();
    	for(int tc = 1; tc <= T; tc++) {
    		int N = sc.nextInt();
            int M = sc.nextInt();

            parent = new int[N + 1];
            size = new int[N + 1];
            answer = N;
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            for (int i = 0; i < M; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                union(a, b);
            }
    		System.out.println("#" + tc + " " + answer);
    	}
    }
}
