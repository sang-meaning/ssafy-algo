package pratice;

import java.io.*;
import java.util.*;

public class Solution_6808_규영이와인영이카드게임 {
	
	private static int[] gArr, iArr;
	private static int M = 9;
	private static int winCnt = 0;
	
	public static void main(String[] args) throws Exception{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int TC = Integer.parseInt(br.readLine());
	for (int tc =1; tc <= TC; ++tc) {
		boolean[] isPicked = new boolean[19];
		gArr = new int[M];
		iArr = new int[M];
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < M; i++) {
			gArr[i] = Integer.parseInt(st.nextToken());
			isPicked[gArr[i]] = true;
		}
		for (int i =1,j=0; i <19; i++) {
			if(!isPicked[i]) iArr[j++] = i;
		}
		
		winCnt = 0;
		permutation(0,0,0,0);
		System.out.println("#"+tc+" "+ winCnt+ " "+(362880-winCnt));
		
		}
	}
	
	static void permutation(int cnt, int flag, int gScore, int iScore) {
		if(cnt == M){
			if(gScore>iScore) ++winCnt;
			return;
		}
		
		for (int i = 0; i< M; i++) {
			if ((flag & 1<<i) != 0) continue;
			
			int sum = gArr[cnt] + iArr[i];
			permutation(cnt+1, flag | 1 << i, gScore + (gArr[cnt]>iArr[i]?sum:0), iScore+(gArr[cnt]<iArr[i]?sum:0));
		}
		
	}
}
