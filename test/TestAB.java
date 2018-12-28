public class TestAB {
    public static void main(String[] args) {
        GenerateRandom generateAB = new GenerateRandom();
        int[] a = generateAB.generateRandoma();
        int[] b = generateAB.generateRandomb();
        for(int c :a){
            System.out.print(c+" ");
        }
        System.out.println();
        for (int d :b){
            System.out.print(d+" ");
        }
    }
}
