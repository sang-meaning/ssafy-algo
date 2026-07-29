import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine());

            int[] tree = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            int max = 0;
            for (int i = 0; i < N; i++) {
                tree[i] = Integer.parseInt(st.nextToken());
                max = Math.max(max, tree[i]);
            }
            int day=0;
            int odd=0;
            int even=0;
            int need=0;
            for(int i = 0; i< N; i++){
                need += max-tree[i];
            }
            if(need==0){
                System.out.println("#"+tc+" "+ 0);
                continue;
            }
            while(true){

                day++;

                if(day%2!=0){
                    odd++;
                }
                else{
                    even++;
                }
                int mustodd=0;
            	int musteven = 0;
                for(int a : tree){
                    mustodd += (max-a)%2;
                    musteven += (max-a)/2;
                }

                if(mustodd>odd) continue;

                int extraodd = odd- mustodd;

                musteven-= extraodd/2;
                if(musteven<=even)
                    break;
            }
            sb.append("#").append(tc).append(" ").append(day).append("\n");
        }

        System.out.print(sb);
    }
}