import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int checkCnt = discount.length - 9; // 체크할 일수
        
        for(int i = 0; i < checkCnt; i++) { 
            HashMap<String, Integer> saleMap = new HashMap<>();
            for(int j = 0; j < 10; j++) {
                saleMap.put(discount[i+j], saleMap.getOrDefault(discount[i+j] , 0) + 1);
            }
            if(checkPossible(saleMap, want, number)) 
                answer++;
        }
        return answer;
    }
    
    public boolean checkPossible(HashMap<String, Integer> saleMap, String[] want, int[] number) {
        for(int i = 0; i < want.length; i++) {
            if(saleMap.getOrDefault(want[i] , 0) < number[i]) {
                return false;
            }
        }
        return true;
    }
}