import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1868 {
    static int T,N;
    static int[][] map;
    static boolean[][] visited;
    static int ans;
    static int[] dx = new int[] {0,0,1,-1,1,-1,1,-1};
    static int[] dy = new int[] {1,-1,0,0,1,-1,-1,1};
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static void dfs(int r, int c){
        for (int i =0; i<8; i++){
            if ((r+dx[i] >= 0 && r+dx[i] <N && c+dy[i] >= 0 && c+dy[i] < N) && visited[r+dx[i]][c+dy[i]] == false){ // 미방문 && 바운더리
                visited[r+dx[i]][c+dy[i]] = true;
                if (findMine(r+dx[i],c+dy[i]) != true){
                    dfs(r+dx[i],c+dy[i]);
                }
            }
        }
    }

    static boolean findMine(int r, int c){
        for (int i = 0; i<8; i++){
            if (r+dx[i] >= 0 && r+dx[i] <N && c+dy[i] >= 0 && c+dy[i] < N){
                if (map[r+dx[i]][c+dy[i]] == '*' ){
                    return true;
                }
            }
        }
        return false;
    }

    static void solve(){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if (!visited[i][j] && map[i][j] != '*' && findMine(i,j) == false){
                    visited[i][j] = true;
                    dfs(i,j);
                    ans++;
                }
            }
        }
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if (visited[i][j] != true && map[i][j] != '*' && findMine(i,j) == true){
                    visited[i][j] = true;
                    ans++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();

        T = nextInt();
        for (int tc = 1; tc<=T; tc++){
            
            N = nextInt();

            map = new int[N][N];
            visited = new boolean[N][N];

            for(int[] rows: map){
                String s = next();
                for(int i = 0; i<N; i++){
                    rows[i] = s.charAt(i);
                }
            } 

            solve();
            sb.append("#").append(tc).append(" ").append(ans).append('\n');
            ans = 0;
        }
        System.out.print(sb);
    }

    static String next() throws IOException{
        if (st == null || !st.hasMoreTokens()){
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
    
    static int nextInt() throws IOException{ // 잦은 입력 호출 함수
        return Integer.parseInt(next());
    }
}
