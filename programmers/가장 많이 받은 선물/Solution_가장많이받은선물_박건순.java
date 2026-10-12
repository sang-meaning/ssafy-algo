import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[] friends, String[] gifts) {
	        Map<String,Map<String,Integer>> giftlist = new HashMap<>();
	        Map<String,Integer> giverlist = new HashMap<>();
	        Map<String,Integer> receiverlist = new HashMap<>();
	        Map<String,Integer> answerlist = new HashMap<>();
	       
	        for(String f : friends) {
	        	answerlist.put(f,0);
	        }
	        
	        for(String gift : gifts) {
	        	String[] list = gift.split(" ");
	        	giftlist.computeIfAbsent(list[0], k-> new HashMap<>()).merge(list[1],1,Integer::sum);
	        	giverlist.merge(list[0], 1,Integer::sum);
	        	receiverlist.merge(list[1], 1, Integer::sum);
	        }
	        
	        for(int i = 0; i<friends.length;i++) {
	        	for(int j=i+1; j<friends.length;j++) {
	        		String A = friends[i];
	        		String B = friends[j];
	        		
	        		int Agift = giftlist.getOrDefault(A, new HashMap<>()).getOrDefault(B,0);
	        		int Bgift = giftlist.getOrDefault(B, new HashMap<>()).getOrDefault(A,0);
	        		if(Agift > Bgift) {
	        			answerlist.merge(A, 1, Integer::sum);
	        		}else if(Agift < Bgift) {
	        			answerlist.merge(B, 1, Integer::sum);
	        		} else {
	        			int Ascore = giverlist.getOrDefault(A, 0) - receiverlist.getOrDefault(A, 0);
	        			int Bscore = giverlist.getOrDefault(B, 0) - receiverlist.getOrDefault(B, 0);
                        
	        			if(Ascore > Bscore) {
	        				answerlist.merge(A, 1, Integer::sum);
	        			} else if(Ascore < Bscore) {
	        				answerlist.merge(B, 1, Integer::sum);
	        			}
	        		}
	        	}
	        }
	        int answer = Collections.max(answerlist.values());
	        return answer;
	    }
}