package LeetCode.queue_stack;

public class Main {
    public static void main(String[] args) {
        MyCircularQueue mq = new MyCircularQueue(6);
        System.out.println(mq.enQueue(6));
        System.out.println(mq.Rear());
        System.out.println(mq.Rear());
        System.out.println(mq.deQueue());
        System.out.println(mq.enQueue(5));
        System.out.println(mq.Rear());
        System.out.println(mq.deQueue());
        System.out.println(mq.Front());
        System.out.println(mq.deQueue());
        System.out.println(mq.deQueue());
        System.out.println(mq.deQueue());
    }
}