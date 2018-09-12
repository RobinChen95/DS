package Chapter1_Basic;

public class Node_test<Integer> {

    private Node first;
    private int n;

    private class Node{
        private int data;
        private Node next;
    }

    public Node_test(){
        first = null;
        n=0;
    }

    public int size(){return n;}

    public void getIn(int vaule){
        Node oldfirst = first;
        first = new Node();
        first.data = vaule;
        first.next = oldfirst;
        n++;
    }

    public int sumNodes(){
        int sum = 0;
        Node current = new Node();
        current = first;
        for (int i=0;i<n;i++){
            sum+=current.data;
            current = current.next;
        }
        return sum;
    }

    public static void main(String args[]){
        Node_test nt = new Node_test();
        nt.getIn(1);
        nt.getIn(2);
        nt.getIn(3);
        System.out.println("size:"+nt.size());
        System.out.println("sum:"+nt.sumNodes());
    }
}
