package swea;

import java.util.*;
import java.io.*;

public class SWEA5648 {

    static int N;

    // 상, 하, 좌, 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

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

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            ArrayList<Atom> atoms = new ArrayList<>();

            for (int i = 0; i < N; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken()) * 2;
                int y = Integer.parseInt(st.nextToken()) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());

                atoms.add(new Atom(x, y, dir, energy));
            }

            int ans = 0;

            while (!atoms.isEmpty()) {

                // 1. 모든 원자 이동
                ArrayList<Atom> moved = new ArrayList<>();

                for (Atom atom : atoms) {

                    atom.x += dx[atom.dir];
                    atom.y += dy[atom.dir];

                    // 이 범위를 벗어나면 더 이상 충돌할 수 없음
                    if (atom.x < -2000 || atom.x > 2000
                            || atom.y < -2000 || atom.y > 2000) {
                        continue;
                    }

                    moved.add(atom);
                }

                // 2. 같은 위치에 있는지 확인
                HashMap<Integer, Integer> map = new HashMap<>();

                boolean[] dead = new boolean[moved.size()];

                for (int i = 0; i < moved.size(); i++) {

                    Atom atom = moved.get(i);

                    int key = (atom.x + 2000) * 4001
                            + (atom.y + 2000);

                    // 이 위치에 처음 온 원자
                    if (!map.containsKey(key)) {

                        map.put(key, i);

                    } else {

                        int prev = map.get(key);

                        // 이미 여기서 충돌이 발생했던 경우
                        if (prev == -1) {

                            dead[i] = true;
                            ans += atom.energy;

                        } else {

                            // 이전 원자 + 현재 원자 충돌
                            dead[prev] = true;
                            dead[i] = true;

                            ans += moved.get(prev).energy;
                            ans += atom.energy;

                            // 이 좌표는 이미 충돌이 발생한 좌표
                            map.put(key, -1);
                        }
                    }
                }

                // 3. 살아남은 원자만 다시 저장
                ArrayList<Atom> next = new ArrayList<>();

                for (int i = 0; i < moved.size(); i++) {

                    if (!dead[i]) {
                        next.add(moved.get(i));
                    }
                }

                atoms = next;
            }

            sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(ans)
                    .append("\n");
        }

        System.out.print(sb);
    }
}