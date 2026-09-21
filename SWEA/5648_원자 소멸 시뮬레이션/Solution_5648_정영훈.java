import java.io.*;
import java.util.*;

class Solution {
    static class Atom {
        int x, y, dir, energy;
        boolean alive = true;

        Atom(int x, int y, int dir, int energy) {
            this.x = x * 2;
            this.y = y * 2;
            this.dir = dir;
            this.energy = energy;
        }
    }

    // 상, 하, 좌, 우
    static final int[] DX = {0, 0, -1, 1};
    static final int[] DY = {1, -1, 0, 0};

    static int key(int x, int y) {
        return (x + 2000) * 4001 + (y + 2000);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            List<Atom> atoms = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                atoms.add(new Atom(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
                ));
            }

            int total = 0;

            for (int time = 0; time <= 4000 && !atoms.isEmpty(); time++) {
                Map<Integer, Integer> count = new HashMap<>();

                for (Atom atom : atoms) {
                    atom.x += DX[atom.dir];
                    atom.y += DY[atom.dir];

                    if (Math.abs(atom.x) > 2000 || Math.abs(atom.y) > 2000) {
                        atom.alive = false;
                        continue;
                    }

                    int position = key(atom.x, atom.y);
                    count.put(position, count.getOrDefault(position, 0) + 1);
                }

                List<Atom> survivors = new ArrayList<>();

                for (Atom atom : atoms) {
                    if (!atom.alive) continue;

                    if (count.get(key(atom.x, atom.y)) >= 2) {
                        total += atom.energy;
                    } else {
                        survivors.add(atom);
                    }
                }

                atoms = survivors;
            }

            answer.append('#').append(tc).append(' ').append(total).append('\n');
        }

        System.out.print(answer);
    }
}