import java.io.*;

// i do not finish it.
public class TestRecoverHash {
    public static void main(String[] args) {
        String line;
        String tweet;
        String signature;
        String tweetID;
        String[] signatures = new String[5];
        File filename = new File("io/testinput.txt");
        try {
//            FileReader filereader = new FileReader(filename);
//            BufferedReader br = new BufferedReader(filereader);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            int i = 0;
            while ((line=br.readLine()) != null){
                tweet = line.replaceAll("\\pP" , "").replaceAll(" ","");
                signatures[i] =tweet.split("signature")[1];
                tweetID = tweet.split("timestampms")[0].split("tweetid")[1];
                //System.out.println(line);
                System.out.println(tweetID+"    "+signatures[i]);
                i += 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        GenerateRandom generateAB = new GenerateRandom();
        int[] a = generateAB.generateRandoma();
        int[] b = generateAB.generateRandomb();
        for (int i = 0; i < 5 ; i++) {
            int[] intSign = ReadTxtFile.stringToInt(signatures[i]);
            int[][] permutedSign = HashFunc.hashFunc(a,b,intSign);

            System.out.println(signatures[i]);
            for (int j = 0; j < JavaTrie.PERMUTE_TIMES ; j++) {
                for (int k = 0; k <JavaTrie.SIGNATURE_LENGTH ; k++) {
                    System.out.print(permutedSign[j][k]);
                }
                System.out.println();
            }


        }
    }
}
