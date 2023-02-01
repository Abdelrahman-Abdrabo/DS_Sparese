public class Main {
    public static void main(String[] args) {
        System.out.println("-------------------Testing sparse array-------------------");
        SparseArray<Integer> SA =new SparseArray<>(5);
        SA.setValue(5,0);
        SA.setValue(5,-6);
        SA.setValue(0,1);
        SA.setValue(6,2);
        SA.setValue(7,3);
        System.out.println("-----------------------------------------------------------");
        SA.printAll();
        System.out.println("-----------------------------------------------------------");
        SA.printAllNoneZeros();
        System.out.println("-----------------------------------------------------------");
        SparseArray<Integer> SA2 =new SparseArray<>(5);
        SA2.setValue(1,1);
        SA2.setValue(2,2);
        SA2.setValue(3,3);
        SA2.setValue(4,4);
        SA2.printAll();
        System.out.println("-----------------------------------------------------------");
        SA.add(SA2);
        SA.printAll();
        System.out.println("-----------------------------------------------------------");
        SA.printAllNoneZeros();
        System.out.println("-----------------------------------------------------------");
    }
}
