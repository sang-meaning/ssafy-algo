import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        // 정렬 + 앞뒤 투포인터
        Arrays.sort(people);
        
        int pointerA = 0;
        int pointerB = people.length-1;
        int personLeft = people.length;
        
        while(true) {
            if(pointerA == pointerB) { // 한명만 남으면
                answer++;
                break;
            }
            if(personLeft == 2) { // 두명남으면
                if(people[pointerA] + people[pointerB] > limit) {
                    answer += 2;
                    break;
                } else{ 
                    answer++; 
                    break;
                }
            }
            int temp = people[pointerA] + people[pointerB];
            if(temp > limit) {
                pointerB--;
                answer++;
                personLeft--;
            } else {
                pointerA++;
                pointerB--;
                answer++;
                personLeft -= 2;
            }
        }
        
        return answer;
    }
}