import java.io.*;
import java.util.*;

public class Solution {

    static int[] dealerCards;
    static int[] playerCards;
    static boolean[] isSelected;

    static int dealerWin;
    static int playerWin;

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            dealerCards = new int[9];
            playerCards = new int[9];
            isSelected = new boolean[9];

            boolean[] hasCard = new boolean[19];

            dealerWin = 0;
            playerWin = 0;

            // 규영이가 가진 카드 입력 받기
            for (int i = 0; i < 9; i++) {
                dealerCards[i] = Integer.parseInt(st.nextToken());
                hasCard[dealerCards[i]] = true;
            }

            // 규영이가 가지고 있지 않은 카드는 인영이 카드
            int index = 0;

            for (int card = 1; card <= 18; card++) {

                if (!hasCard[card]) {
                    playerCards[index++] = card;
                }
            }

            // 카드 게임 시작!!!!!!!!!!!!
            playGame(0, 0, 0);

            System.out.println(
                    "#" + test_case + " " + dealerWin + " " + playerWin
            );
        }
    }

    static void playGame(
            int count,
            int dealerPoint,
            int playerPoint
    ) {

        // 카드 9장을 전부 사용했다면 승패 확인
        if (count == 9) {

            if (dealerPoint > playerPoint) {
                dealerWin++;
            } else if (dealerPoint < playerPoint) {
                playerWin++;
            }

            return;
        }

        // 인영이가 낼 카드 하나씩 선택
        for (int i = 0; i < 9; i++) {

            // 이미 사용한 카드라면 넘어가기
            if (isSelected[i]) {
                continue;
            }

            int dealerCard = dealerCards[count];
            int playerCard = playerCards[i];
            int point = dealerCard + playerCard;

            isSelected[i] = true;

            // 규영이 카드가 더 크다면 규영이가 점수 획득
            if (dealerCard > playerCard) {

                playGame(
                        count + 1,
                        dealerPoint + point,
                        playerPoint
                );

            // 인영이 카드가 더 크다면 인영이가 점수 획득
            } else {

                playGame(
                        count + 1,
                        dealerPoint,
                        playerPoint + point
                );
            }

            // 사용했던 카드 다시 돌려놓기
            isSelected[i] = false;
        }
    }
}