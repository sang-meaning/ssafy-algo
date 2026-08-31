package algorithm;

import java.util.*;
import java.io.*;
public class Solution_1225_손영주 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;

		for (int test_case_num = 1; test_case_num <= T; test_case_num++) {
			
			int testNum = Integer.parseInt(br.readLine());

			String line = br.readLine();
			StringTokenizer st = new StringTokenizer(line, " ");
			
			
			Queue<Integer> password = new ArrayDeque<>();
			for(int i = 0; i<8; i++) {
				password.add(Integer.parseInt(st.nextToken()));
			}
			
			int count = 0;
			while(true) {
				count += 1;
				if(count > 5) {
					count = 1;
				}
				int temp = password.poll() - count;
				if (temp <= 0) {
					password.add(0);
					break;
				}
				password.add(temp);
			}
			
			String answer = "";
			for(int i = 0; i<8; i++) {
				answer += password.poll() + " ";
			}
			
			System.out.println("#" + test_case_num + " " + answer);
		}
	}
}