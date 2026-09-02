import java.util.HashSet;
class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> answerSet = new HashSet<>();
        
        for(int i = 1; i <= elements.length; i++) {
            for(int j = 0; j < elements.length; j++) {
                int sum = 0;
                for(int k = 0; k < i; k++) {
                    int target = j+k;
                    if(j + k >= elements.length) {
                        target -= elements.length;
                    }
                    sum += elements[target];
                }
                answerSet.add(sum);
            }
        }
        
        return answerSet.size();
    }
}