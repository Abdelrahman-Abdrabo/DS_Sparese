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
    // let's create a row node first and add col node to it
    private RowNode<E> head;            //head of the row
    private RowNode<E> tail;            // tail of the row
    private int VirtualROWS;            // stores the virtual Rows number
    private int VirtualCOlS;           //stores the virtual columns number

    public SparseMatrix(int Rows, int Columns) {
        this.VirtualROWS = Rows;
        this.VirtualCOlS = Columns;
        head = new RowNode<>(null, null, null, -1);
        tail = new RowNode<>(null, head, null, Rows + 1);
        head.setNext(tail);
    }
    private void addRowBetween(ColNode<E> Element, RowNode<E> prev, RowNode<E> next, int indexOfRow) {
        RowNode<E> newest  = new RowNode<>(next, prev, Element, indexOfRow);
        prev.setNext(newest);
        next.setPrev(newest);
    }

    private void addColumnBetween(Integer element, ColNode<E> prev, ColNode<E> next, int indexOfColumn) {
        ColNode<E> newest = new ColNode<>(next, prev, element, indexOfColumn);
        prev.setNext(newest);
        if (next != null) {
            next.setPrev(newest);
        }
    }

    /// sorting Rows and Columns
    private RowNode<E> bubbleSortROWS(RowNode<E> start) {
        int swapped;
        RowNode<E> ptr1;
        RowNode<E> lptr = null;

        // Checking for empty list
        if (start == null)
            return null;

        do {
            swapped = 0;
            ptr1 = start;

            while (ptr1.Next != lptr) {
                if (ptr1.index > ptr1.Next.index) {
                    int t = ptr1.index;
                    ColNode<E> te = ptr1.Element;
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
        return start;
    }
    private ColNode<E> bubbleSortCOLS(ColNode<E> start) {
        int swapped;
        ColNode<E> ptr1;
        ColNode<E> lptr = null;

        // Checking for empty list
        if (start == null)
            return null;

        do {
            swapped = 0;
            ptr1 = start;

            while (ptr1.Next != lptr) {
                if (ptr1.index > ptr1.Next.index) {
                    int t = ptr1.index;
                    Integer temp = ptr1.Element;
                    ptr1.index = ptr1.Next.index;
                    ptr1.Element = ptr1.Next.Element;
                    ptr1.Next.index = t;
                    ptr1.Next.Element = temp;
                    swapped = 1;
                }
                ptr1 = ptr1.Next;
            }
            lptr = ptr1;
        }
        while (swapped != 0);
        return start;
    }

    //sort all to sort all rows and columns by just one method (just for more readability)
    private void sortAll() {
        head = bubbleSortROWS(head);
        RowNode<E> rowTempr = head;
        while (rowTempr.Next != null) {
            rowTempr.Element = bubbleSortCOLS(rowTempr.Element);
            rowTempr = rowTempr.Next;
        }
    }

    //search by index of row
    private RowNode<E> SearchRowByIndex(int ROWindex) {
        RowNode<E> temp = head;
        // let's iterate on each row node
        while (temp.Next != null) {
            //check if this row is the required row
            //if true let's find the required column
            if (temp.getIndex() == ROWindex) {
                return temp;
            } else {
                //if the current row is not the required row index let's move to the next row node
                temp = temp.Next;
            }
        }
        //if not found return null
        return null;
    }

    //search column by its row pointer and index
    private ColNode<E> SearchColumnByIndex(RowNode<E> HERE, int ColumnIndex) {
        ColNode<E> temp = HERE.getElement();
        while (temp != null) {
            if (temp.getIndex() == ColumnIndex) {
                return temp;
            }
            temp = temp.Next;
        }
        return null;
    }

    //add two values just a copy past of set value just set Method replace values this method sums them
    private void addValue(Integer Element, int indexOfRow, int indexOfColumn) {
        if (indexOfColumn > VirtualCOlS || indexOfRow > VirtualROWS) {
            System.out.println("Element = " + Element + " Not add because one or more of indexes is not correct ");
            return;
        }
        RowNode<E> rowTemp = SearchRowByIndex(indexOfRow);
        ColNode<E> columnTemp;
        if (rowTemp != null) {
            ColNode<E> colTemp = SearchColumnByIndex(rowTemp, indexOfColumn);
            if (colTemp != null) {
                colTemp.setElement(Element + colTemp.Element);
            } else {
                addColumnBetween(Element, rowTemp.Element, rowTemp.Element.Next, indexOfColumn);
            }
        } else {   //row not found
            columnTemp = new ColNode<>(null, null, Element, indexOfColumn);
            addRowBetween(columnTemp, head, head.Next, indexOfRow);
        }
        sortAll();
    }


    //----------------------------------------------///////////////////////   required methods   ///////////////////////-------------------------------------------------//


    //set value (value , row , col )
    public void setValue(Integer Element, int indexOfRow, int indexOfColumn) {
        if (indexOfColumn > VirtualCOlS || indexOfRow > VirtualROWS ||indexOfRow<=0 ||indexOfColumn<=0 ) {
            System.out.println("Element = " + Element + " Not add because one or more of indexes is not correct ");
            return;
        }
        RowNode<E> rowTemp = SearchRowByIndex(indexOfRow);
        ColNode<E> columnTemp;
        if (rowTemp != null) {
            ColNode<E> colTemp = SearchColumnByIndex(rowTemp, indexOfColumn);
            if (colTemp != null) {
                colTemp.setElement(Element);
            } else {
                addColumnBetween(Element, rowTemp.Element, rowTemp.Element.Next, indexOfColumn);
            }
        } else {   //row not found
            columnTemp = new ColNode<>(null, null, Element, indexOfColumn);
            addRowBetween(columnTemp, head, head.Next, indexOfRow);
        }
        sortAll();
    }          //that's all (DONE)

    //print all with zeros
    public void printAll() {
        // sorting before printing
        sortAll();
        //now it's sorted before printing
        RowNode<E> rowTemp = head.Next;
        ColNode<E> colTemp;
        for (int i = 1; i <= VirtualROWS; i++) {
            if (rowTemp.getIndex() == i) {
                colTemp = rowTemp.Element;
                for (int j = 1; j <= VirtualCOlS; j++) {
                    if (colTemp != null && colTemp.getIndex() == j) {
                        System.out.print(colTemp.getElement() + " ");
                        colTemp = colTemp.Next;
                    } else {
                        System.out.print(0 + " ");
                    }
                }//columns
                System.out.println(""); //to get new break line
                rowTemp = rowTemp.Next;
            } else {
                for (int z = 1; z <= VirtualCOlS; z++)
                    System.out.print("0 ");
                System.out.println();
            }
        }
    }                                                       //That's all (DONE)

    //print all non zeros
    public void printAll_nonZeros() {
        sortAll();
        //now it's sorted before printing
        RowNode<E> rowTemp = head.Next;
        ColNode<E> colTemp;
        while (rowTemp != null) {
            colTemp = rowTemp.Element;
            while (colTemp != null) {
                System.out.print(colTemp.getElement() + " ");
                colTemp = colTemp.Next;
            }
            System.out.println(); //to print a break line
            rowTemp = rowTemp.Next;
        }
    }                                              //that's all (DONE)

    //add two mat
    //its logic is very similar to print all non zeros
    public void Add(SparseMatrix other) {
        if (((other.VirtualCOlS) != VirtualCOlS) && ((other.VirtualROWS) != VirtualROWS)) {
            System.out.println("The two matrix don't have the same size ");
            //just to break the program
            return;
        }
        RowNode<E> rowTemp = other.head.Next;
        ColNode<E> colTemp;
        while (rowTemp != null) {
            colTemp = rowTemp.Element;
            while (colTemp != null) {
                addValue(colTemp.Element, rowTemp.index, colTemp.index);
                colTemp = colTemp.Next;
            }
            rowTemp = rowTemp.Next;
        }
    }

}
//that's all (DONE)


/*Abdrabo(Abd El-Rahman Ahmed Abdrabo)
ended (12/5/2022)
 */
