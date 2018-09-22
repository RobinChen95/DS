package Chapter1_Basic;

public class MyListedList {
    private Node head;
    private int size;

    private class Node{
        private int val;
        private Node next;
        public Node(int data){val=data;}
    }

    public MyListedList(){
        //initial here
        head = null;
        size = 0;
    }

    public void add(int num){
        Node newNode = new Node(num);
        if (size==0){head=newNode;size++;}
        else {
            newNode.next=head;
            head=newNode;
            size++;
        }
    }

    public void display(){
        int tempSize = size;
        Node node = head;
        if (size==0){System.out.println("空链表");}
        else if(tempSize == 1){//当前链表只有一个节点
            System.out.println("["+node.val+"]");
            return;
        }
             else{
                 System.out.print("[");
                 while(tempSize>0){
                     //System.out.print("tempSize:"+tempSize+" ");
                     System.out.print(node.val);
                     if (node.next!=null){
                         node = node.next;
                         System.out.print("->");
                     }
                     tempSize--;
                 }
                 System.out.println("]");
        }
    }

    public int delete(int del){
        Node node = head;
        Node prenode = head;
        int tempSize = size;
        int deleteVal=-1;
        if (size==0) return -1;
        if (size==1) {
            if (del==node.val){deleteVal=head.val;head = null;size--;return deleteVal;}
            else{return -1;}
        }
        else {
            if (node.val==del){
                head=head.next;
                size--;
                return node.val;
            }
            else {
                node=node.next;
                while (tempSize>0){
                    if (node.val==del){
                        deleteVal = node.val;
                        prenode.next = node.next;
                        size--;
                        return deleteVal;
                    }
                    else {
                        prenode=prenode.next;
                        node=node.next;
                    }
                    tempSize--;
                }}
        }
        return  -1;
    }

    public static void main(String args[]){
        MyListedList ml = new MyListedList();
        ml.add(1);
        ml.add(2);
        ml.add(3);
        ml.add(4);
        ml.display();
        ml.delete(2);
        ml.delete(4);
        ml.display();
    }
}
