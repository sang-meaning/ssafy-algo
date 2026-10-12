class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        if (cards1.length + cards2.length < goal.length)
        	return "No";
        
        for (int i=0, j=0, gi=0; gi<=goal.length; gi++) {
        	if (gi == goal.length) return "Yes";
        	
        	if (i<cards1.length && cards1[i].equals(goal[gi])) { i++; continue; }
        	if (j<cards2.length && cards2[j].equals(goal[gi])) { j++; continue; }
        	return "No";
        }
        
        return "No";
    }
}