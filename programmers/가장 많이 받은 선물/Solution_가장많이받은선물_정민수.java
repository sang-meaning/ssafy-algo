import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        

		
		Map<String, Integer> change = new HashMap<>(); // 교환 ex) muzi frodo, 1 
		
		Map<String, int[]> total = new HashMap<>(); // 총 지수
		
		
		int[] max = new int[friends.length];
		
		
		
		//기능
		
		for (int i = 0; i < gifts.length; i++) {
		    String[] names = gifts[i].split(" ");
		    String from = names[0];
		    String to = names[1];
		    
		    String str = from + " " + to;
		    change.put(str, change.getOrDefault(str, 0) + 1);
		    
		    int[] from_result = total.getOrDefault(from, new int[3]);
		    from_result[0] += 1;
		    total.put(from, from_result);
		    
		    int[] to_result = total.getOrDefault(to, new int[3]);
		    to_result[1] += 1;
		    total.put(to, to_result);
		}
		
		int[] present = new int[friends.length];
		
		for(int i=0; i<friends.length; i++) {
			int[] arr = total.getOrDefault(friends[i], new int[3]);
			
			present[i] = arr[0]-arr[1];
			
			
		}
		
		
		//선물 계산
		for(int i=0; i<friends.length-1; i++) {
			
			for(int j=i+1; j<friends.length; j++) {
				String f1 = friends[i];
				String f2 = friends[j];
				
				String str = f1 + " " + f2;
				String str2 = f2 + " " + f1;
				int num = change.getOrDefault(str, 0);
				int num2 = change.getOrDefault(str2, 0);
			
				if(num>0 || num2>0) {
					if(num>num2) {
						max[i] += 1;
					}else if(num == num2){
						if(present[i] == present[j]) {
							continue;
						}else {
							if(present[i]>present[j]) {
								max[i] += 1;
							}else {
								max[j] += 1;
							}
						}
					}else{
						max[j] = max[j]+1;
					}
					
				}else {
					if(present[i] == present[j]) {
						continue;
					}else {
						if(present[i]>present[j]) {
							max[i] += 1;
						}else {
							max[j] += 1;
						}
					}
				}
				
				
			}
		}
		
		
	
		
		for(int i=0; i<max.length; i++) {
			if(answer<max[i]) {
				answer=max[i];
			}
		}
		
		System.out.println(answer);
        
        
        return answer;
    }
}