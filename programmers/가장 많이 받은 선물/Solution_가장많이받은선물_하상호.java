package JAVA_08_prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        HashMap<String, Integer> cost = new HashMap<>();
        HashMap<String, HashMap<String, Integer>> members = new HashMap<>();
        HashMap<String, Integer> gift_per_member = new HashMap<>();
        
        for(String name : friends) {
        	cost.put(name, 0);
        	members.put(name, new HashMap<>());
        	gift_per_member.put(name, 0);
        }
        
        for (String gift : gifts) {
        	String[] persons = gift.split("\\s+");
        	
        	String a_person = persons[0];
        	String b_person = persons[1];
        	
        	cost.put(a_person, cost.get(a_person) + 1);
        	cost.put(b_person, cost.get(b_person) - 1);
        	
        	HashMap<String, Integer> giftMap = members.get(a_person);
        	
            giftMap.put(
                    b_person,
                    giftMap.getOrDefault(b_person, 0) + 1
            );

        }
        
        
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
            	String a = friends[i];
            	String b = friends[j];
            	
                int a2b = members.get(a).getOrDefault(b, 0);
                int b2a = members.get(b).getOrDefault(a, 0);
                
        		if (a2b > b2a) {
        			gift_per_member.put(a, gift_per_member.get(a) + 1);
        		}
        		
        		else if (a2b < b2a) {
        			gift_per_member.put(b, gift_per_member.get(b) + 1);
        		}
        		
        		else {
                    if (cost.get(a) > cost.get(b)) {
                    	gift_per_member.put(a, gift_per_member.get(a) + 1);
                    } 
                    else if (cost.get(a) < cost.get(b)) {
                    	gift_per_member.put(b, gift_per_member.get(b) + 1);
                    }
        		
        		}            	
            	
            }

        }
        
        int answer = 0;
    	for(String n : friends) {
    		answer = Math.max(answer, gift_per_member.get(n));
    	}
    	
        return answer;
    }
}

public class Solution_가장많이받은선물_하상호 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );
        
        String[] line = br.readLine()
        		.replace("\"", "")
        		.split("\\],\\s*\\[");
        
        String[] friends = line[0]
        		.replace("[", "")
        		.replace("]", "")
        		.split(",\\s*");
        String[] gifts = line[1]
        		.replace("[", "")
        		.replace("]", "")
        		.split(",\\s*");
        
        
        System.out.println(Arrays.toString(friends));
        System.out.println(Arrays.toString(gifts));
        
        Solution sl = new Solution();
        int result = sl.solution(friends, gifts);
        System.out.println(result);
    }
}
