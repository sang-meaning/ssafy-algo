class Solution {
    public static int answer;
    public static int[] idx;
    public static char[] chars = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

    public static int solution(int n, String[] data) {
        idx = new int[8];
        for (int i=0; i<8; i++) idx[i] = i;
        
        answer = 0;
        perm(0, data);
        return answer;
    }

    public static void perm(int d, String[] data) {
        if (d == 8) {
            int[] real = new int[26];
            for (int i=0; i<8; i++) {
                real[chars[i] -'A'] = idx[i];
            }

            for (String s : data) {
                int n1 = real[s.charAt(0)-'A'];
                int n2 = real[s.charAt(2)-'A'];
                char c3 = s.charAt(3);
                int n3 = s.charAt(4) - '0';

                if (c3 == '=' && Math.abs(n1 - n2) - 1 != n3) return;
                if (c3 == '<' && Math.abs(n1 - n2) - 1 >= n3) return;
                if (c3 == '>' && Math.abs(n1 - n2) - 1 <= n3) return;
            }

            answer++;
            return;
        }

        for (int i=d; i<8; i++) {
            int temp = idx[d];
            idx[d] = idx[i];
            idx[i] = temp;

            perm(d+1, data);

            temp = idx[d];
            idx[d] = idx[i];
            idx[i] = temp;
        }
    }
}