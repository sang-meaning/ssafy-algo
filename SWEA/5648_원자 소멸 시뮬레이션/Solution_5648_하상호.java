package swea;

import java.io.*;
import java.util.*;

public class Solution_5648_하상호 {

    static class Atom {
        int x;
        int y;
        int dir;
        int energy;

        Atom(int x, int y, int dir, int energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
    }

    // 문제 기준
    // 0 : 상
    // 1 : 하
    // 2 : 좌
    // 3 : 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            int N = Integer.parseInt(br.readLine());

            List<Atom> atoms = new ArrayList<>();

            for (int i = 0; i < N; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken()) * 2;
                int y = Integer.parseInt(st.nextToken()) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());

                atoms.add(new Atom(x, y, dir, energy));
            }

            int answer = 0;

            // 원래 좌표가 -1000 ~ 1000
            // 2배 했으므로 충돌 가능한 범위는 대략 -2000 ~ 2000
            while (!atoms.isEmpty()) {

                Map<String, List<Atom>> map = new HashMap<>();

                // -------------------------
                // 모든 원자 이동
                // -------------------------
                for (Atom atom : atoms) {

                    atom.x += dx[atom.dir];
                    atom.y += dy[atom.dir];

                    // 범위를 벗어나면 다시 만날 수 없음
                    if (atom.x < -2000 || atom.x > 2000
                            || atom.y < -2000 || atom.y > 2000) {
                        continue;
                    }

                    String key = atom.x + "," + atom.y;

                    map.computeIfAbsent(
                            key,
                            k -> new ArrayList<>()
                    ).add(atom);
                }

                // 다음 시간에 살아남을 원자
                List<Atom> next = new ArrayList<>();

                for (List<Atom> list : map.values()) {

                    // 같은 위치에 2개 이상 존재
                    // → 충돌
                    if (list.size() >= 2) {

                        for (Atom atom : list) {
                            answer += atom.energy;
                        }

                    } else {

                        next.add(list.get(0));
                    }
                }

                atoms = next;
            }

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }
}