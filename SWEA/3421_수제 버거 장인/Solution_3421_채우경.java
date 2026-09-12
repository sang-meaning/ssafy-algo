import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int N;
    static int M;
    static int min;
    static boolean[][] bad; // 안 맞는 쌍 저장 (2차원 배열)
    static boolean[] selected; // 현재 선택된 재료들
    static int cnt=0;

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());//재료개수
            M = Integer.parseInt(st.nextToken());//궁합안맞는 재료 개수

            arr = new int[N];
            bad = new boolean[N + 1][N + 1];
            selected = new boolean[N + 1];

            for (int i=0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                bad[a][b] = true;
                bad[b][a] = true;
            }

            cnt=0;
            dfs(1);

            System.out.println("#"+t+" "+cnt);

        }
    }
    static void dfs(int idx){

        if(idx>N){
            cnt++;
            return;
        }


        dfs(idx+1);

        boolean canSelect = true;
        for (int i = 1; i < idx; i++) {
            if (selected[i] && bad[idx][i]) {
                canSelect = false; // 안 맞는 재료가 이미 선택되어 있음
                break;
            }
        }

        if (canSelect) {
            selected[idx] = true;  // idx번 재료 선택
            dfs(idx + 1);          // 다음 재료 진행
            selected[idx] = false; // 원상복구 (백트래킹)
        }

    }
}