import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {

    static int winCount;
    static int loseCount;
    static int[] kyuyoungCardArr;
    static int[] inyoungCardArr;
    static boolean[] isSelected;

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(br.readLine());

            kyuyoungCardArr = new int[9];
            inyoungCardArr = new int[9];
            isSelected = new boolean[9];

            boolean[] isKyuyoungCard = new boolean[19];

            winCount = 0;
            loseCount = 0;

            for (int i = 0; i < 9; i++) {
                kyuyoungCardArr[i] = Integer.parseInt(st.nextToken());
                isKyuyoungCard[kyuyoungCardArr[i]] = true;
            }

            int index = 0;

            for (int card = 1; card <= 18; card++) {
                if (!isKyuyoungCard[card]) {
                    inyoungCardArr[index++] = card;
                }
            }

            playGame(0, 0, 0);

            System.out.println("#" + test_case + " " + winCount + " " + loseCount);
        }
    }

    static void playGame(int cnt, int kyuyoungScore, int inyoungScore) {

        if (cnt == 9) {

            if (kyuyoungScore > inyoungScore) {
                winCount++;
            } else {
                loseCount++;
            }

            return;
        }

        for (int i = 0; i < 9; i++) {

            if (isSelected[i]) {
                continue;
            }

            isSelected[i] = true;

            int score = kyuyoungCardArr[cnt] + inyoungCardArr[i];

            if (kyuyoungCardArr[cnt] > inyoungCardArr[i]) {
                playGame(cnt + 1, kyuyoungScore + score, inyoungScore);
            } else {
                playGame(cnt + 1, kyuyoungScore, inyoungScore + score);
            }

            isSelected[i] = false;
        }
    }
}