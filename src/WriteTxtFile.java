import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author tianyh
 */
public class WriteTxtFile {
    private Hashtable<String,String> twoNeighbors;
    public WriteTxtFile(){}
    public WriteTxtFile(Hashtable<String,String> twoNeighbors){
        this.twoNeighbors =  twoNeighbors;
    }
    public void writeTwoNeighbors() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("io/out.txt"));
        for (String key: this.twoNeighbors.keySet()){
            String toString= key+" "+this.twoNeighbors.get(key);
            pw.println(toString);
        }
        pw.close();
    }

    public void  writeTries(JavaTrie trie,String filename) throws IOException{
        PrintWriter pw = new PrintWriter(new FileWriter(filename));
        int index = 0;
        for (String key: trie.getAllSignatures().keySet()){
            index += 1;
            String toString = key+" "+trie.getAllSignatures().get(key)+" "+index;
            pw.println(toString);
        }
        pw.close();
    }

}
