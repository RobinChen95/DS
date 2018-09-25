package LeetCode.primary_level.ListAndTree;

public class reverseList {

    private int size=0;

    public class Node{
        int val;
        Node next;
        Node(int x){val=x;}
    }

    public Node reverseList(Node head) {
        if (head == null) return null;
        Node p1 = head;
        Node p2 = head.next;
        p1.next = null;
        while(p2!=null){
            Node temp = p2.next;
            p2.next=p1;
            p1=p2;
            p2=temp;
        }
        return p1;
    }
}
