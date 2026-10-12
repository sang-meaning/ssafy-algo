package ysson.D5;
import java.io.*;

public class 현주가좋아하는제곱근놀이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int testcase = 1; testcase <= T; testcase++){
            long N = Long.parseLong(br.readLine());
            long cnt = 0;
            while(N != 2){
                long n = (long)Math.sqrt(N);
                // 안나눠짐
                if(n != Math.sqrt(N)){
                    long origin = N;
                    N = (n+1)*(n+1);
                    cnt += (N-origin);
                }
                
                cnt++;
                N = (long)Math.sqrt(N);
            }
            System.out.println("#" + testcase + " " + cnt);
        }
    }
}

// 가까운 제일 작은 제곱수 찾기

// 한 숫자당 제곱근 씌우고
// 자연수 아니면 그 보다 큰 가까운 자연수의 제곱만큼 더하고
// 자연수면 나누고