import java.util.*;
import java.io.*;

public class Solution_3421_김민우 {

	static int T, N, M;
	static List<Integer>[] forbid;
	static boolean[] selected;
	static int answer;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			if(M == 0) {
				answer = (int)Math.pow(2, N);
				sb.append("#"+test_case+" "+answer+"\n");
				continue;
			}
			
			forbid = new ArrayList[N+1];
			for(int i = 1; i <= N; i++) {
				forbid[i] = new ArrayList<>();
			}
			selected = new boolean[N+1];
			
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int fst = Integer.parseInt(st.nextToken());
				int scd = Integer.parseInt(st.nextToken());
				
				forbid[fst].add(scd);
				forbid[scd].add(fst);
			}
			answer = 0;
			makeBurger(0);
			sb.append("#"+test_case+" "+answer+"\n");
		}//test_case
		System.out.println(sb);
	}//main

	public static void makeBurger(int cnt) {
		if(cnt == N) {
			boolean flag = true;
			//현재 햄버거에 들어가 있는 재료 i에 대하여
			for(int i = 1; i <= N; i++) {
				if(!selected[i])
					continue;
				//금지 재료 j가 같이 들어가 있는가?
				for(int j : forbid[i]) {
					if(selected[j]) {
						flag = false;
						break;
					}
				}
				//flag == false => 즉, 만들어질 수 없는 버거면 return
				if(!flag)
					return;
			}
			//모든 for문을 돌았다면, 만들어질 수 있는 햄버거이므로 answer++
			answer++;
			return;
		}//if문 끝
		
		selected[cnt+1] = true;
		makeBurger(cnt+1);
		selected[cnt+1] = false;
		makeBurger(cnt+1);
	}
}