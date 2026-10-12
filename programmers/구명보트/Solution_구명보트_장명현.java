import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int i = 0, j = people.length-1;
        while (i+1 < j) {
        	if (people[i] + people[j] > limit) {
        		j--;
        		answer++;
        	} else {
        		i++;
        		j--;
        		answer++;
        	}
        }
        
        if (people[i] + people[j] > limit) answer+=2;
        else answer++;
        
        return answer;
    }
}