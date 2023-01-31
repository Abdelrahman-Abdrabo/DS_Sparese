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
    

}
