import java.util.*;
import java.io.*;

public class Solution_기능개발_임성진 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			
			List<Integer> list = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			
			while(st.hasMoreTokens()) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			int[] speeds = new int[list.size()];
			
			speeds = list.stream().mapToInt(i -> i).toArray();
			
			list = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			
			while(st.hasMoreTokens()) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			int[] progresses = new int[list.size()];
			
			progresses = list.stream().mapToInt(i -> i).toArray();
			
			int[] s = solution(progresses, speeds);
			
			for (int j : s) {
				System.out.print(j + " ");
			}
			System.out.println();
		}

	}

	static int[] solution(int[] progresses, int[] speeds) {
	    List<Integer> answer = new ArrayList<>();
	    int standard = days(progresses[0], speeds[0]);
	    int count = 1;

	    for (int i = 1; i < progresses.length; i++) {
	        int day = days(progresses[i], speeds[i]);
	        if (day <= standard) {
	            count++;
	        } else {
	            answer.add(count);
	            standard = day;
	            count = 1;
	        }
	    }
	    answer.add(count);
	    return answer.stream().mapToInt(Integer::intValue).toArray();
	}

	static int days(int progress, int speed) {
	    return (100 - progress + speed - 1) / speed;
	}

}
