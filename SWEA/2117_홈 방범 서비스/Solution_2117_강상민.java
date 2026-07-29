import java.util.*;
import java.io.*;

public class Solution_2117_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;
    static int N,M; // 도시크기, 집 비용
    static int[][] board;
    static int result = 0; // 손해x 최대 집의 개수

    public static void main(String[] args) throws IOException {

        // 집 : 마름모 중앙 이라고 믿고싶은데.. <- 그건 아닌듯
        // 방범의 중심을 board의 한 칸으로 맞추고 완탐
        // k=1 부터 2*k-1 > N 인 k까지?
        // n=20, k는 11까지 즉 k는 1~11

        // 시간 복잡도
        // 50 * 20 * 20 * 마름모 넓이 (board 다 포함하면 400) * 11 = 50 * 400 * 400 * 11 = 160만 * 55

        // k에 대한 마름모 만들고, 중심을 board에 맞춰 완탐하기

    

        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            result = 0;

            for (int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int maxK; // 탐색해야하는 k의 최댓값, 최솟값은 1
            maxK = 2*N;

            // k의 크기에 따라, 방범 마름모 만들고, 마름모 중심 찾아 board에 방범 범위 갱신, 이득 계산
            // k*2-1 크기 배열 만들기
            for (int k=1; k<=maxK; k++) {
                
                for (int i=0; i<N; i++) {
                    for (int j=0; j<N; j++) {
                        // i,j를 중심으로 하고 마름모 가진 새로운 배열을 k에 대해 만들기
                        int[][] dia = new int[N][N];
                        dia = makeD(i,j,k);

                        // N,N까지 돌면서 dia == 1 && board == 집 개수 판단 : *M 해서 수금 받고
                        // dia == 1 인 곳 개수만큼 비용 발행
                        // 이득 계산

                        int subscriber = 0;
                        int cost = k*k + (k-1)*(k-1) ;

                        // 방법 가입자수 
                        for (int a=0; a<N; a++) {
                            for (int b=0; b<N; b++) {
                                if (dia[a][b] == 1 && board[a][b] == 1) subscriber++;
                            }
                        }

                        int benefit = subscriber * M - cost;
                        if (benefit >= 0) {
                            result = Math.max(result, subscriber);
                        }
                    }
                }
            }

            sb.append("#"+t+" "+result).append("\n");

        }
        // tc 완료
        System.out.print(sb);

    }

    // i,j를 중심으로 갖는 마름모를 k에 대해 만들기 
    static int[][] makeD(int i, int j, int k) {
        int[][] arr = new int[N][N];

        for (int r=0; r<N; r++) {
            for (int c=0; c<N; c++) {
                // r,c 와 i,j의 멘하탄 거리 < k 인 arr[r][c]만 1로 초기화
                int distance = Math.abs(i-r) + Math.abs(j-c);
                if (distance < k) arr[r][c] =1;
            }
        }

        return arr;
    }
    
}
