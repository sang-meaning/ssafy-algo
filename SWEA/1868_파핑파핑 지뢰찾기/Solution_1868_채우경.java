package samsung01;

import java.io.BufferedReader;
import java.util.*;
import java.io.InputStreamReader;

public class Solution {

    static int[] dx = {0, 0, 1, -1,1,1,-1,-1};
    static int[] dy = {1, -1, 0, 0,-1,1,1,-1};
    static int N;
    static int K;
    static char[][] arr;
    static List<int[]> cores;
    static int maxLen;
    static boolean[][] visited;
		static boolean[][] isZero; // 주변 8방향에 지뢰가 0개인지 여부를 저장하는 flag 배열
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
        	N = Integer.parseInt(br.readLine());
            isZero = new boolean[N][N];
            visited = new boolean[N][N];
        	
        	arr= new char[N][N];
        	
        	for(int i=0;i<N;i++) {
        		
        		String s = br.readLine();
        		for(int j=0;j<N;j++) {
        			
        			arr[i][j]= s.charAt(j);
        		}
        		
        	}
        	
        	for(int i=0;i<N;i++) {
        		for(int j=0;j<N;j++) {
        			
        			if(arr[i][j]=='.') {
        				boolean flag=false;
        				for(int d=0;d<8;d++) {// 0~7 일 때 
    		
				    	 	int nx=i+dx[d];
				        	int ny=j+dy[d];
				    		
				    		if(nx<0||ny<0||nx>=N||ny>=N)
				    			continue;
				    		
				    		if(arr[nx][ny]=='*') {
				    			flag=true;
				    			break;
				    		}
        				}
        			if(!flag) {
        				isZero[i][j]=true;//지뢰가 없을때 iszero배열의 트루 설정
        			}
        			}
        			
        		}
        	}
        	
        	int click=0;
        	
        	for(int i=0;i<N;i++) {
        		for(int j=0;j<N;j++) {
        			if(arr[i][j]=='.'&&isZero[i][j]&&!visited[i][j]) {// 전체 순회하며 .이고 주변에 지뢰없고,방문하지않은곳
        				click++;
        				dfs(i,j);
        			}
        		}
        	}

        	for(int i=0;i<N;i++) {
        		for(int j=0;j<N;j++) {
        			if(arr[i][j]=='.'&&!visited[i][j]) {//남은 짜바리들
        				click++;
        			}
        		}
        	}
        	
        	System.out.println("#"+t+" "+click);
        	/*
        	 * 변이나 꼭지점이 맞닿아있는 최대 8칸에 대해 0~8로 표현
        	 * 0이면 8방향 지뢰 없음
        	 * 지뢰는 * 없는칸은.
        	 * 클릭한 지뢰가 없는칸 0 박고 -> dfs로 이어져야함
        	 * 
        	 *  . . x
        	 *  . . x
        	 *  x x .
        	 *  
        	 *  1. 8방향이 아무것도 없는 지점을 다 찾고 - 있으면 주변 값들 숫자값넣기
        	 *  2. 남은 개별 "."들 카운트
        	 *  3. 
        	 *  */
        	
        }
    }
    static void dfs(int x,int y) {    
    	visited[x][y]=true;
    	for(int d=0;d<8;d++) {// 0~7 일 때 
    		
    	 	int nx=x+dx[d];
        	int ny=y+dy[d];
    		
    		if(nx<0||ny<0||nx>=N||ny>=N)
    			continue;
    		
    		if(arr[nx][ny]=='.'&&!visited[nx][ny]) {
    			visited[nx][ny]=true;
    			
    			if(isZero[nx][ny])
    				dfs(nx,ny);
    		}
    			
    		
    		
		}
    	
    	
    }
    
 
    

}
