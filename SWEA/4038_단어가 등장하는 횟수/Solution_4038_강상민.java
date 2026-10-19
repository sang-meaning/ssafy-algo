import java.util.*;
import java.io.*;

public class Solution_4038_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int T;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            char[] text = br.readLine().toCharArray();
            char[] voc = br.readLine().toCharArray();

            int[] pi = getPi(voc);
            int cnt = kmp(text, voc, pi);

            sb.append("#"+t+" "+cnt).append("\n");
        }

        System.out.println(sb);
   
    }

    static int[] getPi(char[] v) {
        int m = v.length;
        int[] pi = new int[m];
        int j=0;
        for (int i=1; i<m; i++) {
            while(j>0 && v[i] != v[j]) 
                j = pi[j-1];

            if (v[i] == v[j]) {
                j++;
                pi[i] = j;
            }
        }
        return pi;
    }

    static int kmp(char[] t, char[] v, int[] pi) {
        int n = t.length;
        int m = v.length;
        int j=0;
        int cnt=0;

        for (int i=0; i<n; i++) {
            while(j>0 && t[i] != v[j])
                j = pi[j-1];
            if (t[i] == v[j]) {
                if (j==m-1) { // 단어 매칭 성공
                    cnt++;
                    j=pi[j]; // 중첩 매핑 위해 pi로 이어나가기
                } else {
                    j++;
                }
            }
        }
        return cnt;
    }
}
