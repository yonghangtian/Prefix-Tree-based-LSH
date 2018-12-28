import java.util.Arrays;
import java.util.HashMap;

public class TestTillTrieCreation {
    public static void main(String[] args) {
//                String str = "one123";
//                String regex = "(?<=one)";
//                String[] strs = str.split(regex);
//                for(int i = 0; i < strs.length; i++) {
//                    System.out.printf("strs[%d] = %s%n", i, strs[i]);
//                }

        String tweet = "{\"tweetid\": 1063045314058153984, \"timestamp_ms\": \"1542284727955\", \"features\": [\"birthday\", \"dancing\", \"goodluck\", \"sa\", \"saturdayyy\", \"gbua\", \"mbtc\"], \"vector\": \"{1: 1, 484: 1, 213: 1}\", \"signature\":00001100011110011}";
        //String regex1 = "(?<=signature)";
//        String[] part1 = tweet.split("signature");
//        String signature = part1[1].replace("}","").replace("\"","").replace(":","");
//        System.out.println(signature);
//        System.out.println(signature.length());

        String regex2 = "(?<=timestamp_ms)";
        //delete all symbols.
        String part2 = tweet.replaceAll("\\pP" , "").replaceAll(" ","");
        System.out.println(part2);
        String test = part2.split("signature")[1];
        //System.out.println(test);
        //System.out.println(test.length());
        char[] chars = test.toCharArray();
        int[] a = new int[chars.length];
        //System.out.println(a);
        for(int i = 0; i < chars.length; i++){
            a[i] = chars[i] - 48;
            System.out.print(a[i]);
        }
        System.out.println("---orignial signature----- ");
        JavaTrie[] tries = new JavaTrie[JavaTrie.PERMUTE_TIMES];
        for (int i = 0; i < JavaTrie.PERMUTE_TIMES; i++) {
            tries[i] = new JavaTrie();
        }
        JavaTrie trie = new JavaTrie();
        //String line;
        //String tweet;
        //String signature;
        //int[] bitSig;
        int[][] permutedSign;
        //prepare permutation functions
        GenerateRandom b = new GenerateRandom();
        HashFunc c = new HashFunc();
        //s.replaceAll("\\pP" , "") is going to delete all symbols. re..All(" ","")delete all the whitespaces
        //tweet = line.replaceAll("\\pP" , "").replaceAll(" ","");
        //signature =tweet.split("signature")[1];
        //change string signature to int[]
        //bitSig = stringToInt(signature);
        permutedSign = c.hashFunc(b.generateRandoma(),b.generateRandomb(),a);
        //print out permuted signatures
        for (int i = 0; i < JavaTrie.PERMUTE_TIMES; i++) {
            int zero = 0, one = 0;
            for (int j = 0; j < JavaTrie.SIGNATURE_LENGTH; j++) {
                if (permutedSign[i][j] == 1){
                    one += 1;
                }
                else {
                    zero += 1;
                }
                System.out.print(permutedSign[i][j]);
            }
            System.out.println("-----end of one permutation 1:"+one+" 0:"+zero);
        }
//        trie.insert(JavaTrie.toString(permutedSign[0]));
//        System.out.println(JavaTrie.toString(permutedSign[0]));
//
//        System.out.println(trie.exist(JavaTrie.toString(permutedSign[0])));
//        System.out.println(trie.getAllSignatures());
        for (int i = 0; i < JavaTrie.PERMUTE_TIMES ; i++) {
            tries[i].insert(JavaTrie.toString(permutedSign[i]));
            System.out.println(tries[i].getAllSignatures().keySet());
        }
        //insertpermutedSignature(permutedSign,tries);

//        System.out.println(tweet.replace(" ",""));
//        for (String part : part1) {
//            System.out.println(part.replace(" ",""));
//        }
//        for (String part:part2){
//            System.out.println(part.replace(" ",""));
//        }

//        String sign = "1110001";
//        int length = sign.length();
//        char[] charStr = sign.toCharArray();
//        int[] intArray = new int[length];
//        String str = "";
//        for(int i = 0; i < length; i++){
//            System.out.println(charStr[i]);
//            // change char to int
//            intArray[i] = charStr[i] - '0';
//            System.out.println(intArray[i]);
//            str = str+intArray[i];
//            System.out.println(str);
    }
}