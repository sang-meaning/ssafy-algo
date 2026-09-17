import java.io.*;
import java.util.*;

public class Solution_5648_김민우 {

    static class Atom {
        int x;
        int y;
        int dir;
        int power;

        public Atom(int x, int y, int dir, int power) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.power = power;
        }
    }

    static int T, N;
    static int total;

    /*
     * 원래 좌표에서는 한 번에 0.5씩 이동하지만
     * 좌표를 2배 했으므로 한 번에 1씩 이동한다.
     *
     * 0: 상
     * 1: 하
     * 2: 좌
     * 3: 우
     */
    static int[][] delta = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= T; testCase++) {

            N = Integer.parseInt(br.readLine());

            List<Atom> atoms = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                // 0.5 단위 충돌을 정수로 처리하기 위해 좌표를 2배
                int x = Integer.parseInt(st.nextToken()) * 2;
                int y = Integer.parseInt(st.nextToken()) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());

                atoms.add(new Atom(x, y, dir, power));
            }

            total = 0;

            while (!atoms.isEmpty()) {
                atoms = moveAndCrash(atoms);
            }

            sb.append("#").append(testCase).append(" ").append(total).append("\n");
        }

        System.out.print(sb);
    }

    static List<Atom> moveAndCrash(List<Atom> atoms) {

        /*
         * 해당 좌표에 가장 먼저 도착한 원자를 저장한다.
         *
         * key   : 좌표를 하나의 정수로 변환한 값
         * value : 해당 좌표에 먼저 도착한 원자
         */
        Map<Integer, Atom> positionMap = new HashMap<>();

        /*
         * 충돌이 발생한 좌표를 저장한다.
         */
        Set<Integer> crashedPositions = new HashSet<>();

        for (Atom atom : atoms) {

            // 1. 원자 이동
            atom.x += delta[atom.dir][0];
            atom.y += delta[atom.dir][1];

            // 2. 범위를 벗어나면 제거
            if (!isIn(atom.x, atom.y)) {
                continue;
            }

            // 3. 현재 좌표를 하나의 정수로 변환
            int position = encode(atom.x, atom.y);

            // 이 좌표에 처음 도착한 원자인 경우
            if (!positionMap.containsKey(position)) {
                positionMap.put(position, atom);
            }

            // 이미 다른 원자가 존재하는 경우
            else {
                /*
                 * 이 좌표에서 처음 충돌이 발생한 경우
                 * 기존 원자의 에너지도 더한다.
                 */
                if (!crashedPositions.contains(position)) {
                    Atom firstAtom = positionMap.get(position);

                    total += firstAtom.power;
                    crashedPositions.add(position);
                }

                // 현재 원자의 에너지를 더한다.
                total += atom.power;
            }
        }

        // 충돌하지 않은 원자만 다음 시간으로 넘긴다.
        List<Atom> nextAtoms = new ArrayList<>();

        for (Map.Entry<Integer, Atom> entry : positionMap.entrySet()) {
            int position = entry.getKey();

            if (!crashedPositions.contains(position)) {
                nextAtoms.add(entry.getValue());
            }
        }

        return nextAtoms;
    }

    /*
     * 좌표를 벗어났는지 확인한다.
     *
     * 원래 범위: -1000 ~ 1000
     * 좌표를 2배 했으므로: -2000 ~ 2000
     */
    static boolean isIn(int x, int y) {
        return x >= -2000 && x <= 2000
                && y >= -2000 && y <= 2000;
    }

    /*
     * (x, y) 좌표를 하나의 정수로 변환한다.
     *
     * x, y의 범위가 -2000 ~ 2000이므로
     * 각각 2000을 더하면 0 ~ 4000이 된다.
     */
    static int encode(int x, int y) {
        return (x + 2000) * 4001 + (y + 2000);
    }
}