import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * @author tianyh
 */
public class HashFunc {

    static int[][] hashFunc(int[] a, int[] b, int[] valueToHash){
        int[][] result = new int[JavaTrie.PERMUTE_TIMES][JavaTrie.SIGNATURE_LENGTH];
        for(int i = 0; i < JavaTrie.PERMUTE_TIMES; i++ ){
            result[i] = getHashedValue(a[i],b[i],valueToHash);
            //print out to check
//            System.out.println("this is i="+i+"  this is a[i]="+a[i]+"  this is b[i]="+b[i]);
//            System.out.println("the permutation result is "+ Arrays.toString(result[i]));
        }
        return result;
    }

    private static int[] getHashedValue(int a, int b, int[] valueToHash){
        int hashValue = 0;
        int[] result = new int[JavaTrie.SIGNATURE_LENGTH];
        for (int i = 0; i < JavaTrie.SIGNATURE_LENGTH ; i++) {
            // (ax+b)%p p should be signature length since (ax+b)%p have to locate in [0, Signature_Length]
            hashValue = (a*i+b)%(JavaTrie.SIGNATURE_LENGTH);
            result[i] = valueToHash[hashValue];
        }
        return result;
    }

    static String recoverSignature(int[] a, int[] b, HashMap<Integer,String> nearestNeighbors){
//        Set<Integer> numOfHashFunc = nearestNeighbors.keySet();
//        Integer[] nums = (Integer[]) numOfHashFunc.toArray();
//        String[] signatures = new String[nums.length];
//        for (int j = 0; j < nums.length; j++) {
//            signatures[j] = recoverPermuteSignature(a[nums[j]],b[nums[j]],nearestNeighbors.get(nums[j]));
//        }
        //return signatures;
        String signature = new String();
        int num = nearestNeighbors.entrySet().iterator().next().getKey();
        signature = recoverPermuteSignature(a[num],b[num],nearestNeighbors.get(num));
        return signature;
    }

    static String recoverPermuteSignature(int a, int b, String permutedSignature){
         // recovered signature
         String signature="";
         int hashValue;
         int[] intSign = new int[JavaTrie.SIGNATURE_LENGTH];
         int[] intPermutedSign = ReadTxtFile.stringToInt(permutedSignature);
         for (int i = 0; i < JavaTrie.SIGNATURE_LENGTH; i++){
             hashValue = (a*i+b)%(JavaTrie.SIGNATURE_LENGTH);
             intSign[hashValue] = intPermutedSign[i];
         }
        for (int i = 0; i <JavaTrie.SIGNATURE_LENGTH ; i++) {
            signature = signature+intSign[i];
        }
        return signature;
    }
}
