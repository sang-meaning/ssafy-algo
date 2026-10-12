public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {

            String A = sc.next();
            String B = sc.next();

            int a = A.length();
            int b = B.length();                      

            int c = (a >= b ? a : b) + 1;

            int [] Aarray = new int[c-1];
            for(int i=0; i<a; i++) {
                Aarray[(c-1-a)+i] = A.charAt(i) - '0';
            }

            int [] Barray = new int[c-1];
            for(int i=0; i<b; i++) {
                Barray[(c-1-b)+i] = B.charAt(i) - '0';
            }

            int [] Carryarray = new int[c];
            int [] Answerarray = new int[c];


            for(int i=c-2; i>=0; i--) {
                int temp = Aarray[i] + Barray[i] + Carryarray[i+1];
                if(temp >= 10) {
                    Carryarray[i] = 1;
                    Answerarray[i+1] = temp % 10;
                } else {
                    Answerarray[i+1] = temp;
                }
            }

            if(Carryarray[0] != 0) {
                Answerarray[0] = Carryarray[0];
            }

            StringBuilder sb = new StringBuilder();

            int start = 0;
            if(Answerarray[0] == 0) {
                start = 1;
            }

            for(int i=start; i<c; i++) {
                sb.append(Answerarray[i]);
            }

            System.out.println("#" + tc + " " + sb);
        }
    }
}