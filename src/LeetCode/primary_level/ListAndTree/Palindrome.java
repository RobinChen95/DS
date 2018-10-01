package LeetCode.primary_level.ListAndTree;

public class Palindrome {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        } else {
            ListNode slow = head, fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            if (fast != null) {
                slow = slow.next;
                slow = reverseList(slow);
            } else {
                slow = reverseList(slow);
            }
            while (slow != null) {
                if (head.val != slow.val) return false;
                slow = slow.next;
                head = head.next;
            }
            return true;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null && head.next == null) {
            return head;
        } else {
            ListNode p1 = head;
            ListNode p2 = head.next;
            ListNode temp = p2.next;
            p1.next = null;
            while (p2.next != null) {
                p2.next = p1;
                p1 = p2;
                p2 = temp;
                temp = p2.next;//java.lang.NullPointerException Input:[1,2]
            }
            return p1;
        }
    }

}
