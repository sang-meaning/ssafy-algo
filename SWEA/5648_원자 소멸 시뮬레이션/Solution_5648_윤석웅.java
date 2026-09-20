import java.io.*;
import java.util.*;

public class Solution {

    static class Atom {
        int x, y, dir, e;

        Atom(int x, int y, int dir, int e) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.e = e;
        }
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static ArrayList<Atom> atoms;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {

            int N = Integer.parseInt(br.readLine());

            atoms = new ArrayList<>();

            for(int i=0; i<N; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken()) * 2;
                int y = Integer.parseInt(st.nextToken()) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());

                atoms.add(new Atom(x, y, dir, e));
            }

            int ans = simulate();

            sb.append("#").append(tc).append(" ")
              .append(ans).append("\n");
        }

        System.out.print(sb);
    }

    static int simulate() {

        int ans = 0;

        while(!atoms.isEmpty()) {

            HashMap<Integer, Long> pos = new HashMap<>();
            ArrayList<Atom> moved = new ArrayList<>();

            // 이동
            for(Atom a : atoms) {

                a.x += dx[a.dir];
                a.y += dy[a.dir];

                if(a.x < -2000 || a.x > 2000 ||
                   a.y < -2000 || a.y > 2000)
                    continue;

                moved.add(a);

                int key = makeKey(a.x, a.y);

                long val = pos.getOrDefault(key, 0L);

                int cnt = (int)(val >> 32);
                int sum = (int)val;

                cnt++;
                sum += a.e;

                pos.put(
                    key,
                    ((long)cnt << 32) | (sum & 0xffffffffL)
                );
            }

            // 충돌 에너지 계산
            for(long val : pos.values()) {

                int cnt = (int)(val >> 32);
                int sum = (int)val;

                if(cnt >= 2)
                    ans += sum;
            }

            // 충돌하지 않은 원자만 유지
            ArrayList<Atom> next = new ArrayList<>();

            for(Atom a : moved) {

                int key = makeKey(a.x, a.y);
                long val = pos.get(key);

                int cnt = (int)(val >> 32);

                if(cnt == 1)
                    next.add(a);
            }

            atoms = next;
        }

        return ans;
    }

    static int makeKey(int x, int y) {

        x += 2000;
        y += 2000;

        return x * 4001 + y;
    }
}