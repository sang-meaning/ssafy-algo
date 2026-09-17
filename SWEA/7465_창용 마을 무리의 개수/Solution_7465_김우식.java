package swea;

import java.util.*;
import java.io.*;

public class SWEA7465 {

	  static int[] parent;

	    public static void main(String[] args) throws Exception {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringBuilder sb = new StringBuilder();

	        int T = Integer.parseInt(br.readLine());

	        for (int tc = 1; tc <= T; tc++) {
	            StringTokenizer st = new StringTokenizer(br.readLine());

	            int N = Integer.parseInt(st.nextToken());
	            int M = Integer.parseInt(st.nextToken());

	            makeSet(N);

	            for (int i = 0; i < M; i++) {
	                st = new StringTokenizer(br.readLine());

	                int a = Integer.parseInt(st.nextToken());
	                int b = Integer.parseInt(st.nextToken());

	                union(a, b);
	            }

	            int cnt = 0;

	            for (int i = 1; i <= N; i++) {
	                if (findSet(i) == i) {
	                    cnt++;
	                }
	            }

	            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
	        }

	        System.out.print(sb);
	    }

	    static void makeSet(int n) {
	        parent = new int[n + 1];

	        for (int i = 1; i <= n; i++) {
	            parent[i] = i;
	        }
	    }

	    static int findSet(int x) {
	        if (parent[x] == x) {
	            return x;
	        }

	        return parent[x] = findSet(parent[x]);
	    }

	    static void union(int a, int b) {
	        int rootA = findSet(a);
	        int rootB = findSet(b);

	        if (rootA != rootB) {
	            parent[rootB] = rootA;
	        }
	    }
	}

