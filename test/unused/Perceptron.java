//package unused;
//
//import java.io.*;
//
//public class Perceptron {
//    public static void main(String[] args) {
//        String path = "";
//    }
//
//    private void readData(String path){
//        File filename = new File(path);
//        String line;
//        int label;
//        try{
//            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
//            BufferedReader br = new BufferedReader(reader);
//            while ((line=br.readLine())!=null){
//                line.trim();
//                if(line.split(" ")[1]=="-1"){
//                    label = 0;
//                }
//                else if(line.split(" ")[1]=="+1"){
//                    label = 1;
//                }
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
