package LeetCode.primary_level.ListAndTree;

public class removeLastN {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode prenode = head;
        ListNode curnode = head;

        for (int i=0;i<n;i++){
            curnode = curnode.next;
        }

        if (curnode==null)
            return prenode.next;

        while (curnode.next!=null){
            prenode = prenode.next;
            curnode = curnode.next;
        }

        prenode.next = prenode.next.next;

        return head;
    }
}
