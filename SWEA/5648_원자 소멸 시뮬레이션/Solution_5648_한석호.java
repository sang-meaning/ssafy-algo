import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Solution_5648_한석호 {
    // 0: 상(y+), 1: 하(y-), 2: 좌(x-), 3: 우(x+)
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    // 해당 위치에 몇 개의 원자가 도착했는지 카운트하는 배열
    static int[][] map = new int[4001][4001];

    static class Atom {
        int x, y, dir, energy;

        Atom(int x, int y, int dir, int energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            ArrayList<Atom> atoms = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                // 좌표를 2배 불리고 [0, 4000] 범위로 이동
                int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());

                atoms.add(new Atom(x, y, dir, energy));
            }

            int totalEnergy = 0;

            // 좌표 범위를 가로지르는 최대 시간인 4000번 반복
            for (int time = 0; time <= 4000; time++) {
                if (atoms.size() <= 1) break; // 원자가 1개 이하로 남으면 종료

                // 1단계: 모든 원자를 한 칸씩 이동
                for (Atom atom : atoms) {
                    atom.x += dx[atom.dir];
                    atom.y += dy[atom.dir];

                    // 경계 내에 있을 때만 map에 개수 증가
                    if (atom.x >= 0 && atom.x <= 4000 && atom.y >= 0 && atom.y <= 4000) {
                        map[atom.x][atom.y]++;
                    }
                }

                // 2단계: 충돌 확인 및 방출 에너지 계산, 살아남은 원자 선별
                ArrayList<Atom> nextAtoms = new ArrayList<>();

                for (Atom atom : atoms) {
                    // 경계를 벗어난 경우
                    if (atom.x < 0 || atom.x > 4000 || atom.y < 0 || atom.y > 4000) {
                        continue;
                    }

                    // 해당 위치에 2개 이상의 원자가 모인 경우 -> 충돌 소멸
                    if (map[atom.x][atom.y] >= 2) {
                        totalEnergy += atom.energy;
                    } else {
                        // 혼자 위치한 경우 -> 다음 턴에도 살아남음
                        nextAtoms.add(atom);
                    }
                }

                // 3단계: map 초기화 (원자가 방문한 칸만 0으로 원복)
                for (Atom atom : atoms) {
                    if (atom.x >= 0 && atom.x <= 4000 && atom.y >= 0 && atom.y <= 4000) {
                        map[atom.x][atom.y] = 0;
                    }
                }

                // 원자 리스트 갱신
                atoms = nextAtoms;
            }

            sb.append("#").append(tc).append(" ").append(totalEnergy).append("\n");
        }

        System.out.print(sb.toString());
    }
}