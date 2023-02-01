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


        System.out.println();


        System.out.println("-------------------Testing sparse matrix-------------------");
        System.out.println("-----------------------------------------------------------");
        SparseMatrix<Integer> sp =new SparseMatrix<>(5,5);
        sp.setValue(5,-1,1);
        sp.setValue(5,0,0); //will not be added and will give a message that is not add
        sp.setValue(7,2,2);
        sp.setValue(0,3,3);
        sp.setValue(10,2,3);
        sp.setValue(25,2,3);  //change 10 to 25
        sp.setValue(11,2,4);
        sp.setValue(15,5,5);
        sp.setValue(20,4,4);
        sp.setValue(30,6,6); //will not be added and will give a message that is not add
        System.out.println("-----------------------------------------------------------");
        sp.printAll_nonZeros();
        System.out.println("-----------------------------------------------------------");
        sp.printAll();
        System.out.println("-----------------------------------------------------------");
        SparseMatrix<Integer> sp2 =new SparseMatrix<>(5,5);
        sp2.setValue(5,1,1);
        sp2.setValue(7,2,2);
        sp2.setValue(10,3,3);
        sp2.setValue(10,2,3);
        sp2.setValue(11,2,4);
        sp2.setValue(15,5,5);
        sp2.setValue(20,4,4);
        sp.Add(sp2);
        System.out.println("The sum of two matrix ");
        System.out.println("-----------------------------------------------------------");
        sp.printAll();
        System.out.println("-----------------------------------------------------------");
        System.out.println("That's all :)");
    }
}
