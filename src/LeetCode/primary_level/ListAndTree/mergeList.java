package LeetCode.primary_level.ListAndTree;

public class mergeList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head;
        if (l1 == null || l2 == null)
            return l1 == null ? l2 : l1;
        else {
            if (l1.val < l2.val) {
                head = new ListNode(l1.val);
                l1 = l1.next;
                head.next = mergeTwoLists(l1, l2);
            } else {
                head = new ListNode(l2.val);
                l2 = l2.next;
                head.next = mergeTwoLists(l1, l2);
            }
        }

        return head;
    }
}
