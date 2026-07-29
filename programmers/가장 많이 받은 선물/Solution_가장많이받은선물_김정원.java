package programmers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Solution_가장많이받은선물_김정원 {
	public class Person {
		String name;
		Map<String, Integer> giftHistory = new HashMap<>();
		int sum = 0;
		int giftCount = 0;
		int nextSum = 0;
		
		public Person(String name) {
			this.name = name;
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input3.txt"));
		Scanner sc = new Scanner(System.in);	
		String[] f = sc.nextLine().split(" ");

		String[] tokens = sc.nextLine().split(" ");
		String[] g = new String[tokens.length / 2];
		int index = 0;
		for (int i = 0; i < tokens.length - 1; i+=2) {
		    g[index++] = tokens[i] + " " + tokens[i + 1];
		}
		Solution_가장많이받은선물_김정원 i = new Solution_가장많이받은선물_김정원();
		System.out.println(i.solution(f, g));
	}
	
	
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        // 선물을 준것을 map<String, Map<String, Integer>> 로
        // 첫번째 key 는 준사람 이름 두번째 key 는 받은 사람 이름으로
        // value 값은 선물을 보낸 누적 갯수를 의미한다
        Map<String, Person> persons = new HashMap<>();
        for (String name : friends) {
        	persons.put(name, new Person(name));
        }
        
        // 선물을 주고받은 내역을 저장
        // 1. 선물을 보낸 사람의 giftHistory 에 누구에게 선물을 몇개 보냈는지 기록
        // 2. 선물 보낸 사람은 선물 지수를 +1, 반대로 받은 사람은 -1 giftCount 에 기록
        // 3. 선물 받은 사람은 누적 받은 선물의 갯수를 sum 에 기록
        for (int i = 0; i < gifts.length; i++) {
        	String[] temp = gifts[i].split(" ");
        	String giver = temp[0];
        	String recipient = temp[1];
        	
        	// 선물 내역을 저장
        	persons.get(giver).giftHistory.merge(recipient, 1, Integer::sum);
        	persons.get(giver).giftCount += 1;
        	persons.get(recipient).giftCount -= 1;
        	persons.get(recipient).sum += 1;
        }
        
        // 선물 기록이 정리가 완료 되면 탐색을 시작한다
        // 두 사람이 선물을 얼마나 주고 받았는지 갯수를 비교한다
        
        List<String> keys = new ArrayList<>();
        String aName = "";
        String bName = "";
        persons.keySet().stream().forEach(t -> keys.add(t));

        // persons.keySet().stream().forEach(k -> System.out.println(k + " : : " + persons.get(k).giftHistory));
        
        for (int a = 0; a < keys.size(); a++) {
        	for (int b = a + 1; b < keys.size(); b++) {
        		aName = keys.get(a);
        		bName = keys.get(b);
        		int atob = persons.get(aName).giftHistory.getOrDefault(bName, -1);
        		int btoa = persons.get(bName).giftHistory.getOrDefault(aName, -1);
                // 1. 비교하여 많은 사람은 적은 사람에게 선물을 1 하나 받는다
        		if (atob > btoa) {
        			//System.out.println(aName + " 1-> " + bName);
        			persons.get(aName).nextSum += 1;
        		} else if (atob < btoa) {
        			//System.out.println(aName + " 2-> " + bName);
        			persons.get(bName).nextSum += 1;
        		} else {
        	        // 2. 기록이 없거나, 주고 받은 수가 같다면 선물 지수를 비교한다
        	        // 선물 지수가 큰 사람이 작은 사람에게 선물을 받는다
        	        // 2-1. 선물 지수까지 같다면 선물을 주고 받지 않는다
            		if (persons.get(aName).giftCount > persons.get(bName).giftCount) {
            			//System.out.println(aName + " 3-> " + bName);
            			persons.get(aName).nextSum += 1;
            		} else if (persons.get(aName).giftCount < persons.get(bName).giftCount) {
            			//System.out.println(aName + " 4-> " + bName);
            			persons.get(bName).nextSum += 1;        			
            		} else {
            		}
        		}
        	}
        }
        // System.out.println();
        int max = 0;
        for (int i = 0; i < keys.size(); i++) {
        	Person p = persons.get(keys.get(i));
        	//System.out.println(String.format("name:%s nextSum:%d", keys.get(i), p.nextSum));
        	max = Math.max(max, p.nextSum);
        }
        answer = max;
        return answer;
    }
/*

*/
}
