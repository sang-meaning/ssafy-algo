class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";
		int card1Count = 0;
		int card2Count = 0;
		for (int i = 0; i < goal.length; i++) {
			if (card1Count < cards1.length && goal[i].equals(cards1[card1Count])) {
				card1Count++;
			} else if (card2Count < cards2.length && goal[i].equals(cards2[card2Count])) {
				card2Count++;
			} else {
				answer = "No";
			}
		}
		if (answer != "No") {
			answer = "Yes";
		}
		return answer;
    }
}