import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = people.length;
        int start = 0;
        int end = people.length - 1;
        Arrays.sort(people);
        while(start < end){
            if(people[start] + people[end] <= limit){
                start++;
                end--;
                answer--;
            }else{
                end--;
            }
        }
        return answer;
    }
}