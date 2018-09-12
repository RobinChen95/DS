package Chapter1_Basic;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag_SelfMade<anytype> implements Iterable<anytype>{
    private Node<anytype> first;
    private int n;

    private static class Node<anytype>{
        private anytype item;
        private Node<anytype> next;
    }

    public Bag_SelfMade(){
        first = null;
        n = 0;
    }

    public boolean isEmpty(){return first == null;}

    public int size(){return  n;}

    public void add(anytype item){
        Node<anytype> oldfirst = first;
        first = new Node<anytype>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }


    //其实不是很懂迭代器，参看Java.lang.Iterable

    public Iterator<anytype> iterator()  {
        return new ListIterator<anytype>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Bag_SelfMade<String> bag = new Bag_SelfMade<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            bag.add(item);
        }

        StdOut.println("size of bag = " + bag.size());
        for (String s : bag) {
            StdOut.println(s);
        }
    }
}
