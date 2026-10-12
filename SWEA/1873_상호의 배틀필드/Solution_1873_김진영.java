import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int T,N,M;
	static int[] map[];
	static int[] dx = {-1,1,0,0}; // 상 하 좌 우
	static int[] dy = {0,0,-1,1};
	
	static int w;
	static int state;
	static int cx,cy;
	
	public static void main(String[] args) throws Exception{
		T = Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			state = 0;
			map = new int[N][M];
			
			for(int i=0;i<N;i++) {
				String temp = br.readLine();
				for(int j=0;j<M;j++) {
					if(temp.charAt(j) == '.') {
						map[i][j] = 0;
					}else if(temp.charAt(j) == '*') {
						map[i][j] = 1;
					}else if(temp.charAt(j) == '#') {
						map[i][j] = 2;
					}else if(temp.charAt(j) == '-') {
						map[i][j] = 3;
					}else {
						map[i][j] = 4;
						cx = i;
						cy = j;
						
						if(temp.charAt(j) == '^') {
							state = 1;
						}else if(temp.charAt(j) == 'v') {
							state = 2;
						}else if(temp.charAt(j) == '<') {
							state = 3;
						}else if(temp.charAt(j) == '>') {
							state = 4;
						}  
					}
				}
			}

			
			w = Integer.parseInt(br.readLine());
			String command = br.readLine();
			
			for(int i=0;i<w;i++) {
				move(command.charAt(i));
			}

			sb.append("#"+test_case+" ");
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j] == 0) {
						sb.append(".");
					}else if(map[i][j] == 1) {
						sb.append("*");
					}else if(map[i][j] == 2) {
						sb.append("#");
					}else if(map[i][j] == 3) {
						sb.append("-");
					}else if(map[i][j] == 4) {
						if(state == 1) {
							sb.append("^");
						}else if(state == 2) {
							sb.append("v");
						}else if(state == 3) {
							sb.append("<");
						}else if(state == 4) {
							sb.append(">");
						}
					}
				}
				sb.append("\n");
				
			}
		}
		System.out.print(sb);
		
	}

	private static void move(char command) {
		if(command == 'S') {
			int nx = cx + dx[state-1];
			int ny = cy + dy[state-1];
			
			while(nx >= 0 && ny >= 0 && nx < N && ny < M) { 
				
				if(map[nx][ny] == 1) {
					map[nx][ny] = 0;
					break;
				}else if(map[nx][ny] == 2) {
					break;
				}
				
				nx += dx[state-1];
				ny += dy[state-1];
			}
		}else {
			if(command == 'U') {
				state = 1;
			}else if(command == 'D') {
				state = 2;
			}else if(command == 'L') {
				state = 3;
			}else if(command == 'R') {
				state = 4;
			}
			int nx = cx + dx[state-1];
			int ny = cy + dy[state-1];
			
			
			if(nx >= 0 && ny >= 0 && nx < N && ny < M) { // 범위 안 이라면
				
				if(map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 3) {
					
				}else {
					map[nx][ny] = 4;
					map[cx][cy] = 0;
					cx = nx;
					cy = ny;
				}				
			}
		}		
	}
}

/*

1U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
2D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
3L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
4R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.

문자	의미
0.	평지(전차가 들어갈 수 있다.)
1*	벽돌로 만들어진 벽
2#	강철로 만들어진 벽
3-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)

 */
