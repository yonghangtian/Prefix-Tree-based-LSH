package unused;
import java.util.HashMap;

public class TrieForNonBinary {

//这是论文中per-fix tree based LSH 算法中 per-fix tree即 trie的java实现....

    //permute times
    public static final Integer PERMUTE_TIMES = 17;
    public static final Integer SIGNATURE_LENGTH = 17;
    private class Node{
        //该字串的重复数目，  该属性统计重复次数的时候有用,取值为0、1、2、3、4、5……
        private int dumpli_num;
        //以该字串为前缀的字串数， 应该包括该字串本身！！！！！
        private int prefix_num;
        //此处用数组实现，当然也可以map或list实现以节省空间
        private Node childs[];
        //是否为单词节点
        private boolean leaf;
        public Node(){
            dumpli_num=0;
            prefix_num=0;
            leaf=false;
            childs=new Node[PERMUTE_TIMES];
        }
    }


    private Node root;///树根
    public TrieForNonBinary(){
        ///初始化trie 树
        root=new Node();
    }



    /**
     * 插入字串，用循环代替迭代实现
     * @param hashedSignature
     */
    public void insert(int[] hashedSignature){
        insert(this.root, hashedSignature);
    }
    /**
     * 插入字串，用循环代替迭代实现
     * @param root
     * @param hashedSignature
     */
    private void insert(Node root,int[] hashedSignature){
        for(int i=0; i<SIGNATURE_LENGTH; i++){
            //用hash过后的signature的值来做下标，因为只有这几个
            int index=hashedSignature[i];
            if(root.childs[index]!=null){
                //已经存在了，该子节点prefix_num++
                root.childs[index].prefix_num++;
            }else{
                //如果不存在
                root.childs[index]=new Node();
                root.childs[index].prefix_num++;
            }

            //如果到了字串结尾，则做标记
            if(i==SIGNATURE_LENGTH-1){
                root.childs[index].leaf=true;
                root.childs[index].dumpli_num++;
            }
            //root指向子节点，继续处理
            root=root.childs[index];
        }

    }


    /**
     * 遍历Trie树，查找所有的hashedSignature以及出现次数
     * @return HashMap<String, Integer> map
     */
    public HashMap<Integer[],Integer> getAllhashedSignature(){
        //HashMap<String, Integer> map=new HashMap<String, Integer>();
        int levelCounter = 0;
        return preTraversal(this.root,null,levelCounter);
    }

    /**
     * 前序遍历。。。
     * @param root		子树根节点
     * @param prefixs	查询到该节点前所遍历过的前缀
     * @return
     */
    private  HashMap<Integer[],Integer> preTraversal(Node root,Integer[] prefixs,int levelCounter){
        HashMap<Integer[], Integer> map=new HashMap<>();

        Integer[] tempArray = new Integer[levelCounter+1];
        if(root!=null){
            levelCounter += 1;
            if(root.leaf==true){
                ////当前即为一个hashedSignature
                map.put(prefixs, root.dumpli_num);
            }

            for(int i=0,length=root.childs.length; i<length;i++){
                if(root.childs[i]!=null){
                    ////递归调用前序遍历
                    tempArray[levelCounter] = i;
                    map.putAll(preTraversal(root.childs[i], tempArray, levelCounter));
                }
            }
        }

        return map;
    }



    /**
     * 判断某字串是否在字典树中
     * @param hashedSignature
     * @return true if exists ,otherwise  false
     */
    public boolean exist(Integer[] hashedSignature){
        return search(this.root, hashedSignature);
    }
    /**
     * 查询某字串是否在字典树中
     * @param hashedSignature
     * @return true if exists ,otherwise  false
     */
    private boolean search(Node root,Integer[] hashedSignature){
        for(int i=0; i<SIGNATURE_LENGTH;i++){
            int index=hashedSignature[i];
            if(root.childs[index]==null){
                ///如果不存在，则查找失败
                return false;
            }
            root=root.childs[index];
        }

        return true;
    }

    /**
     * 得到以某字串为前缀的字串集，包括字串本身！ 类似单词输入法的联想功能
     * @param prefix 数组前缀
     * @return 字串集以及出现次数，如果不存在则返回null
     */
    public HashMap<Integer[], Integer> gethashedSignatureForPrefix(Integer[] prefix){
        return gethashedSignatureForPrefix(this.root, prefix);
    }
    /**
     * 得到以某字串为前缀的字串集，包括字串本身！
     * @param root
     * @param prefix
     * @return 字串集以及出现次数
     */
    private HashMap<Integer[], Integer> gethashedSignatureForPrefix(Node root,Integer[] prefix){
        HashMap<Integer[], Integer> map=new HashMap<>();
        int length=prefix.length;
        for(int i=0; i<length; i++){

            int index=prefix[i];
            if(root.childs[index]==null){
                return null;
            }

            root=root.childs[index];

        }
        ///结果包括该前缀本身
        ///此处利用之前的前序搜索方法进行搜索
        return preTraversal(root, prefix,length);
    }

}
