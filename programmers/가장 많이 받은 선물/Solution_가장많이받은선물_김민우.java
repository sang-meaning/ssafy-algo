import java.util.Map;
import java.util.HashMap;

class Solution {
    
    static Map<String, Map<String, Integer>> trList;
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        trList = new HashMap<>();
        
        //해시맵 초기화
        for(String me : friends){
            Map<String, Integer> myList = new HashMap<>();
            for(String other : friends){
                myList.put(other, 0);
            }
            trList.put(me, myList);
        }
        
        for(String tr : gifts){
            String[] people = tr.trim().split(" ");
            /* myP : 나와 상대방 간의 주고받은 선물 개수 차이 */
            /* "people[0/1] : { ..., peopl[1/0] : myP1 +/- 1 , ...}"로 업데이트*/
            int myP1 = trList.get(people[0]).get(people[1]);
            trList.get(people[0]).put(people[1], myP1+1);
            int myP2 = trList.get(people[1]).get(people[0]);
            trList.get(people[1]).put(people[0], myP2-1);
            
            /* totalP : 나와 상대방의 선물지수 */
            int totalP1 = trList.get(people[0]).get(people[0]);
            int totalP2 = trList.get(people[1]).get(people[1]);
            trList.get(people[0]).put(people[0], totalP1+1);
            trList.get(people[1]).put(people[1], totalP2-1);
        }
        
        for(String me : friends){
            int curSum = 0;
            int myP = trList.get(me).get(me);
            for(String other : friends){
                if(me == other) continue;
                /* flag = 나(me)와 상대방(other)간의 주고받은 선물 개수 차이*/
                int flag = trList.get(me).get(other);
                /* flag > 0일 경우, 내(me)가 상대방(other)에게 선물을 더 많이 준 경우이므로 다음달에 받게 될 선물 개수(curSum)에 +1 */
                if(flag > 0)
                    curSum++;
                /* flag = 0일 경우, 나(me)와 상대방(other)의 선물지수를 비교한 다음, 클 경우에만 다음달에 받게 될 선물 개수(curSum)에 +1 */
                if(flag == 0){
                    int otherP = trList.get(other).get(other);
                    if(myP > otherP)
                        curSum++;
                }
            }
            if(curSum > answer) answer = curSum;
        }
                
        return answer;
    }
}