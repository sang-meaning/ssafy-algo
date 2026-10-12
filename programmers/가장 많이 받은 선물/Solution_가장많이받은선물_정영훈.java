
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Solution_가장많이받은선물_정영훈 {
    


    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        Map<String, Map<String, Integer>> givers=new HashMap<>();
        Map<String, Integer> nextPresent=new HashMap<>();
        Map<String, Integer> presentPoint=new HashMap<>();
        for (String string : friends) {
            nextPresent.put(string, 0);
            presentPoint.put(string, 0);
            givers.put(string, new HashMap<>());

        }
        for(int i=0; i<gifts.length; i++){
            StringTokenizer st=new StringTokenizer(gifts[i]);
            String giver=st.nextToken();
            String receiver=st.nextToken();

            presentPoint.put(giver, presentPoint.get(giver)+1);
            presentPoint.put(receiver, presentPoint.get(receiver)-1);

            givers.get(giver).put(receiver,givers.get(giver).getOrDefault(receiver, 0)+1);
            
        }
        for (int f1=0; f1<friends.length; f1++){
                String friend1=friends[f1];
                for (int f2=f1+1; f2<friends.length; f2++) {
                    String friend2=friends[f2];
                    
                    Integer from1To2=givers.get(friend1).getOrDefault(friend2,0);
                    Integer from2To1=givers.get(friend2).getOrDefault(friend1,0);
                    if (from1To2>from2To1) {
                        nextPresent.put(friend1, nextPresent.get(friend1)+1);
                    }else if (from1To2<from2To1) {
                        nextPresent.put(friend2, nextPresent.get(friend2)+1);
                        
                    }else{
                        Integer friend1_point=presentPoint.get(friend1);
                        Integer friend2_point=presentPoint.get(friend2);
                        if (friend1_point>friend2_point) {
                            nextPresent.put(friend1,nextPresent.get(friend1)+1);
                        }else if (friend1_point<friend2_point) {
                            nextPresent.put(friend2,nextPresent.get(friend2)+1);
                        }
                        
                    }
                }
            }
        int maxPoint=0;
        for (String friend : friends) {
            maxPoint=Math.max(maxPoint, nextPresent.get(friend));
        }
        answer=maxPoint;
        System.out.println(answer);


        return answer;
    }
    

    
}
