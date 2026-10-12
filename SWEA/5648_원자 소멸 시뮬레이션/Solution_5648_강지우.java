import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    // 좌표를 2배로 늘렸으므로, -1000~1000의 범위는 0~4000이 됩니다.
    static int[][] map = new int[4001][4001];
    
    // 문제 방향: 0(상), 1(하), 2(좌), 3(우)
    // 주어진 좌표계는 일반적인 수학 좌표계(상 방향이 y 증가)
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static class Atom {
        int x, y, dir, k;

        public Atom(int x, int y, int dir, int k) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.k = k;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine().trim());
            Queue<Atom> queue = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                // 음수 좌표를 양수로 변환 (최소값이 0이 되도록 +1000)
                // 0.5초에서의 충돌을 정수 좌표계에서 계산하기 위해 모든 좌표를 2배로 확장
                x = (x + 1000) * 2;
                y = (y + 1000) * 2;
                queue.offer(new Atom(x, y, dir, k));
            }

            int totalEnergy = 0;

            // 큐가 빌 때까지(모든 원소가 소멸하거나 맵 밖으로 나갈 때까지) 시뮬레이션
            while (!queue.isEmpty()) {
                int size = queue.size();

                // 1. 모든 원자를 1초(여기서는 확장된 기준 1칸)씩 이동시킴
                for (int i = 0; i < size; i++) {
                    Atom atom = queue.poll();
                    atom.x += dx[atom.dir];
                    atom.y += dy[atom.dir];

                    // 범위를 벗어난 원자는 소멸하므로 버림 (4000을 넘어가면 다시 돌아오지 않음)
                    if (atom.x < 0 || atom.x > 4000 || atom.y < 0 || atom.y > 4000) {
                        continue;
                    }

                    // 이동한 위치의 map에 에너지를 누적시킴
                    map[atom.x][atom.y] += atom.k;
                    queue.offer(atom); // 생존한 원자 다시 큐에 삽입
                }

                // 2. 충돌 여부 확인 및 에너지 정산
                size = queue.size();
                for (int i = 0; i < size; i++) {
                    Atom atom = queue.poll();

                    // map 값이 0이라면 이미 다른 원자와 충돌 정산이 완료되어 0으로 초기화된 상태
                    if (map[atom.x][atom.y] == 0) {
                        continue; 
                    } 
                    // 누적 에너지가 현재 원자의 에너지보다 크면 충돌이 발생한 것
                    else if (map[atom.x][atom.y] > atom.k) {
                        totalEnergy += map[atom.x][atom.y];
                        map[atom.x][atom.y] = 0; // 해당 위치 정산 완료 처리(0으로 초기화)
                    } 
                    // 누적 에너지가 현재 원자의 에너지와 같다면 충돌하지 않은 것
                    else if (map[atom.x][atom.y] == atom.k) {
                        map[atom.x][atom.y] = 0; // 다음 턴을 위해 map은 0으로 비워줌
                        queue.offer(atom); // 살아남은 원자는 다음 턴 진행을 위해 다시 큐로 삽입
                    }
                }
            }

            System.out.println("#" + t + " " + totalEnergy);
        }
    }
}