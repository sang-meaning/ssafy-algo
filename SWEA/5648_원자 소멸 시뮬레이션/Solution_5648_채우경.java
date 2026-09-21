import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    // 이동 방향: 상(0: y+1), 하(1: y-1), 좌(2: x-1), 우(3: x+1)
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    // 좌표계 크기를 2배 확장에 맞춰 설정 (-2000 ~ 2000 -> 0 ~ 4000 오프셋 적용)
    static final int OFFSET = 2000;
    static final int MAP_SIZE = 4001;

    // 각 위치에 위치한 원자의 수와 에너지를 기록
    static int[][] map = new int[MAP_SIZE][MAP_SIZE];

    static class Atom {
        int x, y, dir, energy;
        boolean isDead;

        Atom(int x, int y, int dir, int energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
            this.isDead = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            List<Atom> atoms = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                // 좌표를 2배로 확장하고 오프셋(2000)을 더해 양수 인덱스로 변환
                int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());

                atoms.add(new Atom(x, y, dir, energy));
            }

            int totalEnergy = 0;

            // 0.5초 단위로 4000번(최대 2000초 시뮬레이션) 이동
            for (int step = 0; step <= 4000; step++) {
                // 살아있는 원자 개수가 2개 미만이면 더 이상 충돌 불가능
                int aliveCount = 0;

                // 1. 모든 원자 이동 처리
                for (Atom atom : atoms) {
                    if (atom.isDead) continue;

                    atom.x += dx[atom.dir];
                    atom.y += dy[atom.dir];

                    // 맵 경계를 벗어난 원자는 소멸 처리
                    if (atom.x < 0 || atom.x >= MAP_SIZE || atom.y < 0 || atom.y >= MAP_SIZE) {
                        atom.isDead = true;
                        continue;
                    }

                    map[atom.x][atom.y] += 1;
                    aliveCount++;
                }

                if (aliveCount < 2) {
                    // 남은 맵 위치 초기화 후 종료
                    for (Atom atom : atoms) {
                        if (atom.x >= 0 && atom.x < MAP_SIZE && atom.y >= 0 && atom.y < MAP_SIZE) {
                            map[atom.x][atom.y] = 0;
                        }
                    }
                    break;
                }

                // 2. 충돌 체크 및 에너지 방출
                for (Atom atom : atoms) {
                    if (atom.isDead) continue;

                    // 이동 위치에 2개 이상의 원자가 모인 경우
                    if (map[atom.x][atom.y] > 1) {
                        totalEnergy += atom.energy;
                        atom.isDead = true;
                    }
                }

                // 3. 맵 방문 배열 원복 (다음 step 준비)
                for (Atom atom : atoms) {
                    if (atom.x >= 0 && atom.x < MAP_SIZE && atom.y >= 0 && atom.y < MAP_SIZE) {
                        map[atom.x][atom.y] = 0;
                    }
                }
            }

            System.out.println("#" + t + " " + totalEnergy);
        }
    }
}