import java.util.HashMap;
/**
 * @author tianyh
 */
public class JavaTrie {

    // permuteTimes should be a prime number(only have 1 and itself to divide)
    public static final Integer PERMUTE_TIMES = 19;

    public static final Integer SIGNATURE_LENGTH = 19;

    public static String toString(int[] signature){
        String strSign = "";
        for (int i = 0; i < signature.length; i++) {
            strSign = strSign + signature[i];
        }
        return strSign;
    }

    private class Node{
        //以该字串为前缀的字串数， 应该包括该字串本身！！！！！
        private int prefix_num;
        //该字串的重复数目，  该属性统计重复次数的时候有用,取值为0、1、2、3、4、5……
        private int duplicate_num;
        //是否为单词节点
        private boolean leaf;
        private Node[] childs;
        //此处用数组实现，当然也可以map或list实现以节省空间
        public Node(){
            duplicate_num=0;
            prefix_num=0;
            leaf=false;
            childs=new Node[2];
        }
    }
    //public Node getChild(){
    //    return root.childs[1];
    //}

    private Node root;
    //树根
    public JavaTrie(){
        ///初始化trie 树
        root = new Node();
    }

    /**
     * 插入字串，用循环代替迭代实现
     * @param signature
     */
    public void insert(String signature){
        //将this.root的地址传递给insert中的root，然后root不断地修改这个地址存贮的数据，但是this.root依然指向第一个节点。
        insert(this.root, signature);
    }
    /**
     * 插入字串，用循环代替迭代实现
     * @param root
     * @param signature
     */

    private void insert(Node root,String signature){
        //转化为数组
        int lengthSignature = signature.length();
        char[] strChar = signature.toCharArray();
//        int[] bitArray = new int[lengthSignature];
//        for(int j = 0; j < lengthSignature; j++ ){
//            bitArray[j] = strChar[j] - '0';
//        }
        for(int i=0; i<lengthSignature; i++){
            if(strChar[i] == '0'){
                //如果是0，prefix_num++
                if(root.childs[0] == null){
                    root.childs[0] = new Node();
                    root.childs[0].prefix_num++;
                }
                else{
                    root.childs[0].prefix_num++;
                }
                root = root.childs[0];
            }else if (strChar[i] == '1'){
                // if strChar is 1
                if(root.childs[1] == null){
                    root.childs[1] = new Node();
                    root.childs[1].prefix_num++;
                }
                else{
                    root.childs[1].prefix_num++;
                }
                root = root.childs[1];
            }

            //如果到了字符数组结尾，做标记，且指定子节点为下个循环的root
            if(i==lengthSignature-1){
                if(strChar[i]=='0'){
                    root.leaf=true;
                    root.duplicate_num++;
                }
                else if(strChar[i] == '1'){
                    root.leaf=true;
                    root.duplicate_num++;
                }
            }
        }

    }


    /**
     * 遍历Trie树，查找所有的words以及出现次数
     * @return HashMap<String, Integer> map
     */
    public HashMap<String,Integer> getAllSignatures(){
//		HashMap<String, Integer> map=new HashMap<String, Integer>();

        return preTraversal(this.root, "");
    }

    /**
     * 前序遍历。。。
     * @param root		子树根节点
     * @param prefixs	查询到该节点前所遍历过的前缀
     * @return
     */
    private  HashMap<String,Integer> preTraversal(Node root,String prefixs){
        HashMap<String, Integer> map=new HashMap<String, Integer>();

        if(root!=null){

            if(root.leaf){
                ////当前即为一个单词
                map.put(prefixs, root.duplicate_num);
            }

            for(int i=0,length=root.childs.length; i<length;i++){
                if(root.childs[i]!=null){
                    //char ch= i +  '0';
                    //递归调用前序遍历
                    String tempStr=prefixs+i;
                    map.putAll(preTraversal(root.childs[i], tempStr));
                }
            }
        }

        return map;
    }


    /**
     * 判断某字串是否在字典树中
     * @param signature
     * @return true if exists ,otherwise  false
     */
    public boolean exist(String signature){
        return search(this.root, signature);
    }
    /**
     * 查询某字串是否在字典树中
     * @param signature
     * @return true if exists ,otherwise  false
     */
    private boolean search(Node root,String signature){
        char[] chs=signature.toCharArray();
        for(int i=0,length=chs.length; i<length;i++){
            if((chs[i] == '0')&&(root.childs[0] != null)){
                root = root.childs[0];
            }
            else if((chs[i] == '1')&&(root.childs[1] != null)){
                root = root.childs[1];
            }
            else {
                return false;
            }
        }
        return true;
    }


    /**
     * 得到以某字串为前缀的字串集，包括字串本身！ 类似单词输入法的联想功能
     * @param prefix 字串前缀
     * @return 字串集以及出现次数，如果不存在则返回null
     */
    public HashMap<String, Integer> getSignatureForPrefix(String prefix){
        return getSignaturesForPrefix(this.root, prefix);
    }
    /**
     * 得到以某字串为前缀的字串集，包括字串本身！
     * @param root
     * @param prefix
     * @return 字串集以及出现次数
     */
    private HashMap<String, Integer> getSignaturesForPrefix(Node root,String prefix){
        HashMap<String, Integer> map=new HashMap<>();
        char[] chs=prefix.toCharArray();
        for(int i=0,length=chs.length; i<length; i++) {
            if ((chs[i] == '0') && (root.childs[0] != null)) {
                root = root.childs[0];
            } else if ((chs[i] == '1') && (root.childs[1] != null)) {
                root = root.childs[1];
            }
            else{
                System.out.println("there is no signature for this prefix: "+prefix);
                return null;
            }
        }
        ///结果包括该前缀本身
        ///此处利用之前的前序搜索方法进行搜索
        return preTraversal(root, prefix);
    }

    public int getPrefixNum(String prefix){
        return getPrefixNumUnderRoot(this.root,prefix);
    }
    /** this function is going to find the requierd prefix's prefix_num
     * @param prefix
     * @return int prefix's prefix_num
     */
    public int getPrefixNumUnderRoot(Node root, String prefix) {
        char[] chs = prefix.toCharArray();
        for (int i = 0, length = chs.length; i < length; i++) {
            if ((chs[i] == '0') && (root.childs[0] != null)) {
                root = root.childs[0];
            } else if ((chs[i] == '1') && (root.childs[1] != null)) {
                root = root.childs[1];
            } else {
                System.out.printf("there is no signature for this prefix");
                return 0;
            }
        }
        return root.prefix_num;
    }

    public void delete(String signature){
        deleteSignature(this.root,signature);
    }

    private void deleteSignature(Node root,String signature){
        if((exist(signature))&&(signature.length()==JavaTrie.SIGNATURE_LENGTH)){
            char[] chs = signature.toCharArray();
            for (int i = 0; i < JavaTrie.SIGNATURE_LENGTH;i++){
                if (chs[i] == '0') {
                    root = root.childs[0];
                } else if (chs[i] == '1') {
                    root = root.childs[1];
                }

                if((root.prefix_num>1)&&(!root.leaf)){
                    root.prefix_num -= 1;
                } else if((root.prefix_num == 1)&&(!root.leaf)){
                    root.childs[0] = null;
                    root.childs[1] = null;
                    return;
                } else if(root.leaf){
                    root.prefix_num -=1;
                    root.duplicate_num -=1;
                    return;
                }
            }
        }
        else {
            System.out.println("Signature not exist: "+signature);
        }

    }
}