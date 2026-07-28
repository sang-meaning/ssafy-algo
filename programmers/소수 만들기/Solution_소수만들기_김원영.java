import java.util.*;

class Solution {
    
    public boolean IsPrime(int number){
        for(int i = 2; i * i <= number; i++ ){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
    //3중 포문을 돌려서 나온 값들을 해시 맵의 키값으로 두고 벨류값을 겹치는 횟수로 설정합니다.
    //키값들로 소수 판별을 한 뒤 해당되는 벨류값들을 모두 더합니다.
    public int solution(int[] nums) {
        Map <Integer, Integer> map = new HashMap<Integer, Integer>();
        int len = nums.length;
        int answer = 0;
        for (int i = 0; i < len - 2; i++){
            for ( int j = i + 1; j < len - 1; j++){
                for ( int k = j + 1; k < len ; k++ ){
                    int key_number = (nums[i] + nums[j] + nums[k]);
                    if(map.containsKey(key_number)){
                        int duplicated_number = map.get(key_number)+1;
                        map.put(key_number,duplicated_number);
                    }else if(map.get(key_number)==null){
                        map.put(key_number,1);
                    }
                }
            }
        }
        for (Integer key : map.keySet()) {
            if(IsPrime(key)){
                answer += map.get(key);
            }
        }
        return answer;
    }
}