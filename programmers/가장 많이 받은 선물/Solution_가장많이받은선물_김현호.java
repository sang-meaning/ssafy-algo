package algorithm_solution;

public class PROG_258712 {
	public static int solution(String[] friends, String[] gifts) {
	    int answer = 0;
	    int n = friends.length;

	    int[] giftcount = new int[n];
	    int[] friendgift = new int[n];
	    int[][] changegift = new int[n][n];

	    for (String gift : gifts) {
	        String[] names = gift.split(" ");

	        String giver = names[0];
	        String receiver = names[1];

	        int friend1 = -1;
	        int friend2 = -1;

	        for (int j = 0; j < n; j++) {
	            if (friends[j].equals(giver)) {
	                friend1 = j;
	            }

	            if (friends[j].equals(receiver)) {
	                friend2 = j;
	            }
	        }

	        friendgift[friend1]++;
	        friendgift[friend2]--;

	        changegift[friend1][friend2]++;
	    }

	    for (int i = 0; i < n; i++) {
	        for (int j = i + 1; j < n; j++) {

	            if (changegift[i][j] > changegift[j][i]) {
	                giftcount[i]++;

	            } else if (changegift[i][j] < changegift[j][i]) {
	                giftcount[j]++;

	            } else {
	                if (friendgift[i] > friendgift[j]) {
	                    giftcount[i]++;

	                } else if (friendgift[i] < friendgift[j]) {
	                    giftcount[j]++;
	                }
	            }
	        }
	    }

	    for (int count : giftcount) {
	        answer = Math.max(answer, count);
	    }

	    return answer;
	}
	public static void main(String[] args) {
		String[] friends = {"a", "b", "c", "d", "e"};
		String[] gifts = {"a b", "a b", "b a", "c a", "c a", "d c"};
		System.out.println(solution(friends, gifts));
	}
}
