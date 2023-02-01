public class SparseArray<E> {
    private static class Node<E> {
        private Integer Element;
        private Node<E> prev;
        private Node<E> Next;
        private int index ;
        public Node(Integer e, Node<E> p, Node<E> n ,int index) {
            this.index=index;
            Element = e;
            prev = p;
            Next= n;
        }
        public int getIndex() {return index;}
        public void setIndex(int index) {this.index = index;}
        public Integer getElement() { return Element; }
        public Node<E> getPrev() { return prev; }
        public Node<E> getNext() { return Next; }
        public void setPrev(Node<E> p) { prev = p; }
        public void setNext(Node<E> n) { Next = n; }
    }
    private Node<E> head ;
    private Node<E> tail ;
    private int size;

    public SparseArray(int size){
        this.size=size;
        head=new Node<>(null,null,null,-1);
        tail=new Node<>(null,head,null,size+1);
        head.setNext(tail);
    }
    private void addBetween(Integer e, Node<E> predecessor, Node<E> successor,int index) {
        Node<E> newest = new Node<>(e, predecessor, successor,index);
        predecessor.setNext(newest);
        successor.setPrev(newest);
    }
    private Node<E> SearchByIndex(int index) {
        Node<E> temp = head;
        while (temp.Next != null) {
            if (temp.getIndex() == index) {
                return temp;
            } else {
                temp = temp.Next;
            }
        }
        //if not found return null
        return null;
    }
    //bubble sort
    private Node<E> Sort(Node<E> header){
        int swapped;
        Node<E> ptr1;
        Node<E> lptr = null;
        // Checking for empty list
        if (header == null)
            return null;
        do {
            swapped = 0;
            ptr1 = header;

            while (ptr1.Next != lptr) {
                if (ptr1.index > ptr1.Next.index) {
                    int t = ptr1.index;
                    Integer te = ptr1.Element;
                    ptr1.index = ptr1.Next.index;
                    ptr1.Element = ptr1.Next.Element;
                    ptr1.Next.index = t;
                    ptr1.Next.Element = te;
                    swapped = 1;
                }
                ptr1 = ptr1.Next;
            }
            lptr = ptr1;
        }
        while (swapped != 0);
        return header;
    }
    //----------------------------------------required methods -------------------------------------------------//

    //Set value (Integer x , int index)
    public void setValue(Integer value ,int index){
        if(index>=this.size||index<0){
            System.out.println("Value "+value+" not added because the index is not valid");
            return;
        }
        Node<E> temp =SearchByIndex(index);
        if (temp!= null){
            temp.Element=value;
        }else{
            addBetween(value,head,head.getNext(),index);
        }
        head=Sort(head);
    }
    //print values with zeros
    public void printAll(){
        head= Sort(head);
        Node<E> temp =head.getNext();
        for(int i =0 ; i<size;i++){
            if(temp.getIndex()==i){
                System.out.print(" "+temp.getElement()+" ");
                temp=temp.Next;
            }else {
                System.out.print(" 0 ");
            }
        }
        System.out.println();
    }
    //print actual values only without zeros
    public void printAllNoneZeros(){
        head=Sort(head);
        Node<E> temp = head.getNext();
        while(temp.getNext()!= null){
            System.out.println(" "+temp.getElement()+" ");
            temp=temp.Next;
        }
    }
    //add two sparse array (sparseArray other)
    public void add(SparseArray<E> other){
        if(other==null||other.size!= this.size|| other.size==0 ){
            System.out.println("can't add this array because the size of this array not equal to other array");
            return;
        }
        other.head=Sort(other.head);
        head=Sort(head);
        Node<E> otherTemp =other.head.getNext();
        Node<E> temp =this.head.getNext();
        Node<E> search ;
        while(otherTemp.Next!= null){
            search=this.SearchByIndex(otherTemp.index);
            if(search==null){
                addBetween(otherTemp.Element,this.head,this.head.getNext(), otherTemp.index);
                this.head=Sort(this.head);
            }else{
                temp=this.SearchByIndex(otherTemp.index);
                temp.Element= temp.Element+otherTemp.Element;
            }
            otherTemp=otherTemp.Next;
        }
    }
}
