import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class FindNearestNeighbor {

    /**
     * this function is going to find the true nearest neighbor of purpose signature among all nearest neighbors found from different tries.
     * @param neighbors
     * @return HashMap<Integer permutationTrieNumber(in order to recover from permutation), String theTrueNearestNeighbor>
     */
     HashMap<Integer,String> findNearestNeighbors(ArrayList<HashMap<String,Integer>> neighbors) {
        HashMap<Integer,String> nearestNeighbor = new HashMap<>();
        int hammingDis,minHammingDis;
        nearestNeighbor.put(0,neighbors.get(0).entrySet().iterator().next().getKey());
        for (int i = 1; i < JavaTrie.PERMUTE_TIMES ; i++) {
            minHammingDis = neighbors.get(0).entrySet().iterator().next().getValue();
            hammingDis = neighbors.get(i).entrySet().iterator().next().getValue();
            if (hammingDis < minHammingDis){
                //to make sure there is only one nearest neighbors
                nearestNeighbor.clear();
                nearestNeighbor.put(i,neighbors.get(i).entrySet().iterator().next().getKey());
            }
        }
        return nearestNeighbor;
    }


    /**
     * findNearestNeighborFromOneTrie is to find the nearest neighbor of signature from one permutation tree.
     * @param trie
     * @param signature
     * @return HashMap<String nearestNeighbor,Integer hammingDisBetweenNearestNeighborAndSignature>
     */

    HashMap<String, Integer> findNearestNeighborFromOneTrie(JavaTrie trie, int[] signature) {
        String tempStr = JavaTrie.toString(signature);
        //hashmap min is to store Key: the signature that has the min hamming dis to purposed signature(tempStr) and Value: min hamming dis
        HashMap<String, Integer> min = new HashMap<>();
        int hammingDis,minHammingDis;
        // minHammingSignature = min.entrySet().iterator().next().getKey()
        String minHammingSignature;
        
        HashMap<String, Integer> allSignatures;
        
        if (!trie.exist(tempStr)) {
            
            System.out.println("there is no this signature");
            return null;
            
        } else if (trie.getSignatureForPrefix(tempStr).get(tempStr) > 1) {
            
            minHammingDis = 0;
            min.put(tempStr,minHammingDis);
            return min;
            
        } else {
            // initial min
            min.put(null,JavaTrie.SIGNATURE_LENGTH);
            // i = 16
            for (int i = JavaTrie.SIGNATURE_LENGTH-1; i > 0; i--) {
                // cut off the last bit of the existing string
                String subStr = tempStr.substring(0,i-1);
                if(trie.getPrefixNum(subStr) > 1){
                    HashMap<String,Integer> possibleNeighbors = trie.getSignatureForPrefix(subStr);
                    possibleNeighbors.remove(tempStr);
                    Iterator it = possibleNeighbors.entrySet().iterator();

                    while (it.hasNext()){
                        Map.Entry pair = (Map.Entry) it.next();
                        hammingDis = computeHammingDis(tempStr,(String) pair.getKey());
                        minHammingDis = min.entrySet().iterator().next().getValue();
                        //dis == 1 is the smallest dis now, because the "else if" condition.
                        if (minHammingDis == 1){
                            return min;
                        }

                        if (hammingDis < minHammingDis){
                            min.clear();
                            min.put((String) pair.getKey(),hammingDis);
                        }
                        it.remove();
                    }
                    // jump out of for loop, since we have found the nearest neighbor.
                    break;
                }
            }
            return min;
        }

    }


    static private int computeHammingDis(String str1, String str2){
        //str1 and str2 are two string of 0 and 1 and they are of same length
        int length = str1.length();
        int hammingDis = 0;
        char[] chr1 = str1.toCharArray();
        char[] chr2 = str2.toCharArray();
        for (int i = 0; i < length ; i++) {
            if(chr1[i]!=chr2[i]){
                hammingDis += 1;
            }
        }
        return hammingDis;
    }
}
