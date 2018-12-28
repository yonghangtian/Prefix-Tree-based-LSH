import java.io.*;
import java.util.HashMap;

/**
 * @author tianyh
 */
public class ReadTxtFile {
    private HashMap<String,String> idAndSignature = new HashMap<>();
    private int[] a,b;

    //getter a for hash function ax+b%p
    public int[] getA() {
        return a;
    }

    //getter b for hash function ax+b%p
    public int[] getB() {
        return b;
    }

    // getter for idAndSignature
    public HashMap<String, String> getIdAndSignature() {
        return idAndSignature;
    }

    //处理输入的文件，读取其中信息，并转换成可处理的数据类型
    //实例tweet:{"tweetid": 1063045314058153984, "timestamp_ms": "1542284727955", "features": ["birthday", "dancing", "goodluck", "sa", "saturdayyy", "gbua", "mbtc"], "vector": "{1: 1, 484: 1, 213: 1}", "signature":00001100011110011}
    public JavaTrie[] readInput(String pathname,JavaTrie[] tries){
        String line;
        String tweet;
        String signature;
        String tweetID;
        int[] bitSig;
        int[][] permutedSign;
        File filename = new File(pathname);
        //prepare permutation functions
        GenerateRandom randomAB = new GenerateRandom();

        //int i = 0;
        try{
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            this.a = randomAB.generateRandoma();
            this.b = randomAB.generateRandomb();
//            this.a = new int[]{16,8,9,17,9};
//            this.b = new int[]{9,10,0,15,18};
            //this.a = new int[]{2,17,9,12,6};
            //this.b = new int[]{13,8,2,14,6};
            //this.a = new int[]{14,18,2,18,1};
            //this.b = new int[]{12,1,5,7,11};
            while ((line = br.readLine()) != null){

                //s.replaceAll("\\pP" , "") is going to delete all symbols. re..All(" ","")delete all the whitespaces
                tweet = line.replaceAll("\\pP" , "").replaceAll(" ","").trim();
                tweetID = tweet.split("vector")[0].split("tweetid")[1];
                signature =tweet.split("signature")[1];
                this.idAndSignature.put(tweetID,signature);
                //change string signature to int[]
                bitSig = stringToInt(signature);
                if (signature.length() != JavaTrie.SIGNATURE_LENGTH){
                    System.out.println(tweet);
                    System.out.println(tweetID);
                    System.out.println(signature+" "+signature.length());
                    System.out.println("There is something wrong with input file!");
                }
//                System.out.println("this is the signature"+signature);

                permutedSign = HashFunc.hashFunc(this.a,this.b,bitSig);
                insertPermutedSignature(permutedSign,tries);
            }
        } catch (IOException e){
            e.printStackTrace();;
        }

        return tries;
    }
    public static int[] stringToInt(String signature){
        char[] chars = signature.toCharArray();
        int[] bitSignature = new int[chars.length];
        for(int i = 0; i < chars.length; i++){
            bitSignature[i] = chars[i] - 48;
            //System.out.print(a[i]);
        }
        return bitSignature;
    }

    private void insertPermutedSignature(int[][] permutedSign, JavaTrie[] tries){
        for (int i = 0; i < JavaTrie.PERMUTE_TIMES ; i++) {
            tries[i].insert(JavaTrie.toString(permutedSign[i]));
        }
    }
}
