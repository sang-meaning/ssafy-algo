import java.util.*;

class Solution_외벽점검_임성진 {
	static int len, answer;
	static int[] ext, friends, perm;
	static boolean[] used;

	public int solution(int n, int[] weak, int[] dist) {
		len = weak.length;
		friends = dist;
		perm = new int[dist.length];
		used = new boolean[dist.length];
		answer = Integer.MAX_VALUE;

		ext = new int[len * 2];
		for (int i = 0; i < len * 2; i++) {
			ext[i] = (i < len) ? weak[i] : weak[i - len] + n;
		}

		permute(0);
		return answer == Integer.MAX_VALUE ? -1 : answer;
	}

	static void permute(int depth) {
		if (depth == friends.length) {
			check();
			return;
		}
		for (int i = 0; i < friends.length; i++) {
			if (used[i]) continue;

			used[i] = true;
			perm[depth] = friends[i];
			permute(depth + 1);
			used[i] = false;
		}
	}

	static void check() {
		for (int s = 0; s < len; s++) {
			int covered = 0;
			int cnt = 0;

			while (covered < len && cnt < perm.length) {
				int reach = ext[s + covered] + perm[cnt];
				cnt++;
				while (covered < len && ext[s + covered] <= reach) covered++;
			}
			if (covered == len) answer = Math.min(answer, cnt);
		}
	}
}