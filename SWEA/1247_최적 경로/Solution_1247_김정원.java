package ssafy.swea.kjw;

import java.io.BufferedReader;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1247_김정원 {
	static class Place {
		int x, y;

		public Place(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Customer [x=" + x + ", y=" + y + "]";
		}
		
	}
	
	static int N;
	static int result = Integer.MAX_VALUE;
	static Place[] placeList;
	static Place home;
	static boolean[] visited;
	
    public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        final int INF = Integer.MAX_VALUE;
        StringTokenizer st = null;
        for (int test_case = 1; test_case <= T; test_case++) {
        	N = Integer.parseInt(br.readLine());
        	placeList = new Place[N + 2];
        	visited = new boolean[N + 2];
        	result = INF;
        	st = new StringTokenizer(br.readLine(), " ");
        	for (int i = 0; i < N + 2; i++) {
        		placeList[i] = new Place(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        	}
        	home = placeList[1];
        	dfs(0,0,0);
    		System.out.println(String.format("#%d %d", test_case, result));
        }
    }
    
    static void dfs(int cnt, int curIndex, int sum) {
    	Place cur = placeList[curIndex];
    	if (cnt == N) {
    		sum += Math.abs(home.x - cur.x) + Math.abs(home.y - cur.y);
    		result = result > sum ? sum : result;
    		return;
    	}
    	
    	for (int i = 2; i < N + 2; i++) {
    		if (visited[i]) continue;
    		visited[i] = true;
    		Place next = placeList[i];
    		int distance = Math.abs(next.x - cur.x) + Math.abs(next.y - cur.y);
    		dfs(cnt+1,i,sum+distance);
    		visited[i] = false;
    	}
    }
}