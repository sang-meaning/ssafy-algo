 
import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int T,N;
	static int[][] map;
	static int people_count;
	static int sx1,sy1,sx2,sy2;
	static int[] arr;
	static int[][] people;
	static int result;
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			people_count = 0;
			sx1 = sy1 = sx2 = sy2 = 0;
			int stair_flag = 0;
			result = Integer.MAX_VALUE;
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						people_count++;
					}else if(map[i][j] > 1) {
						if(stair_flag == 0) {
							sx1 = i;
							sy1 = j;
							stair_flag = 1;
						}else {
							sx2 = i;
							sy2 = j;
						}
					}
				}
			}
			people = new int[2][people_count];
			int index = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j] == 1) {
						people[0][index] = i;
						people[1][index] = j;
						index++;
					}
				}
			}

			arr = new int[people_count];
			dfs(0);
			
			sb.append("#"+tc+" "+result+"\n");
		}
		System.out.print(sb);
	}
	
	private static void dfs(int depth) {
		
		if(depth == people_count) {
			calcul(arr);
			return;
		}
		
		arr[depth] = 1;
		dfs(depth+1);
		
		arr[depth] = 2;
		dfs(depth+1);
	}

	private static void calcul(int[] arr2) {
		
		ArrayList<Integer> stair1 = new ArrayList<>();
		ArrayList<Integer> stair2 = new ArrayList<>();
		for(int i=0;i<people_count;i++) {
			int cx = people[0][i];
			int cy = people[1][i];
			
			if(arr2[i] == 1) {
				int dist = Math.abs(cx-sx1) + Math.abs(cy-sy1);
				stair1.add(dist);
			}else {
				int dist = Math.abs(cx-sx2) + Math.abs(cy-sy2);
				stair2.add(dist);
			}
		}
		
		Collections.sort(stair1);
		Collections.sort(stair2);
		
		int time1 = getTime(stair1,map[sx1][sy1]);
		int time2 = getTime(stair2,map[sx2][sy2]);
		
		result = Math.min(Math.max(time1, time2), result);
	}

	private static int getTime(ArrayList<Integer> list, int stairlen) {
		
		if(list.isEmpty()) {
	        return 0;
	    }
		
		int[] finish = new int[list.size()];
		for(int i=0;i<list.size();i++) {
			
			int arrive = list.get(i)+1;
			
			if(i<3) {
				finish[i] = arrive + stairlen;
			}else {
				int start = Math.max(arrive, finish[i-3]);
				
				finish[i] = start + stairlen;
			}
		}
		return finish[finish.length-1];
	}
}
