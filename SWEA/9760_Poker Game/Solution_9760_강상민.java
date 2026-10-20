import java.util.*;
import java.io.*;

public class Solution_9760_강상민 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T;
    static int[][] board;
    static int[][] card;


    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; t++) {
            board = new int[4][13];
            card = new int[5][2]; // board 의 r,c index 저장

            st = new StringTokenizer(br.readLine());
            for (int i=0; i<5; i++) {
                String temp = st.nextToken();

                char a = temp.charAt(0);
                char b = temp.charAt(1);

                int r,c;

                if (a == 'S') r = 0;
                else if (a == 'D') r = 1;
                else if (a == 'H') r = 2;
                else r = 3;

                if (b == 'A') c = 0;
                else if (b == 'T') c = 9;
                else if (b == 'J') c = 10;
                else if (b == 'Q') c = 11;
                else if (b == 'K') c = 12;
                else {
                    c = b - '0'-1;
                }

                card[i][0] = r;
                card[i][1] = c;
            }

            // 카드를 4*12 행렬의 index로 표현 완료

            // 슈트 = 행, 랭크 = 열
            boolean isStraight = checkS(); // card의 열만 보기
            boolean isFlush = checkF(); // card의 행만 보기

            sb.append("#"+t+" ");

            // 1. Straight Flush
            // 같은 행에서 열이 연속, 9 10 11 12 0 도 허용
            if (isStraight && isFlush) {
                sb.append("Straight Flush").append("\n");
                continue;
            }

            // 2. Four of a Kind
            // 오직 4장의 열이 같다
            if (second()) {
                sb.append("Four of a Kind").append("\n");
                continue;
            }
            

            // 3. Full House
            // 같은 열이 3개, 2개 로 존재한다
            if (third()) {
                sb.append("Full House").append("\n");
                continue;
            }

            // 4. Flush
            // 5장 모두 동일 행
            if (isFlush) {
                sb.append("Flush").append("\n");
                continue;
            }

            // 5. Straight
            // 열 연속
            if (isStraight) {
                sb.append("Straight").append("\n");
                continue;
            }

            // 6. Three of a kind
            // 동일한 열이 3장이다
            if (sixth()) {
                sb.append("Three of a kind").append("\n");
                continue;
            }

            // 7. Two Pair
            // 동일 열이 2개씩 두개의 행으로 이루어졌다 (2 6 6 3 3) 
            if (seventh()) {
                sb.append("Two pair").append("\n");
                continue;
            }

            // 8. One Pair
            // 동일 열이 2장이다
            if (eighth()) {
                sb.append("One pair").append("\n");
                continue;
            }

            sb.append("High card").append("\n");
            

        }

        System.out.println(sb);
    }

    static boolean eighth() {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i=0; i<5; i++) {
            int temp = card[i][1];
            map.put(temp, map.getOrDefault(temp, 0)+1 );
        }

        int cnt = 0;

        for (int i=0; i<5; i++) {
            int temp = card[i][1];
            if (map.get(temp) == 2) cnt++;
        
        }

        if (cnt == 2) return true;
        else return false;
    }

    static boolean seventh() {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i=0; i<5; i++) {
            int temp = card[i][1];
            map.put(temp, map.getOrDefault(temp, 0)+1 );
        }

        int cnt = 0;

        if (map.size() != 3) return false;

        for (int i=0; i<5; i++) {
            int temp = card[i][1];
            if (map.get(temp) == 2) cnt++;
        
        }

        if (cnt == 4) return true;
        else return false;
        
    }

    static boolean sixth() {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i=0; i<5; i++) {
            int temp = card[i][1];
            map.put(temp, map.getOrDefault(temp, 0)+1 );
        }

        for (int i=0; i<5; i++) {
            int temp = card[i][1];
            if (map.get(temp) == 3) return true;
        
        }

        return false;
    }

    static boolean third() {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i=0; i<5; i++) {
            int temp = card[i][1];
            map.put(temp, map.getOrDefault(temp, 0)+1 );
        }

        for (int i=0; i<5; i++) {
            int temp = card[i][1];
            if (map.get(temp) != 2 && map.get(temp) != 3) return false;
        
        }
        

        return true;
    }

    static boolean second() {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i=0; i<5; i++) {
            int temp = card[i][1];
            map.put(temp, map.getOrDefault(temp, 0)+1 );
        }

        for (int i=0; i<5; i++) {
            int temp = card[i][1];
            if (map.get(temp) == 4) return true;
        
        }

        return false;

        
    }
    
    static boolean checkS() {
        int[] temp = new int[5];

        for (int i=0; i<5; i++) {
            temp[i] = card[i][1];
        }

        Arrays.sort(temp);

        if (temp[0] == 0 && temp[1]==9 && temp[2]==10 && temp[3]==11 && temp[4] == 12) return true;
        
        for (int j=0; j<4; j++) {
            if (temp[j+1] - temp[j] != 1) return false;
        }
        return true;
    }

    static boolean checkF() {
        int[] temp = new int[5];

        for (int i=0; i<5; i++) {
            temp[i] = card[i][0];
        }

        for (int i=0; i<4; i++) {
            if (temp[i] != temp[i+1]) return false;
        }

        return true;


    }
}
