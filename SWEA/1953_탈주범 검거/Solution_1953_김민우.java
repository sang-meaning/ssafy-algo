import java.util.*;
import java.io.*;

class Solution
{
    static int[][] map;
  	static boolean[][] visited;
    //파이프별 연결가능 방향
  	static boolean[][] pipe = {{},
    	                        	 {true, true, true, true},
        	                     	 {false, true, false, true},
            	               	 	 {true, false, true, false},
                	            	 {false, true, true, false},
                    	         	 {false, false, true, true},
                        	     	 {true, false, false, true},
                            	 	 {true, true, false, false}};
  	static int[][] delta = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
  	static int T, N, M, R, C, L;
	static int ans;
    
  	static BufferedReader br;
  	static StringTokenizer st;
    
	public static void main(String args[]) throws Exception
	{
		br = new BufferedReader(new InputStreamReader(System.in));
    	st = new StringTokenizer(br.readLine());

    	T = Integer.parseInt(st.nextToken());
    
    	for(int test_case = 1; test_case <= T; test_case++){
        	st = new StringTokenizer(br.readLine());
        
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	R = Integer.parseInt(st.nextToken());
        	C = Integer.parseInt(st.nextToken());
        	L = Integer.parseInt(st.nextToken());

        	map = new int[N][M];
        	visited = new boolean[N][M];
        
        	for(int  i = 0; i < N; i++){
            	st = new StringTokenizer(br.readLine());
            	for(int j = 0; j < M; j++){
                	map[i][j] = Integer.parseInt(st.nextToken());
                	if(i == R && j == C)
                    	visited[i][j] = true;
                	else
                    	visited[i][j] = false;
            	}
       	 	}

        	int cnt = 1;
        	ans = 1;
        	Queue<Node> q = new LinkedList<>();
            q.offer(new Node(R, C, 1));
            while(!q.isEmpty()){
            	Node cur = q.poll();
                if(cur.time == L)
                    continue;
                else{
                	for(int d =0; d <4; d++){
                        if(!pipe[map[cur.r][cur.c]][d])
                            continue;
                    	int nr = cur.r + delta[d][0];
                        int nc = cur.c + delta[d][1];
                    	if(!isIn(nr,nc) || map[nr][nc] == 0 || visited[nr][nc])
                            continue;
                        if(pipe[map[nr][nc]][(d+2)%4]){
                        	visited[nr][nc] = true;
                            q.add(new Node(nr, nc, cur.time+1));
                            ans++;
                        }
                    }
                }
            
            }
        	System.out.printf("#%d %d\n", test_case, ans);
    	}
	}
	public static class Node{
    	int r;
        int c;
        int time;
        
        Node(int r, int c, int time){
        	this.r = r;
            this.c = c;
            this.time = time;
        }
    }
  	public static boolean isIn(int i, int j){
    	return i >= 0 && i < N && j >= 0 && j < M;
  	}
}