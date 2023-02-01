public class SparseMatrix {
    //rowNode saves columns pointer the node has next and perv and index for row and pointer for  column
    private class RowNode<E> {
        private RowNode<E> Next;
        private RowNode<E> Prev;
        private ColNode<E> Element;
        private int index;

        public RowNode(RowNode<E> next, RowNode<E> prev, ColNode<E> element, int index) {
            Next = next;
            Prev = prev;
            Element = element;
            this.index = index;
        }

        public RowNode<E> getNext() {
            return Next;
        }

        public void setNext(RowNode<E> next) {
            Next = next;
        }

        public RowNode<E> getPrev() {
            return Prev;
        }

        public void setPrev(RowNode<E> prev) {
            Prev = prev;
        }

        public ColNode<E> getElement() {
            return Element;
        }

        public void setElement(ColNode<E> element) {
            Element = element;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    //Col Node class which saves the data (exactly like Dll but with index)
    private class ColNode<E> {
        private ColNode<E> Next;
        private ColNode<E> Prev;
        private Integer Element;
        private int index;

        public ColNode(ColNode<E> next, ColNode<E> prev, Integer element, int index) {
            Next = next;
            Prev = prev;
            Element = element;
            this.index = index;
        }

        public ColNode<E> getNext() {
            return Next;
        }

        public void setNext(ColNode<E> next) {
            Next = next;
        }

        public ColNode<E> getPrev() {
            return Prev;
        }

        public void setPrev(ColNode<E> prev) {
            Prev = prev;
        }

        public Integer getElement() {
            return Element;
        }

        public void setElement(Integer element) {
            Element = element;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }


}
