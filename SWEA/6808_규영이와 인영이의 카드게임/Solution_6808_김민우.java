import java.util.*;
import java.io.*;

public class Solution_6808_김민우 {
	static int T, W, L;
	static List<Integer> numIn;
	static List<Integer> numKyu;
	static List<Integer> result;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			numIn = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18));
			numKyu = new ArrayList<>();
			result = new ArrayList<>();
			visited = new boolean[19];
			W = 0;
			L = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 9; i++) {
				int n = Integer.parseInt(st.nextToken());
				numIn.remove(Integer.valueOf(n));
				numKyu.add(n);
			}
			
			dfs(0, 0, 0);
			System.out.printf("#%d %d %d\n", test_case, W, L);
			
		}//test_case 끝
	}//main 끝
	
	public static void dfs(int cnt, int kscore, int iscore) {
		if (cnt == 9) {
			if(kscore > iscore) W++;
			else if(kscore < iscore) L++;
		}
		else {
			for (int i= 0; i < 9; i++) {
				if(visited[numIn.get(i)])
					continue;
				visited[numIn.get(i)] = true;
				if(numIn.get(i) > numKyu.get(cnt))
					dfs(cnt+1, kscore, iscore + numIn.get(i) + numKyu.get(cnt));
				else 
					dfs(cnt+1, kscore + numIn.get(i) + numKyu.get(cnt), iscore);
				visited[numIn.get(i)] = false;
			}
		}
	}
	
	//시간초과 ㅠㅠ
//	public static void dfs(int cnt) {
//		if (cnt == 9) {
//			check();
////			System.out.println(result);
//		}
//		else {
//			for(int i = 0; i < 9; i++) {
//				if (visited[numIn.get(i)])
//					continue;
//				result.add(numIn.get(i));
//				visited[numIn.get(i)] = true;
//				dfs(cnt+1);
//				result.remove(Integer.valueOf(numIn.get(i)));
//				visited[numIn.get(i)] = false;
//			}
//		}
//	}//dfs 끝
//	
//	public static void check() {
//		int kyu = 0;
//		int in = 0;
//		
//		for(int i = 0; i <9; i++) {
//			int sum = result.get(i) + numKyu.get(i);
//			if(result.get(i) > numKyu.get(i))
//				in += sum;
//			else
//				kyu += sum;
//		}
//		
//		if(kyu > in)
//			W++;
//		else if(kyu < in)
//			L++;
//	}//check 끝

}//Solution 끝
