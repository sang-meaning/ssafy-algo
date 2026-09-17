import java.io.*;
import java.util.*;

public class Solution {

    static class Atom {
        int x, y, dir, energy;

        Atom(int x, int y, int dir, int energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
    }

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

            while (!atoms.isEmpty()) {
                Map<Integer, Integer> count = new HashMap<>();

                for (Atom atom : atoms) {
                    atom.x += dx[atom.dir];
                    atom.y += dy[atom.dir];

                    if (atom.x < -2000 || atom.x > 2000 || atom.y < -2000 || atom.y > 2000) {
                        continue;
                    }

                    int key = (atom.x + 2000) * 4001 + (atom.y + 2000);
                    count.put(key, count.getOrDefault(key, 0) + 1);
                }

                List<Atom> next = new ArrayList<>();

                for (Atom atom : atoms) {
                    if (atom.x < -2000 || atom.x > 2000 || atom.y < -2000 || atom.y > 2000) {
                        continue;
                    }

                    int key = (atom.x + 2000) * 4001 + (atom.y + 2000);

                    if (count.get(key) >= 2) {
                        answer += atom.energy;
                    } else {
                        next.add(atom);
                    }
                }

                atoms = next;
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }
}