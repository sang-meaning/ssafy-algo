class Solution {

	static int weakPointCount;
	static int minFriend;
	static boolean[] isSelected;

	public static int solution(int n, int[] weak, int[] dist) {

		weakPointCount = weak.length;
		minFriend = Integer.MAX_VALUE;

		int[] weakArr = new int[weak.length * 2];

		for (int i = 0; i < weak.length; i++) {
			weakArr[i] = weak[i];
			weakArr[i + weak.length] = weak[i] + n;
		}

		for (int start = 0; start < weakPointCount; start++) {
			isSelected = new boolean[dist.length];

			// 시작점, 현재 위치, 사용 친구 수
			search(weakArr, dist, start, start, 0);
		}

		if (minFriend == Integer.MAX_VALUE) {
			return -1;
		}

		return minFriend;
	}

	static void search(int[] weakArr, int[] dist, int start, int current, int friend) {

		if (current >= start + weakPointCount) {
			minFriend = Math.min(minFriend, friend);
			return;
		}

		if (friend >= minFriend) {
			return;
		}

		for (int i = 0; i < dist.length; i++) {

			if (isSelected[i]) {
				continue;
			}

			isSelected[i] = true;

			int cover = weakArr[current] + dist[i];
			int next = current;

			while (next < start + weakPointCount && weakArr[next] <= cover) {
				next++;
			}

			search(weakArr, dist, start, next, friend + 1);

			isSelected[i] = false;
		}
	}
}