import java.io.*;
import java.util.*;

public class Solution {

    static class Atom {
        int x, y;
        int d;
        int energy;

        Atom(int x, int y, int d, int energy) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.energy = energy;
        }
    }

    // 상 하 좌 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine());

            List<Atom> atoms = new ArrayList<>();

            for (int i = 0; i < N; i++) {

                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());

                // 0.5초 충돌을 처리하기 위해 좌표 2배
                atoms.add(new Atom(x * 2, y * 2, d, energy));
            }

            int answer = 0;

            while (!atoms.isEmpty()) {

                Map<String, Integer> map = new HashMap<>();

                // 1. 모든 원자 이동
                for (Atom atom : atoms) {

                    atom.x += dx[atom.d];
                    atom.y += dy[atom.d];

                    String key = atom.x + "," + atom.y;

                    map.put(key, map.getOrDefault(key, 0) + 1);
                }

                // 2. 충돌하지 않은 원자만 저장
                List<Atom> next = new ArrayList<>();

                for (Atom atom : atoms) {

                    String key = atom.x + "," + atom.y;

                    // 충돌
                    if (map.get(key) >= 2) {
                        answer += atom.energy;
                    }

                    // 충돌 X
                    else {

                        // 충돌 가능 영역에 있는 원자만 유지
                        if (atom.x >= -2000 && atom.x <= 2000
                                && atom.y >= -2000 && atom.y <= 2000) {

                            next.add(atom);
                        }
                    }
                }

                atoms = next;
            }

            System.out.println("#" + tc + " " + answer);
        }
    }
}