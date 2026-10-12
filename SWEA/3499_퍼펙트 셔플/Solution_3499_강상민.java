import java.util.*;
import java.io.*;


public class Solution_3499_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            int len = Integer.parseInt(br.readLine());
            sb.append("#"+t+" ");

            String[] arr = new String[len];
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<len; i++) {
                arr[i] = st.nextToken();
            }

            if (len %2== 0) {
                int mi = len/2;

                for (int i=0; i<mi; i++) {
                    sb.append(arr[i]+" ");
                    sb.append(arr[i+mi]+" ");
                }

                
            } else {
                int mi = len/2 + 1;

                for (int i=0; i<mi-1; i++) {
                    sb.append(arr[i]+" ");
                    sb.append(arr[i+mi]+" ");
                }
                sb.append(arr[mi-1]);
            }

            sb.append("\n");


        }

        System.out.println(sb);
        
    }
    
}
