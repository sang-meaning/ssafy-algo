package practice;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Solution {
	static int count;
	static int N;
	static int M;
	static int[] isselected;
	static int sum;
	static int[][] notaccepted;
	
	static void backtracking(int start) {
		for(int i=0; i< M; i++) {
			if((isselected[notaccepted[i][0]]==1) && (isselected[notaccepted[i][1]])==1) {
				return;
			}
			continue;
		}
		count++;
		if (sum == N) {
			return;
		}

		
		for(int num=start;num<=N;num++) {
			isselected[num] = 1;
			sum += 1;
			
			backtracking(num+1);
			
			isselected[num] = 0;
			sum -= 1;
		}
	}
	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			sum = 0;
			count = 0;
			notaccepted = new int[M][2];
			isselected = new int[N+1];
			
			for(int i=0; i<M; i++) {
				notaccepted[i][0] = sc.nextInt();
				notaccepted[i][1] = sc.nextInt();
			}
			
			backtracking(1);
			System.out.println("#"+tc+" "+count);
			
		}
	}
}
