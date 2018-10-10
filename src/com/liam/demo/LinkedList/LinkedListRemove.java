package com.liam.demo.LinkedList;

/**
 * 链表删除
 */
public class LinkedListRemove extends LinkedList {


    /**
     * delete the n-th node backwards
     *
     * @param head 头节点
     * @param n    倒数节点序号
     */
    public ListNode removeNodeBackwards(ListNode head, int n) {

        //find the n th node backwards

        //default head means that we have move 1 step
        ListNode fastCursor = head;
        ListNode slowCursor = head;


        //move n-1 step for fast cursor,
        int step = 1;
        while (step < n && fastCursor != null) {
            fastCursor = fastCursor.next;
            step++;
        }

        // n > linkedList length, no legal node found
        if (fastCursor == null) {
            return head;
        }

        //starting from the head node, we just need move fast cursor to the last node
        //cache the prevCursor to delete the last n-th node
        ListNode prevCursor = null;
        while (fastCursor.next != null) {
            fastCursor = fastCursor.next;
            prevCursor = slowCursor;
            slowCursor = slowCursor.next;
        }


        //remove the n-th node, we have two cases
        if (prevCursor == null){ //the last n-th node is the head node
            head = head.next;
        }else { //make pre -> next
            prevCursor.next = slowCursor.next;
        }

        return head;
    }

    public static void main(String args[]) {
        int[] nums = new int[]{1,3,4,6,8,2};
        ListNode head = buildSingleLinkedList(nums);
        ListNode result = new LinkedListRemove().removeNodeBackwards(head, 6);
        printSingleLinkedList(result);
    }
}
