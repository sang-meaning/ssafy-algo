class Solution {
    public int answer;
    public int[] gun;
    public int solution(int[] number) {
        answer = 0;
        gun = new int[3];

        comb(0, 0, number);
        return answer;
    }

    public void comb(int last, int size, int[] number) {
        if (size == 3) {
            if (gun[0] + gun[1] + gun[2] == 0)
                answer++;
            return;
        }
        if (last == number.length) return;

        gun[size] = number[last];
        comb(last+1, size+1, number);
        comb(last+1, size, number);
    }
}