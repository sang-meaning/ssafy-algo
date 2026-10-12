import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_5648_유혜진 {
    static class Atom {
        int id, x, y, dir, energy;
        boolean alive;
        Atom(int id, int x, int y, int dir, int energy) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
            this.alive = true;
        }
    }

    static int[] dx = {0, 0, -1, 1}; // 0: 상, 1: 하, 2: 좌, 3: 우
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            List<Atom> atoms = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) * 2 + 2000;
                int y = Integer.parseInt(st.nextToken()) * 2 + 2000;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());
                atoms.add(new Atom(i, x, y, dir, energy));
            }

            int totalEnergy = 0;

            for (int step = 0; step <= 4000; step++) {
                Map<String, List<Atom>> map = new HashMap<>();

                for (Atom atom : atoms) {
                    if (!atom.alive) continue;
                    atom.x += dx[atom.dir];
                    atom.y += dy[atom.dir];

                    if (atom.x < 0 || atom.x > 4000 || atom.y < 0 || atom.y > 4000) {
                        atom.alive = false;
                        continue;
                    }

                    String posKey = atom.x + "," + atom.y;
                    map.computeIfAbsent(posKey, k -> new ArrayList<>()).add(atom);
                }

                for (String key : map.keySet()) {
                    List<Atom> list = map.get(key);
                    if (list.size() > 1) {
                        for (Atom atom : list) {
                            if (atom.alive) {
                                atom.alive = false;
                                totalEnergy += atom.energy;
                            }
                        }
                    }
                }
            }

            System.out.println("#" + t + " " + totalEnergy);
        }
    }
}