
import java.util.Random;

public class GenerateRandom {

    public int[] generateRandoma() {
        /**
         * @param PERMUTE_TIMES: the num of vector we want to map
         * @param PERMUTE_TIMES: the num of signatures we want to get
         *
         * @return a[]
         */
        int a[] = new int[JavaTrie.PERMUTE_TIMES];
        for(int i = 0; i < JavaTrie.PERMUTE_TIMES;) {
            Random rand = new Random();
            int temp = rand.nextInt(JavaTrie.SIGNATURE_LENGTH);
            // System.out.println(temp);
            if ((temp != 0) && relativePrime(JavaTrie.SIGNATURE_LENGTH,temp)) {
                a[i] = temp;
                i++;
            }
        }
        return a;
    }


    public int[] generateRandomb() {
        /**
         * @param PERMUTE_TIMES: the num of permutation functions we want(P)
         * @param SIGNATURE_LENGTH: the p in permutation (ax+b)%p
         *
         * @return b[]
         */
        int b[] = new int[JavaTrie.PERMUTE_TIMES];
        boolean repeat = false;
        for(int i = 0; i < JavaTrie.PERMUTE_TIMES; ) {
            Random rand = new Random();
             int temp = rand.nextInt(JavaTrie.SIGNATURE_LENGTH);
             for (int j = 0; j < i; j++) {
                 if (temp == b[j]) {
                     repeat = true;
                 }
             }
             if (!repeat) {
                 b[i] = temp;
                 i++;
             }
             repeat = false;
        }
        return b;
    }


    public boolean relativePrime (int m, int n) {
        /**
         * @param m,n: 2 nums
         *
         * @return if m and n are relative-prime
         */
        int k=0;
        for(k = m;0 != k%n;k+=m);
        if(k==m*n){
            return true;
        }else{
            return false;
        }
    }
}
