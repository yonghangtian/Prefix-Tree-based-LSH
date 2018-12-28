import java.io.IOException;

public class TestWriteTries {
    public static void main(String[] args) {
        //create trie array for different permuted signatures
        JavaTrie[] tries = new JavaTrie[JavaTrie.PERMUTE_TIMES];
        //initial tries array
        initTries(tries);

        // permutation functions parameters a,b of ax+b%p
        int[] a, b;

        // tweetID and Signature pairs
        String inputFilePath = "io/Target_ang_no.txt";
        IdAndSignature tweet = new IdAndSignature();
        ReadTxtFile readFirstFile = new ReadTxtFile();
        tries = readFirstFile.readInput(inputFilePath, tries);
        tweet.setIdAndSignature(readFirstFile.getIdAndSignature());
        a = readFirstFile.getA();
        b = readFirstFile.getB();

        System.out.println(tweet.getIdAndSignature().size());
        //System.out.println("\n\n tries are written successfully! \n\n");
        WriteTxtFile write = new WriteTxtFile();
        for (int i = 0; i <JavaTrie.PERMUTE_TIMES ; i++) {
            String filename = "trie"+i;
            try {
                System.out.println(i+" hash function parameters "+a[i]+" "+b[i]);
                write.writeTries(tries[i],filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void initTries(JavaTrie[] tries){
        for (int i = 0; i < JavaTrie.PERMUTE_TIMES ; i++) {
            tries[i] = new JavaTrie();
        }
    }
}


