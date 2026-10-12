import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static int N;
	static int[][] map;
	static int[][] clone_map;
	static int[][] prc;
	static int prc_count;
	static int[] dir;
	static int min_num;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int maxCore;
	public static void combination(int depth, int connected) {
	    // 남은 코어를 전부 연결해도 최대 연결 수에 못 미침
	    if (connected + (prc_count - depth) < maxCore) {
	        return;
	    }

	    if (depth == prc_count) {
	        int count = 0;

	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if (map[i][j] == -1) {
	                    count++;
	                }
	            }
	        }

	        if (connected > maxCore) {
	            maxCore = connected;
	            min_num = count;
	        } else if (connected == maxCore) {
	            min_num = Math.min(min_num, count);
	        }

	        return;
	    }

	    for (int i = 0; i < 4; i++) {
	        if (check(depth, i)) {
	            line(true, depth, i);
	            combination(depth + 1, connected + 1);
	            line(false, depth, i);
	        }
	    }

	    // 연결하지 않는 경우도 반드시 탐색
	    combination(depth + 1, connected);
	}
	public static void line(boolean flag, int depth, int direct) {
		int cx = prc[depth][0];
		int cy = prc[depth][1];
		if(flag == true) {
			while(true) {
				int nx = cx + dx[direct];
				int ny = cy + dy[direct];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
					return;
				}
				map[nx][ny] = -1;
				cx = nx;
				cy = ny;
			}
		}else {
			while(true) {
				int nx = cx + dx[direct];
				int ny = cy + dy[direct];
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
					return;
				}
				map[nx][ny] = 0;
				cx = nx;
				cy = ny;
			}
		}
		
	}
	public static boolean check(int depth, int direct) {
		int cx = prc[depth][0];
		int cy = prc[depth][1];
		while(true) {
			int nx = cx + dx[direct];
			int ny = cy + dy[direct];
			if(nx == 0 || ny == 0 || nx == N-1 || ny == N-1) {
				if(map[nx][ny] == 1 || map[nx][ny] == -1) {
					return false;
				}
				return true;
			}
			if(map[nx][ny] == 1 || map[nx][ny] == -1) {
				return false;
			}
			cx = nx;
			cy = ny;
		}
	}
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
            maxCore = 0;
			N = sc.nextInt();
			map = new int[N][N];
			prc_count = 0;
			min_num = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j] == 1) {
						if(!(i == 0 || j == 0 || i == N - 1 || j == N - 1)) {
							prc_count++;
						}
					}
				}
			}
			prc = new int[prc_count][2];
			int count = 0;
			dir = new int[prc_count];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 1) {
						if(!(i == 0 || j == 0 || i == N - 1 || j == N - 1)) {
							prc[count][0] = i;
							prc[count][1] = j;
							count++;
						}
					}
				}
			}
			combination(0, 0);
			System.out.println("#" + test_case + " " + min_num);

		}
	}
}