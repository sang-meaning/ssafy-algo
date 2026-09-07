import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
	//상하좌우
	//static int[]dr = {-1,1,0,0};
	//static int[]dc = {0,0,-1,1};
	static char[][] fieldmap;
	static int tank_r;
	static int tank_c;
	static int H;
	static int W;
	static char tank_direction;
	static void action(char symbol) {
		if(symbol == 'U') {
			tank_direction = '^';
			fieldmap[tank_r][tank_c] = '^';
			if( tank_r-1>=0 && fieldmap[tank_r-1][tank_c]=='.') {
				fieldmap[tank_r][tank_c] = '.';
				tank_r -= 1;
				fieldmap[tank_r][tank_c] = '^';
			}
		}
		else if(symbol == 'D') {
			tank_direction = 'v';
			fieldmap[tank_r][tank_c] = 'v';
			if( tank_r+1 < H && fieldmap[tank_r+1][tank_c]=='.') {
				fieldmap[tank_r][tank_c] = '.';
				tank_r += 1;
				fieldmap[tank_r][tank_c] = 'v';
			}
		}
		else if(symbol == 'L') {
			tank_direction = '<';
			fieldmap[tank_r][tank_c] = '<';
			if( tank_c-1 >= 0 && fieldmap[tank_r][tank_c-1]=='.') {
				fieldmap[tank_r][tank_c] = '.';
				tank_c -= 1;
				fieldmap[tank_r][tank_c] = '<';
			}
		}
		else if(symbol == 'R') {
			tank_direction = '>';
			fieldmap[tank_r][tank_c] = '>';
			if( tank_c+1 < W && fieldmap[tank_r][tank_c+1]=='.') {
				fieldmap[tank_r][tank_c] = '.';
				tank_c += 1;
				fieldmap[tank_r][tank_c] = '>';
			}
		}else {
			//shoot
			int next_tank_r = tank_r;
			int next_tank_c = tank_c;
			int dr = 0;
			int dc = 0;
			switch(tank_direction) {
			case '^':
				dr = -1;
				dc = 0;
				break;
			case 'v':
				dr = 1;
				dc = 0;
				break;
			case '<':
				dr = 0;
				dc = -1;
				break;
			case '>':
				dr = 0;
				dc = 1;
				break;
			}
			while(true) {
				next_tank_r += dr;
				next_tank_c += dc;
				if(next_tank_r<0 || next_tank_r>=H || next_tank_c<0 || next_tank_c>=W) {
					break;
				}
				else if(fieldmap[next_tank_r][next_tank_c]=='*') {
					fieldmap[next_tank_r][next_tank_c]='.';
					break;
				}
				else if(fieldmap[next_tank_r][next_tank_c]=='#') {
					break;
				}else {
					continue;
				}
			}
			
			
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner sc = new Scanner(System.in);

		
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			H = sc.nextInt();
			W = sc.nextInt();
			//필드맵 입력
			char [][] map = new char[H][W];
			for(int i=0; i<H; i++) {
				String line = sc.next();
				for(int j=0; j<W;j++) {
					map[i][j]=line.charAt(j);
					if(map[i][j]=='^' || map[i][j]=='v' || map[i][j]=='<' || map[i][j]=='>') {
						tank_r=i;
						tank_c=j;
						tank_direction = map[i][j];
					}
				}
			}
			fieldmap = map;
			//명령어 입력
			int N = sc.nextInt();
			String inputline = sc.next();
			char[] input_line = new char [inputline.length()];
			for(int k=0; k< inputline.length();k++) {
				input_line[k] = inputline.charAt(k);
				action(input_line[k]);
			}
			System.out.print("#"+tc+" ");
			for(int i=0; i<H; i++) {
				for(int j=0; j<W;j++) {
					System.out.print(fieldmap[i][j]);
					}
				System.out.println();
				}
			}
		}
}
