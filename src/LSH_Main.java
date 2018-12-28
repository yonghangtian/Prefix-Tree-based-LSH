import java.io.IOException;
import java.util.*;

public class LSH_Main {
    public static void main(String[] args) {
        //create trie array for different permuted signatures
        JavaTrie[] tries = new JavaTrie[JavaTrie.PERMUTE_TIMES];
        //initial tries array
        initTries(tries);
        // permutation functions parameters a,b of ax+b%p
        int[] a,b;

        // tweetID and Signature pairs
        String inputFilePath = "io/Target_tg_no.txt";
        IdAndSignature tweet = new IdAndSignature();
        ReadTxtFile readFirstFile = new ReadTxtFile();
        tries = readFirstFile.readInput(inputFilePath,tries);
        tweet.setIdAndSignature(readFirstFile.getIdAndSignature());        
        a = readFirstFile.getA();
        b = readFirstFile.getB();

        System.out.println(tweet.getIdAndSignature().size());

        //System.out.println("\n\n tries are written successfully! \n\n");

        Hashtable<String,String> twoNeighbors = new Hashtable<>();
        for (Map.Entry<String, String> idAndSignature : tweet.getIdAndSignature().entrySet()) {
            String signature = idAndSignature.getValue();
            //System.out.println(signature);
            int[][] permutedSign = HashFunc.hashFunc(a, b, ReadTxtFile.stringToInt(signature));
            ArrayList<HashMap<String, Integer>> neighbors = new ArrayList<>(JavaTrie.PERMUTE_TIMES);

            //System.out.println("Start to find neighbors!");
            FindNearestNeighbor find = new FindNearestNeighbor();
            for (int i = 0; i < JavaTrie.PERMUTE_TIMES; i++) {
                if (find.findNearestNeighborFromOneTrie(tries[i], permutedSign[i]) != null) {
                    neighbors.add(i, find.findNearestNeighborFromOneTrie(tries[i], permutedSign[i]));
                    //tries[i].delete(JavaTrie.toString(permutedSign[i]));
                }
            }
//            if (!neighbors.isEmpty()) {
//                System.out.println("Neighbors are found successfully! \n");
//            } else {
//                System.out.println("neighbors not found! \n");
//            }


            //System.out.println("Start to compare these neighbors");
            HashMap<Integer, String> nearestNeighborAndHashFuncNum = find.findNearestNeighbors(neighbors);
            //System.out.println("Nearest Neighbors are found successfully! \n");

            //System.out.println("Start to recover permuted signature");
            String nearestNeighbor = HashFunc.recoverSignature(a, b, nearestNeighborAndHashFuncNum);
            String idForNeighbor = tweet.findIdBySignature(nearestNeighbor);
            twoNeighbors.put(idAndSignature.getKey(), idForNeighbor);
//            System.out.println("original signature "+signature+" its neighbor is  "+nearestNeighbor);
//            System.out.println();
//            System.out.println("next");
            //String nearestNeighbors = HashFunc.recoverSignature(a,b,nearestNeighbor);
            //how to store these neighbors just print out.
//            for (int i = 0; i < nearestNeighbors.length; i++) {
//                System.out.println("original signature "+signature+"its neighbor is"+nearestNeighbors[i]);
//            }
        }

        //plz use two thread!
        WriteTxtFile writeNeighbors = new WriteTxtFile(twoNeighbors);
        try{
            writeNeighbors.writeTwoNeighbors();
        } catch (IOException e) {
            System.out.println("Write Fail!");
            e.printStackTrace();
        }

    }


    private static void initTries(JavaTrie[] tries){
        for (int i = 0; i < JavaTrie.PERMUTE_TIMES ; i++) {
            tries[i] = new JavaTrie();
        }
    }
}
