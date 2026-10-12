
import java.io.*;
import java.util.*;
public class  Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= T; testcase++) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] diff = new int[n];
			int max = 0;
			for(int i = 0; i<n; i++) {
				int tree = Integer.parseInt(st.nextToken());
				diff[i] = tree;
				max = Math.max(max, tree);
			}
			int one = 0;
			int two = 0;
			for(int i = 0; i<n; i++) {
				diff[i] = max - diff[i];
				
				one += diff[i] % 2; //최소 필요한 1개수 , 여기서 만들어진 1을 2로 바꾸면 안됨 
				two += diff[i] / 2;
			}
			
			// 균형맞출 수 있는 경우 -> 2를 1로 분해
			while(two > 1 + one) {
				one+=2;
				two--;
			}

			int answer = 0;
			if(one > two) answer = one * 2 -1;
			else answer = two * 2;
			System.out.println("#" + testcase + " " + answer);
		}
	}
}