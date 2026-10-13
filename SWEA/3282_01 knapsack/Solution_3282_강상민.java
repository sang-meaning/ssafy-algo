import java.io.*;
import java.util.*;

public class Solution_3282_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;
    static int N,K; // 물건 개수, 가방 부피
    static int[] volume;
    static int[] value;
    static int[][] d;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            volume = new int[N+1];
            value = new int[N+1];
            d = new int[N+1][K+1];

            for (int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());

                volume[i] = Integer.parseInt(st.nextToken());
                value[i] = Integer.parseInt(st.nextToken());
            }

            for (int i=1; i<=N; i++) {
                for (int k=1; k<=K; k++) { // 무게
                    if (volume[i] > k) {
                        d[i][k] = d[i-1][k];
                    } else {
                        d[i][k] = Math.max(d[i-1][k], d[i-1][k-volume[i]] + value[i]);
                    }
                }
            }

            sb.append("#"+t+" "+d[N][K]).append("\n");




        }
        System.out.println(sb);
        
    }
}
