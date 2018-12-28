import java.util.HashMap;
public class TestTrie {

    public static void main(String args[])  //Just used for test
    {
        JavaTrie trie = new JavaTrie();
        trie.insert("10001");
        trie.insert("11001");
        trie.insert("11001");
        trie.insert("11101");
        trie.insert("11111");
        trie.insert("11111");
        trie.insert("10001");
        trie.insert("11001");
        trie.insert("11101");
        trie.insert("00000000000000000000000");//23

        HashMap<String, Integer> map = trie.getAllSignatures();

        for (String key : map.keySet()) {
            System.out.println(key + " appear " + map.get(key) + " times");
        }


        map = trie.getSignatureForPrefix("10001");
        System.out.println("the prefix num of 1000 is "+trie.getPrefixNum("1000"));

        System.out.println("\n\nincluding 10001 ");
        for (String key : map.keySet()) {
            System.out.println(key + " appear: " + map.get(key) + "times");
        }

        if (trie.exist("00000000000000000000000")) {
            System.out.println("\n\nthere is");
        }
        else {
            System.out.println("\n\nno");
        }
        trie.delete("00000000000000000000000");
        System.out.println(trie.exist("00000000000000000000000"));
    }
}