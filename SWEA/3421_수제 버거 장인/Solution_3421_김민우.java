import java.util.*;
import java.io.*;


public class Solution_3421_김민우 {

	static int T, N, M;
	static Set<List<Integer>> burger;
	static List<List<Integer>> forbid;
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			burger = new HashSet<>();
			List<Integer> init = new ArrayList<>();
            burger.add(init);
			//burger.add(Arrays.asList());
			forbid = new ArrayList<>(N+1);
			for(int i = 0; i <= N; i++) {
				forbid.add(i, new ArrayList<>());
			}
			if(M == 0) {
				System.out.printf("#%d %d\n", test_case, (int)Math.pow(2, N));
				continue;
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int fst = Integer.parseInt(st.nextToken());
				int scd = Integer.parseInt(st.nextToken());
				List<Integer> tmp1 = new ArrayList<>();
				tmp1.addAll(forbid.get(fst));
				tmp1.add(scd);
				List<Integer> tmp2 = new ArrayList<>();
				tmp2.addAll(forbid.get(scd));
				tmp2.add(fst);
				forbid.set(fst, tmp1);
				forbid.set(scd, tmp2);
			}
			
			for(int i = 1; i <= N; i++) {
				Set<List<Integer>> tmpSet = new HashSet<>();
				for(List<Integer> b : burger) {
					boolean flag = true;
					for(int ingred : b) {
						if(forbid.get(ingred).contains(i)) {
							flag = false;
							break;
						}
					}
					if(!flag)
						continue;
					List<Integer> tmp = new ArrayList<>();
					tmp.addAll(b);
					tmp.add(i);
					tmpSet.add(tmp);
				}
				burger.addAll(tmpSet);
			}

			System.out.println(forbid);
			System.out.printf("#%d %d\n", test_case, burger.size());
		}//test_case 끝
	}

}
