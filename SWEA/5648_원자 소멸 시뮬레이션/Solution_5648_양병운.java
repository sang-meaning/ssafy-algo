import java.io.*;
import java.util.*;
 
class Solution {
 
    static class Atom {
        int x, y;
        int dir;
        int energy;
 
        Atom(int x, int y, int dir, int energy) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
    }
 
    // 0: 상, 1: 하, 2: 좌, 3: 우
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
 
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
 
            int totalEnergy = 0;
            while (!atoms.isEmpty()) {
                Map<Integer, List<Atom>> map = new HashMap<>();
                for (Atom atom : atoms) {
                    atom.x += dx[atom.dir];
                    atom.y += dy[atom.dir];
                    if (atom.x < -2000 || atom.x > 2000 ||
                        atom.y < -2000 || atom.y > 2000) {
                        continue;
                    }
                    int key = (atom.x + 2000) * 4001 + (atom.y + 2000);
                    map.computeIfAbsent(key, k -> new ArrayList<>()).add(atom);
                }
 
                List<Atom> next = new ArrayList<>();
                for (List<Atom> list : map.values()) {
                    if (list.size() >= 2) {
                        for (Atom atom : list) {
                            totalEnergy += atom.energy;
                        }
                    } else {
                        next.add(list.get(0));
                    }
                }
                atoms = next;
            }
            answer.append("#").append(tc).append(" ").append(totalEnergy).append("\n");
        }
        System.out.print(answer);
    }
}