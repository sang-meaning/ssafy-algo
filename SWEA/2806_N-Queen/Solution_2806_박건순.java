import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static int[][] board;
    static int answer=0;
	public static void main(String args[]) throws Exception
	{

		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt(); 
		for(int test_case = 1; test_case <= T; test_case++)
		{
            answer  = 0;
            int N = sc.nextInt();
            board = new int[N][N];
            dfs(0);

            System.out.println("#"+test_case+" "+answer);
		}
	}
    static void dfs(int row){
        if(row == board.length){
            answer++;
            return;
        }

        for(int col=0;col<board.length;col++){
            if(!isPossible(row,col)){
                continue;
            }
            board[row][col] = 1;
            dfs(row+1);
            board[row][col] = 0;
        }
    }
    static boolean isPossible(int row, int col){
        for(int i=0;i<row;i++){
            if(board[i][col] == 1){
                return false;
            }
        }
        for(int i=0;i<row;i++){
            if(col - (row-i) >= 0 && board[i][col-(row-i)] == 1){
                return false;
            }
            if(col + (row-i) < board.length && board[i][col+(row-i)] == 1){
                return false;
            }
        }
        return true;
    }
}