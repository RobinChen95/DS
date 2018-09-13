package LeetCode.primary_level;
//一个循环队列
class MyCircularQueue {
    private int[] queue;
    private int length;
    private int first=0;
    private int last=0;
    private boolean empty = true;
    private boolean full = false;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        length=k;
        queue = new int[k];
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        empty = false;
        if (full) return false;
        else {
            queue[last]=value;
            last=(last+1)%length;
        }
        if (last == first) full = true;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        full = false;
        if (empty) return false;
        else {
            first=(first+1)%length;
        }
        if(last == first) empty = true;
        return  true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (empty) return -1;
        else return queue[first];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (empty) return -1;
        else return queue[(last+length-1)%length];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return empty;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return full;
    }
    public static void main(String args[]){
       MyCircularQueue mcq = new MyCircularQueue(3);
       System.out.println(mcq.enQueue(3));
       System.out.println(mcq.enQueue(2));
       System.out.println(mcq.enQueue(1));
    }
}
