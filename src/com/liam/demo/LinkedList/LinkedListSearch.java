package com.liam.demo.LinkedList;


/**
 *  节点查找
 */
public class LinkedListSearch extends LinkedList {

    /**
     * 查找倒数第n个节点
     * <p>
     * 3年前平安面试题
     * 前后指针法
     * 1 A指针先走n步后开始走B指针
     * 2 A到达终点时，B到达倒是第n个节点
     *
     * @param n 节点倒数序号
     * @return 倒数第n个节点
     */
    public ListNode searchNodeReversely(ListNode head, int n) {

        if (head == null) return null;
        if (n == 0) return null;

        //构造哨兵节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fastCursor = dummy;
        ListNode slowCursor = dummy;

        //fastCursor先走n步
        int count = 0;
        while (count < n && fastCursor != null) {
            fastCursor = fastCursor.next;
            count++;
        }

        //n超出了链表本身的长度
        if (fastCursor == null) {
            return null;
        }

        //快慢指针一起走，快指针走到终点时，慢指针走到倒数第n个节点
        while (fastCursor != null) {
            fastCursor = fastCursor.next;
            slowCursor = slowCursor.next;
        }

        //解除哨兵
        dummy.next = null;

        return slowCursor;
    }

    /**
     * 查找中间节点
     * 快慢指针法
     * 1 快指针走2步，慢指针走1步，一起走
     * 2 快指针到达终点时，慢指针到达中间节点
     * @param head 头节点
     * @return 中间节点
     */
    public ListNode searchMiddleListNode(ListNode head){
        if (head == null) return null;
        ListNode fastCursor = head;
        ListNode slowCursor = head;
        while (fastCursor.next != null && fastCursor.next.next != null){
            fastCursor = fastCursor.next.next;
            slowCursor = slowCursor.next;
        }
        return slowCursor;
    }


    public static void main(String args[]) {
        int[] nums = new int[]{1,3,4,6,8,2};
        ListNode head = buildSingleLinkedList(nums);
        ListNode middle = new LinkedListSearch().searchMiddleListNode(head);
        System.out.println(middle.val);
//        ListNode node = new LinkedListSearch().searchNodeReversely(head, 7);
//        if (node == null){
//            System.out.println("null");
//        }else {
//            System.out.println(node.val);
//        }


    }

}
