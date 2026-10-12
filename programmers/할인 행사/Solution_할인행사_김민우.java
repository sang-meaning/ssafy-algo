import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> wantList = new HashMap<>();
        Map<String, Integer> tmp = new HashMap<>();
        
        for(int i = 0; i < want.length; i++){
            wantList.put(want[i], number[i]);
        }
        
        tmp.putAll(wantList);
        
        int start = 0;
        int limit = discount.length-10;
        
        while(start <= limit){
            //시작지점 체크 : want에 있는 물품이 할인?
            //No일 경우, 시작지점(=회원가입날짜)를 +1
            if(!tmp.containsKey(discount[start]))
                start++;
            //Yes일 경우, end를 1씩 늘려가며 10일 연속으로,
            //할인가능한 품목들이 있는지 확인
            else{
                //첫 번째 날 할인품목
                int cur = tmp.get(discount[start]);
                tmp.put(discount[start], cur-1);
                //두 번째 날부터,,,
                boolean register = true;
                for(int i = 1; i < 10; i++){
                    int end = start + i;
                    // 1. 할인품목에 원하는 제품이 없거나,
                    // 2. 이미 해당 제품의 할인날짜수가 원하는 개수를 충족했을 경우
                    // => want를 연속 10일 안에 다 할인받을 수 없으므로 break
                    if(!tmp.containsKey(discount[end]) || tmp.get(discount[end]) == 0){
                        register = false;
                        break;
                    }
                    //할인품목에 원하는 제품 있을 경우, want에서 개수 1씩 차감
                    cur = tmp.get(discount[end]);
                    tmp.put(discount[end], cur-1);
                }
                //register true면 +1
                answer = (register)?(answer+1):answer;
                //초기화
                tmp.putAll(wantList);
                start++;
            }
        }
        
        return answer;
    }
}