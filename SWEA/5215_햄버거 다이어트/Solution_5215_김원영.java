package practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution { 
	static List<Integer> answer = new ArrayList<>();
	static void backtracking(int start,int[][] ingredient, int sum_cal, int sum_taste, int limit,int[] visited,int len) {
		if(sum_cal > limit) {
			return;
		}
		answer.add(sum_taste);
		
		for(int i=start;i<len;i++) {
			if (visited[i] == 1){
				continue;
			}
			visited[i] = 1;
			sum_cal += ingredient[i][1];
			sum_taste += ingredient[i][0];
			backtracking(i+1,ingredient, sum_cal, sum_taste, limit,visited,len);
			
			visited[i] = 0;
			sum_cal -= ingredient[i][1];
			sum_taste -= ingredient[i][0];
		}
		
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc =1 ; tc<= T; tc++) {
			answer.clear();
			int len = sc.nextInt();
			int limit = sc.nextInt();
			//[0]은 맛 점수 [1]은 칼로리 점수
			int[][] ingredient = new int[len][2];
			int sum_cal = 0;
			int sum_taste = 0;
			int []visited = new int[len];
			for(int i=0; i<len; i++) {
				ingredient[i][0] = sc.nextInt();
				ingredient[i][1] = sc.nextInt();
			}
			backtracking(0, ingredient, 0, 0, limit,visited,len);
			int max =0;
			for(int j=0; j< answer.size(); j++) {
				if(answer.get(j)>max) {
					max = answer.get(j);
				}
			}
			System.out.println("#"+tc+" "+max);
		}
	}
}
