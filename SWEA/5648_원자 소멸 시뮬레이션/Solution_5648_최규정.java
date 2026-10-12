import java.util.*;
 
public class Solution {
 
    // atom[0] = x
    // atom[1] = y
    // atom[2] = 방향
    // atom[3] = 에너지
    static ArrayList<int[]> atoms;
 
    static int[][] map = new int[4001][4001];
 
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
 
    public static void main(String[] args) {
 
        Scanner sc = new Scanner(System.in);
 
        int T = sc.nextInt();
 
        for (int tc = 1; tc <= T; tc++) {
 
            int N = sc.nextInt();
 
            atoms = new ArrayList<>();
 
            for (int i = 0; i < N; i++) {
 
                int x = sc.nextInt();
                int y = sc.nextInt();
                int dir = sc.nextInt();
                int energy = sc.nextInt();
 
                atoms.add(new int[]{ x * 2 + 2000, y * 2 + 2000, dir, energy });
            }
 
            int answer = 0;
 
            while (!atoms.isEmpty()) {
 
                for (int i = 0; i < atoms.size(); i++) {
 
                    int[] atom = atoms.get(i);
 
                    int dir = atom[2];
 
                    atom[0] += dx[dir];
                    atom[1] += dy[dir];
 
                    int x = atom[0];
                    int y = atom[1];
 
 
                    if (x < 0 || x > 4000 ||  y < 0 || y > 4000) {
 
                        atom[3] = -1;
                        continue;
                    }
 
                    map[x][y]++;
                }
 
                for (int i = 0; i < atoms.size(); i++) {
 
                    int[] atom = atoms.get(i);
 
                    if (atom[3] == -1) {
                        continue;
                    }
 
                    int x = atom[0];
                    int y = atom[1];
 
                    if (map[x][y] >= 2) {
 
                        answer += atom[3];
 
                        atom[3] = -1;
                    }
                }
 
                for (int i = 0; i < atoms.size(); i++) {
 
                    int[] atom = atoms.get(i);
 
                    int x = atom[0];
                    int y = atom[1];
 
                    if (x >= 0 && x <= 4000 && y >= 0 && y <= 4000) {
 
                        map[x][y] = 0;
                    }
                }
                 
                for (int i = atoms.size() - 1; i >= 0; i--) {
 
                    if (atoms.get(i)[3] == -1) {
                        atoms.remove(i);
                    }
                }
            }
 
            System.out.println("#" + tc + " " + answer);
        }
 
        sc.close();
    }
}