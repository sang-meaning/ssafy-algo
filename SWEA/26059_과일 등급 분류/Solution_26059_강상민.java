import java.util.*;
import java.io.*;

/*
각 과일 무게를 오름 정렬했을 때, 두 지점을 고르고, 그보다 약간 큰 실수를 고르면 완전탐색
1000 C 2 경우의 수

겹치는 수가 많다면 map과 set 써야하는데, 어차피 최악의 경우 2중 for문만 50만, 그 안에서 또 for문 하면 5억

*/

public class Solution_26059_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;
    static int N, lo, hi;
    static int[] f;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());            
            lo = Integer.parseInt(st.nextToken());
            hi = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            result = Integer.MAX_VALUE;

            f = new int[N];
            for (int i=0; i<N; i++) {
                f[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(f);

            // 세팅 완료

            

            for (int i=0; i<N-1; i++) {
                for (int j=i+1; j<N; j++) {
                    double k1 = (double)f[i] + 0.1;
                    double k2 = (double)f[j] + 0.1;

                    int a=0,b=0,c=0; // 순서대로 왼쪽, 중간, 오른쪽 개수

                    if (k1 == k2) continue;

                    for (int k=0; k<N; k++) {
                        int now = f[k];

                        if (now < k1) a++;
                        else if (now > k1 && now < k2) b++;
                        else if (now > k2) c++;
                    }

                    // 분류 완료
                    if (a<lo || a> hi) continue;
                    if (b<lo || b> hi) continue;
                    if (c<lo || c> hi) continue;

                    int ma = Math.max(a,b);
                    ma = Math.max(ma, c);

                    int mi = Math.min(a,b);
                    mi = Math.min(mi, c);

                    int r = ma-mi;
                    if (r <0) result *= -1;
                    result = Math.min(result, r);



                    
                }
            }

            if (result == Integer.MAX_VALUE) {
                sb.append("#"+t+" "+"-1").append("\n");
            } else {
                sb.append("#"+t+" "+result).append("\n");
            }

        }

        // tc 종료
        System.out.println(sb);
        
    }
    
}
