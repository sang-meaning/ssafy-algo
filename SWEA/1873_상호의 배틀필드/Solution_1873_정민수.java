
import java.util.*;

public class 상호의배틀필드 {
	
	static String user_input = "UDLR";
	static char[] direction = {'^', 'v', '<', '>'};
	//상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int h,w;
	static char[][] map; 
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for(int test_case=1; test_case<=t; test_case++) {
			
			
			
			
			h = sc.nextInt();
			w = sc.nextInt();
			
			map = new char[h][w];
			
			int current_x = -1;
			int current_y = -1;
			
			for(int i=0; i<h; i++) {
				String str = sc.next();
				for(int j=0; j<w; j++) {
					map[i][j] = str.charAt(j);
					
					if(map[i][j]=='<' || map[i][j]=='>' || map[i][j]=='^' || map[i][j]=='v') {
						current_x = i;
						current_y = j;
					}
				}
			}
			
			//사용자 입력개수
			int n = sc.nextInt();
			String s_input = sc.next();
			
			for(int i=0; i<n; i++) {
				char input = s_input.charAt(i);
				
				if(input == 'S') {
					for(int j=0; j<4; j++) {
						if(direction[j] == map[current_x][current_y]) {
							dfs(j, current_x, current_y);
							break;
						}
					}
				}else {
					int index = user_input.indexOf(input);
					
					map[current_x][current_y] = direction[index];
					
					int nx = current_x+dx[index];
					int ny = current_y+dy[index];
					if(nx>=0 && nx<h && ny>=0 && ny<w && map[nx][ny] == '.') {
						map[current_x][current_y] = '.';
						map[nx][ny] = direction[index];
						
						current_x = nx;
						current_y = ny;
					}
				}
			}
			
			
			
			//출력
			
			System.out.print("#"+test_case+" ");
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			
			
			
		}
	}
	
	
	// 포탄발사
	public static void dfs(int index, int x, int y) {
		
		
		int nx = x;
		int ny = y;
		
		while(true) {
			nx+=dx[index];
			ny+=dy[index];
			
			if(nx>=0 && nx<h && ny>=0 && ny<w && map[nx][ny] != '#') {
				if(map[nx][ny] == '*') {
					map[nx][ny] = '.';
					break;
				}
				
				continue;
			}else break;
		}
	}

}
/*

맵 구성요소 
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)


사용자 동작
U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.

*/