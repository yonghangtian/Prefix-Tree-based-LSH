//import java.io.*;
//
//public class TestHammingDis {
//    public static void main(String[] args) {
//        String line;
//        String tweet;
//        String signature;
//        String tweetID;
//        String[] signatures = new String[5];
//        File filename = new File("io/testinput.txt");
//        try {
////            FileReader filereader = new FileReader(filename);
////            BufferedReader br = new BufferedReader(filereader);
//            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
//            BufferedReader br = new BufferedReader(reader);
//            int i = 0;
//            while ((line=br.readLine()) != null){
//                tweet = line.replaceAll("\\pP" , "").replaceAll(" ","");
//                signatures[i] =tweet.split("signature")[1];
//                tweetID = tweet.split("timestampms")[0].split("tweetid")[1];
//                System.out.println(line);
//                System.out.println(tweetID+"    "+signatures[i]);
//                i += 1;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (int i = 1; i < 5 ; i++)
//            for (int j = 0; j < i; j++) {
//                int hammingDis = FindNearestNeighbor.computeHammingDis(signatures[i], signatures[j]);
//                System.out.println(signatures[i]);
//                System.out.println(signatures[j] + "    " + hammingDis);
//                System.out.println();
//            }
////        for (int i = 0; i < 5; i++) {
////
////        }
//    }
//}
