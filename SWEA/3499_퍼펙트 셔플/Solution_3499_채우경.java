  public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int t=1;t<=T;t++){

            int N = Integer.parseInt(br.readLine());

            String[] arr = new String[N];

            st=new StringTokenizer(br.readLine());

            for (int i=0;i<N;i++){
                arr[i]=st.nextToken();
            }

            StringBuilder sb = new StringBuilder();
            String[] A = new String[(N+1)/2];
            String[] B = new String[(N+1)/2];
            for (int i=0;i<(N+1)/2;i++){
                A[i]=arr[i];
            }
            int a=0;
            for (int i=((N+1)/2);i<N;i++){

                B[a]=arr[i];
                a++;
            }

            for (int i=0;i<A.length;i++){
                sb.append(A[i]+" ");
                if (B[i]==null)
                    break;


                sb.append(B[i]+" ");

            }

            System.out.println("#"+t+" "+sb);


//            String[] arr = new String[N];
//            st = new StringTokenizer(br.readLine());
//
//            for (int i=0;i<N;i++){
//                arr[i]=st.nextToken();
//            }
//            int a = 0;
//            int b = (N+1)/2;
//
//            StringBuilder sb = new StringBuilder();
//            for (int i=0;i<N/2;i++){
//                sb.append(arr[a++]);
//                sb.append(" ").append(arr[b++]).append(" ");
//            }
//
//            if (N%2!=0)
//                sb.append(arr[a]);
//
//            System.out.println("#"+t+" "+sb);
        }
    }
}
