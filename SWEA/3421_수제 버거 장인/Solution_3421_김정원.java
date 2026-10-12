package ssafy.swea.kjw;

import java.io.*;
import java.util.*;

public class Solution_3421_김정원 {
	static int N,M,target,answer;
	static Map<Integer, Set<Integer>> ban;
	static boolean[] selected;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ban = new HashMap<>();
			answer = 0;
			selected = new boolean[N + 1];
			// 동시에 사용할 수 없는 재료 쌍을 입력 받기
			// 예를 들어 1번 재료가 어떤게 동시에 사용 못하는지 알고 싶다면
			// ban.get(1) 하면 boolean[] 를 반환해주니 거기서 false 인것만 골라오면 된다
			for (int i = 1; i <= N;i++) {
				ban.put(i, new HashSet<>());
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int key = Integer.parseInt(st.nextToken());
				int ingredient = Integer.parseInt(st.nextToken());
				ban.get(key).add(ingredient);
				ban.get(ingredient).add(key);
			}
			cook(1, new HashSet<>());
			System.out.println(String.format("#%d %d", test_case, answer));
		}
	}
	
	static void cook(int cnt, Set<Integer> ban_list) {
		// 모든 식재료를 선택할지 말지 정했다면
		if (cnt > N) {
			answer++;
			return;
		}
		
		// 현재 재료가 금지된 식재료가 아니라면 선택 가능
		if (!ban_list.contains(cnt)) {
			selected[cnt] = true;
			Set<Integer> next_ban_list = new HashSet<>(ban_list);
			// 현재 선택한 식재료와 같이 사용하지 못하는 식재료들을 금지 목록에 추가
			next_ban_list = banIngredinets(cnt, next_ban_list);
			cook(cnt + 1, next_ban_list);
			// 돌아왔으니 선택 취소
			selected[cnt] = false;
		}
		// 현재 재료를 선택하지 않는다
		selected[cnt] = false;
		cook(cnt + 1, ban_list);
	}
	
	
	static Set<Integer> banIngredinets(int ing, Set<Integer> ban_list) {
		// 현재 식재료와 같이 사용할 수 없는 식재료들
		Set<Integer> ing_ban_list = ban.get(ing);
		// 기존 금지 목록에 추가
		ban_list.addAll(ing_ban_list);
		return ban_list;
	}
}